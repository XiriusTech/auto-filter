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

import java.math.BigDecimal;
import tech.xirius.filter.filtering.BasicFilter;
import tech.xirius.filter.filtering.Filter;

public class EntityWithEnumFilter implements Filter {

    private BasicFilter<BigDecimal> myNumber = null;
    private EnumTestFilter myEnum = null;

    public void setMyNumber(BasicFilter<BigDecimal> value) {
        this.myNumber = value;
    }

    public BasicFilter<BigDecimal> getMyNumber() {
        return this.myNumber;
    }

    public void setMyEnum(EnumTestFilter value) {
        this.myEnum = value;
    }

    public EnumTestFilter getMyEnum() {
        return this.myEnum;
    }


    public static class EnumTestFilter extends BasicFilter<EnumTest> {
    }
}
