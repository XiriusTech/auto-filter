package tech.xirius.filter.filtering;

import java.util.Arrays;
import java.util.List;

/**
 * Basic Filter with some common query conditions for a field.
 * In a REST/Web environment a petition using this filter could look like this:
 * <p>
 * <code>my/url?field1.equals=2&field1.in=2,4&field2.isNull=false</code>
 * </p>
 * However keep in mind that the petition structure can change depending on the
 * nesting levels of the filter, for example a filter of a field of a field of
 * the parent entity would look like:
 * <p>
 * <code>my/url?field3.subfield1.equals=2</code>
 * </p>
 * For more information on how to use a filter please refer to the full
 * documentation of the project
 */
public class BasicFilter<T> implements MultiFilter<T> {

    private EqualsFilter<T> equals = null;
    private InFilter<T> in = null;
    private IsNullFilter isNull = null;

    public T getEquals() {
        return FilterUtils.getWrapper(equals);
    }

    public void setEquals(T value) {
        this.equals = FilterUtils.setWrapper(equals, value, () -> new EqualsFilter<>());
    }

    public List<T> getIn() {
        return FilterUtils.getWrapper(in);
    }

    public void setIn(List<T> value) {
        this.in = FilterUtils.setWrapper(in, value, () -> new InFilter<>());
    }

    public Boolean getIsNull() {
        return FilterUtils.getWrapper(isNull);
    }

    public void setIsNull(Boolean value) {
        this.isNull = FilterUtils.setWrapper(isNull, value, () -> new IsNullFilter());
    }

    @Override
    public List<Filter> retrieveSubFilters() {
        return Arrays.asList(equals, in, isNull);
    }

}
