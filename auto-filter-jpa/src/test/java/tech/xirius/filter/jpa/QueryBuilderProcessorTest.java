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
package tech.xirius.filter.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import tech.xirius.filter.filtering.BasicFilter;
import tech.xirius.filter.filtering.RangeFilter;
import tech.xirius.filter.jpa.utils.QueryTestUtils;
import tech.xirius.filter.jpa.utils.SampleEntity;
import tech.xirius.filter.jpa.utils.SampleEntityFilter;

/**
 * Tests for {@link QueryBuilderProcessor}
 */
@RunWith(JUnit4.class)
public class QueryBuilderProcessorTest extends AbstractQueryBuilderTest {

    @Test
    public void testEmptyFilter() throws Exception {
        SampleEntityFilter filter = new SampleEntityFilter();
        List<SampleEntity> result = QueryTestUtils.queryEntity(filter);
        assertEquals(6, result.size());
    }

    @Test
    public void testSingleConditionFilter() throws Exception {
        SampleEntityFilter filter = new SampleEntityFilter();

        BasicFilter<String> nameFilter = new BasicFilter<>();
        nameFilter.setEquals("mark");
        filter.setName(nameFilter);

        List<SampleEntity> result = QueryTestUtils.queryEntity(filter);
        assertEquals(2, result.size());
    }

    @Test
    public void testMultipleFieldsFilter() throws Exception {
        SampleEntityFilter filter = new SampleEntityFilter();

        BasicFilter<String> nameFilter = new BasicFilter<>();
        nameFilter.setEquals("mark");
        filter.setName(nameFilter);

        RangeFilter<Integer> idFilter = new RangeFilter<>();
        idFilter.setEquals(1);
        filter.setId(idFilter);

        List<SampleEntity> result = QueryTestUtils.queryEntity(filter);
        assertEquals(1, result.size());
    }

    @Test
    public void testMultipleConditionsFilter() throws Exception {
        SampleEntityFilter filter = new SampleEntityFilter();

        RangeFilter<Integer> idFilter = new RangeFilter<>();
        idFilter.setIn(Arrays.asList(1, 2, 3, 4, 5));
        idFilter.setGreaterThan(3);
        filter.setId(idFilter);

        List<SampleEntity> result = QueryTestUtils.queryEntity(filter);
        assertEquals(2, result.size());
    }
}
