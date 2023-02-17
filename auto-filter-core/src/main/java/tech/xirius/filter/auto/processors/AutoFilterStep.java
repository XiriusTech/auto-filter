package tech.xirius.filter.auto.processors;

import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.util.ElementFilter;
import javax.tools.JavaFileObject;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DisplayTool;

import com.google.auto.common.BasicAnnotationProcessor.Step;
import com.google.common.collect.ImmutableSetMultimap;

import tech.xirius.filter.auto.annotations.AutoFilter;
import tech.xirius.filter.auto.annotations.AutoFilterExclude;
import tech.xirius.filter.auto.annotations.AutoFilterInclude;
import tech.xirius.filter.filtering.Filter;

public class AutoFilterStep implements Step {
    private ProcessingEnvironment processingEnv;

    public AutoFilterStep(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    private VelocityEngine prepareEngine() {
        VelocityEngine engine = new VelocityEngine();
        engine.setProperty("resource.loader", "class");
        engine.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        engine.init();
        return engine;
    }

    private Template prepareTemplate(VelocityEngine engine, String name) {
        return engine.getTemplate(name);
    }

    public void processSingleElement(Template template, Element element) {

        String className = ((TypeElement) element).getQualifiedName().toString();
        String baseFilterName = getAnnotationClassParameter(element, AutoFilter.class, AutoFilter::baseFilter);

        try {
            writeFile(baseFilterName, className, element, template);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<FieldWrapper> processFields(Element classElement, ClassData baseFilter) {
        List<VariableElement> fields = ElementFilter.fieldsIn(classElement.getEnclosedElements()).stream()
                .filter(y -> y.getAnnotationsByType(AutoFilterExclude.class).length == 0)
                .collect(Collectors.toList());

        List<FieldWrapper> fieldsWrapper = new ArrayList<>();

        for (VariableElement field : fields) {
            ClassData localBaseFilter = baseFilter;
            String fieldAnnotationFilterName = getAnnotationClassParameter(field, AutoFilterInclude.class,
                    AutoFilterInclude::baseFilter);

            if (fieldAnnotationFilterName != null)
                localBaseFilter = new ClassData(fieldAnnotationFilterName);
            else if (!FieldTypeHandler.isFieldTypeAdmitted(field, this.processingEnv))
                continue;

            FieldWrapper wrapper = new FieldWrapper(field.getSimpleName().toString(),
                    FieldTypeHandler.handleFieldType(field.asType(), this.processingEnv), localBaseFilter, false);
            fieldsWrapper.add(wrapper);

            if (FieldTypeHandler.isFieldEnum(field)) {
                wrapper.setEnum(true);
                wrapper.setFinalFilterString(wrapper.getType().getSimpleClassName() + "Filter");
            } else {
                if (wrapper.getBaseFilter().getGenericTypeCount() > 0) {
                    wrapper.setFinalFilterString(wrapper.getBaseFilter().getSimpleClassName() + "<"
                            + wrapper.getType().getSimpleClassName() + ">");
                }
            }
        }
        return fieldsWrapper;
    }

    private void writeFile(String baseFilterName, String className, Element classElement, Template template)
            throws IOException {

        ClassData classData = new ClassData(className);
        String filterClassName = classData.getFullClassName() + "Filter";

        VelocityContext context = new VelocityContext();

        context.put("display", new DisplayTool());
        context.put("packageName", classData.getPackageName());
        context.put("className", classData.getSimpleClassName());

        Set<ClassData> importTypes = new HashSet<>();
        List<FieldWrapper> fieldsWrapper = processFields(classElement, new ClassData(baseFilterName));

        ClassData filterInterface = new ClassData(Filter.class.getName());
        importTypes.add(filterInterface);
        for (FieldWrapper wrapper : fieldsWrapper) {
            importTypes.add(wrapper.getBaseFilter());
            importTypes.add(wrapper.getType());
        }

        importTypes = cleanImports(importTypes, classData.getPackageName());

        context.put("filterInterface", filterInterface.getSimpleClassName());
        context.put("fields", fieldsWrapper);
        context.put("imports", importTypes);

        JavaFileObject filterFile = this.processingEnv.getFiler().createSourceFile(filterClassName);
        try (Writer out = filterFile.openWriter()) {
            template.merge(context, out);
        }
    }

    private Set<ClassData> cleanImports(Set<ClassData> imports, String packageName) {
        return imports.stream().filter(x -> x.getPackageName() != null && !x.getPackageName().equals("java.lang")
                && !x.getPackageName().equals(packageName)).collect(Collectors.toSet());
    }

    // Returns full name including generic type, e.g. String is java.lang.String;
    // ArrayList<T> extends List<T> is java.util.ArrayList<T>
    private <T extends Annotation> String getAnnotationClassParameter(Element ele, Class<T> annotationType,
            Consumer<T> classMethod) {
        ele.getAnnotation(annotationType);
        T annotation = ele.getAnnotation(annotationType);
        if (annotation != null) {
            try {
                classMethod.accept(annotation);
            } catch (MirroredTypeException mte) {
                TypeElement type = this.processingEnv.getElementUtils().getTypeElement(mte.getTypeMirror().toString());
                return type.asType().toString();
            }
        }
        return null;
    }

    @Override
    public Set<String> annotations() {
        return new HashSet<>(Arrays.asList("tech.xirius.filter.auto.annotations.AutoFilter"));
    }

    @Override
    public Set<? extends Element> process(ImmutableSetMultimap<String, Element> arg0) {
        VelocityEngine engine = prepareEngine();
        Template template = prepareTemplate(engine, "templates/auto-filter.vm");

        Set<String> annotations = annotations();
        Set<Element> deferredElements = new HashSet<>();
        for (String annotation : annotations) {
            Set<? extends Element> annotatedElements = arg0.get(annotation);

            for (Element element : annotatedElements) {

                try {
                    processSingleElement(template, element);
                } catch (Exception e) {
                    deferredElements.add(element);
                }
            }
        }
        return deferredElements;
    }
}
