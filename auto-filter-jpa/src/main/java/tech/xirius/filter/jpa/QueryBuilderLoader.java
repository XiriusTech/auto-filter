package tech.xirius.filter.jpa;

import java.util.ServiceLoader;

public class QueryBuilderLoader {
    public static void loadQueryBuilders() {
        ServiceLoader.load(QueryBuilder.class).forEach(QueryBuilderProcessor::registerQueryBuilder);
    }
}
