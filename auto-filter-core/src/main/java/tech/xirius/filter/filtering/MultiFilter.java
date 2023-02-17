package tech.xirius.filter.filtering;

import java.util.List;

/**
 * Interface to define filters for a single field and multiple conditions, e.g.
 * myField.equals=1&myField.in=1,2&myField.isNull=false
 */
public interface MultiFilter<T> extends Filter {
    /**
     * Retrieves the list of filters that conform the {@code MultiFilter}. For example
     * if a filter has the following structure:
     *  <pre>
     *  public class MyFilter<T> implements MultiFilter<T> {
     *    private EqualsFilter<T> equals = null;
     *    private InFilter<T> in = null;
     *    private IsNullFilter isNull = null;
     *    ...
     *  }
     * 
     * </pre>
     * Then this method implementation can be:
     * <pre>
     * public List<Filter> retrieveSubFilters() {
     *    return Arrays.asList(equals, in, isNull);
     * }
     * </pre>
     * 
     * @return the list of subfilters
     */
    List<Filter> retrieveSubFilters();
}
