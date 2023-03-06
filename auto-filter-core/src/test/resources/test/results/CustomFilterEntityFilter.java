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

import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.filtering.RangeFilter;

public class CustomFilterEntityFilter implements Filter {

    private RangeFilter<String> myString = null;
    private CustomFilter<Integer> myInteger = null;

    public void setMyString(RangeFilter<String> value) {
        this.myString = value;
    }

    public RangeFilter<String> getMyString() {
        return this.myString;
    }

    public void setMyInteger(CustomFilter<Integer> value) {
        this.myInteger = value;
    }

    public CustomFilter<Integer> getMyInteger() {
        return this.myInteger;
    }


}
