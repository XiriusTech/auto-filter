package tech.xirius.filter.auto.processors;

import java.util.Arrays;

import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.auto.service.AutoService;

@SupportedAnnotationTypes("tech.xirius.filter.auto.annotations.AutoFilter")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
@AutoService(Processor.class)
public class AutoFilterProcessor extends BasicAnnotationProcessor {

    protected Iterable<? extends Step> steps() {
        return Arrays.asList(new AutoFilterStep(super.processingEnv));
    }
}
