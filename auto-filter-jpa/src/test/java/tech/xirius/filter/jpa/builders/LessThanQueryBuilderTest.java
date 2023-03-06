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
package tech.xirius.filter.jpa.builders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import tech.xirius.filter.filtering.InFilter;
import tech.xirius.filter.filtering.LessThanFilter;
import tech.xirius.filter.jpa.AbstractQueryBuilderTest;
import tech.xirius.filter.jpa.utils.QueryTestUtils;
import tech.xirius.filter.jpa.utils.SampleEntity;
import tech.xirius.filter.jpa.utils.SampleEntity_;

/**
 * Tests for {@link LessThanQueryBuilder}
 */
@RunWith(JUnit4.class)
public class LessThanQueryBuilderTest extends AbstractQueryBuilderTest {

    @Test
    public void testLessThanFilter() throws Exception {
        LessThanFilter<Integer> filter = new LessThanFilter<>();
        filter.setValue(4);

        List<SampleEntity> result = QueryTestUtils.queryEntity(new LessThanQueryBuilder(), SampleEntity_.id, filter);
        assertEquals(3, result.size());
    }

    @Test
    public void testEmptyLessThanFilter() throws Exception {
        LessThanFilter<String> filter = new LessThanFilter<>();

        List<SampleEntity> result = QueryTestUtils.queryEntity(new LessThanQueryBuilder(), SampleEntity_.name, filter);
        assertEquals(6, result.size());
    }

    @Test
    public void testNonMatchingFilter() throws Exception {
        InFilter<String> filter = new InFilter<>();

        List<SampleEntity> result = QueryTestUtils.queryEntity(new LessThanQueryBuilder(), SampleEntity_.name, filter);
        assertEquals(6, result.size());
    }
}