/*
 * Copyright (C) 2023 Xirius Tech S.A.S
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tech.xirius.filter.auto.processors;

import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;

/**
 * Interprets the information contained in a {@link TypeMirror}
 */
public class FieldTypeHandler {

    public static boolean isFieldEnum(Element field) {
        TypeElement typeElement = null;
        if (field.asType().getKind() == TypeKind.DECLARED) {
            typeElement = (TypeElement) ((DeclaredType) field.asType()).asElement();
        }

        return (typeElement != null && typeElement.getKind() == ElementKind.ENUM);
    }
    
    public static boolean isFieldTypeAdmitted(Element field, ProcessingEnvironment processingEnv) {

        String fieldType = field.asType().toString();
        Set<String> otherAdmittedClasses = Set.of("java.lang.String", "java.math.BigDecimal");
        return isFieldTypePrimitive(field) || isFieldTypeBoxedPrimitive(field, processingEnv)
                || otherAdmittedClasses.contains(fieldType) || isFieldEnum(field);
    }

    public static boolean isFieldTypePrimitive(Element field) {
        return field.asType() instanceof PrimitiveType;
    }

    public static boolean isFieldTypeBoxedPrimitive(Element field, ProcessingEnvironment processingEnv) {
        try {
            return processingEnv.getTypeUtils().unboxedType(field.asType()) != null;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static ClassData handleFieldType(TypeMirror type, ProcessingEnvironment processingEnv) {
        if (type instanceof PrimitiveType) {
            Types types = processingEnv.getTypeUtils();
            return new ClassData(types.boxedClass((PrimitiveType) type).asType().toString());
        }

        return new ClassData(type.toString());
    }
}
