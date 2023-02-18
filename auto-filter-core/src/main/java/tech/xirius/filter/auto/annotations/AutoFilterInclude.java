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
package tech.xirius.filter.auto.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import tech.xirius.filter.filtering.Filter;

/**
 * This annotation indicates that a field must be included
 * in the generated filter of an element annotated with
 * {@link AutoFilter}
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface AutoFilterInclude {
    /**
     * The base filter to be used for the annotated field. This overrides
     * the value of {@link AutoFilter#baseFilter()}
     * 
     * @return the base filter of the field
     */
    Class<? extends Filter> baseFilter();
}
