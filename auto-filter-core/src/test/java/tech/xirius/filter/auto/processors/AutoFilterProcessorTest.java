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

import static com.google.testing.compile.CompilationSubject.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.Compiler;
import com.google.testing.compile.JavaFileObjects;

/**
 * Tests the {@link AutoFilterProcessor}
 */
@RunWith(JUnit4.class)
public class AutoFilterProcessorTest {
    @Test
    public void emptyEntityFilter() {
        Compilation compilation = Compiler.javac()
                .withProcessors(new AutoFilterProcessor())
                .compile(
                        JavaFileObjects.forResource("test/EmptyEntity.java"));
        assertThat(compilation).succeededWithoutWarnings();
        assertThat(compilation).generatedSourceFile("test/EmptyEntityFilter")
                .hasSourceEquivalentTo(JavaFileObjects.forResource("test/results/EmptyEntityFilter.java"));
    }

    @Test
    public void basicEntityFilter() {
        Compilation compilation = Compiler.javac()
                .withProcessors(new AutoFilterProcessor())
                .compile(
                        JavaFileObjects.forResource("test/BasicEntity.java"));
        assertThat(compilation).succeededWithoutWarnings();
        assertThat(compilation).generatedSourceFile("test/BasicEntityFilter")
                .hasSourceEquivalentTo(JavaFileObjects.forResource("test/results/BasicEntityFilter.java"));
    }

    @Test
    public void entityWithEnumFilter() {
        Compilation compilation = Compiler.javac()
                .withProcessors(new AutoFilterProcessor())
                .compile(
                        JavaFileObjects.forResource("test/EntityWithEnum.java"),
                        JavaFileObjects.forResource("test/EnumTest.java"));
        assertThat(compilation).succeededWithoutWarnings();
        assertThat(compilation).generatedSourceFile("test/EntityWithEnumFilter")
                .hasSourceEquivalentTo(JavaFileObjects.forResource("test/results/EntityWithEnumFilter.java"));
    }

    @Test
    public void excludeFieldEntityFilter() {
        Compilation compilation = Compiler.javac()
                .withProcessors(new AutoFilterProcessor())
                .compile(
                        JavaFileObjects.forResource("test/ExcludeFieldEntity.java"));
        assertThat(compilation).succeededWithoutWarnings();
        assertThat(compilation).generatedSourceFile("test/ExcludeFieldEntityFilter")
                .hasSourceEquivalentTo(JavaFileObjects.forResource("test/results/ExcludeFieldEntityFilter.java"));
    }

    @Test
    public void customFilterEntityFilter() {
        Compilation compilation = Compiler.javac()
                .withProcessors(new AutoFilterProcessor())
                .compile(
                        JavaFileObjects.forResource("test/CustomFilterEntity.java"),
                        JavaFileObjects.forResource("test/CustomFilter.java"));
        assertThat(compilation).succeededWithoutWarnings();
        assertThat(compilation).generatedSourceFile("test/CustomFilterEntityFilter")
                .hasSourceEquivalentTo(JavaFileObjects.forResource("test/results/CustomFilterEntityFilter.java"));
    }

    @Test
    public void autoFilterReferenceEntityFilter() {
        Compilation compilation = Compiler.javac()
                .withProcessors(new AutoFilterProcessor())
                .compile(
                        JavaFileObjects.forResource("test/AutoFilterReferenceEntity.java"),
                        JavaFileObjects.forResource("test/BasicEntity.java"));
        assertThat(compilation).succeededWithoutWarnings();
        assertThat(compilation).generatedSourceFile("test/AutoFilterReferenceEntityFilter")
                .hasSourceEquivalentTo(
                        JavaFileObjects.forResource("test/results/AutoFilterReferenceEntityFilter.java"));
        assertThat(compilation).generatedSourceFile("test/BasicEntityFilter");
    }

    @Test
    public void ExcludeAndIncludeTheSameField() {
        Compilation compilation = Compiler.javac()
                .withProcessors(new AutoFilterProcessor())
                .compile(
                        JavaFileObjects.forResource("test/ExcludeAndIncludeFieldEntity.java"));
        assertThat(compilation).succeededWithoutWarnings();
        assertThat(compilation).generatedSourceFile("test/ExcludeAndIncludeFieldEntityFilter")
                .hasSourceEquivalentTo(
                        JavaFileObjects.forResource("test/results/ExcludeAndIncludeFieldEntityFilter.java"));
    }

    @Test
    public void badCyclicalReferenceFilter() {
        Compilation compilation = Compiler.javac()
                .withProcessors(new AutoFilterProcessor())
                .compile(
                        JavaFileObjects.forResource("test/CyclicalReferenceEntity1.java"),
                        JavaFileObjects.forResource("test/CyclicalReferenceEntity2.java"));
        assertThat(compilation).failed();
    }

    @Test
    public void typeUseAnnotationFilter() {
        Compilation compilation = Compiler.javac()
                .withProcessors(new AutoFilterProcessor())
                .compile(
                        JavaFileObjects.forResource("test/EntityWithTypeUseAnnotation.java"),
                        JavaFileObjects.forResource("test/TypeUseAnnotation.java"));
        assertThat(compilation).succeededWithoutWarnings();
        assertThat(compilation).generatedSourceFile("test/EntityWithTypeUseAnnotationFilter")
                .hasSourceEquivalentTo(JavaFileObjects.forResource("test/results/EntityWithTypeUseAnnotationFilter.java"));
    }
}
