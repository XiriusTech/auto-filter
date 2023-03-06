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
package test;

import tech.xirius.filter.filtering.BasicFilter;
import tech.xirius.filter.filtering.Filter;

public class AutoFilterReferenceEntityFilter implements Filter {

    private BasicFilter<Integer> intField = null;
    private BasicEntityFilter otherEntity = null;

    public void setIntField(BasicFilter<Integer> value) {
        this.intField = value;
    }

    public BasicFilter<Integer> getIntField() {
        return this.intField;
    }

    public void setOtherEntity(BasicEntityFilter value) {
        this.otherEntity = value;
    }

    public BasicEntityFilter getOtherEntity() {
        return this.otherEntity;
    }


}