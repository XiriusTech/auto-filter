package tech.xirius.filter.jpa;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import tech.xirius.filter.jpa.utils.QueryTestUtils;

public abstract class AbstractQueryBuilderTest {
    @BeforeClass
    public static void insertData() throws Exception {
        QueryTestUtils.insertData();
    }

    @AfterClass
    public static void deleteData() throws Exception {
        QueryTestUtils.deleteData();
    }

}
