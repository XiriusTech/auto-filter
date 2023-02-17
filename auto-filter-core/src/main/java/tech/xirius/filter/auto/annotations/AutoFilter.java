package tech.xirius.filter.auto.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.filtering.BasicFilter;

/**
 * Annotation that allows to automatically create a Filter from
 * the fields of a class.
 * The filter may then be used in a REST/Web environment to easily
 * create queries over multiple conditions for each field, such as:
 * <ul>
 * <li><b>Equal:</b> check if a field is equal to a value</li>
 * <li><b>In:</b> check if a field is in a set of values</li>
 * <li><b>IsNull:</b> check if a field is null or not</li>
 * </ul>
 * Currently only primitive (e.g. {@code int}), primitive wrappers (e.g.
 * {@link Integer}), BigDecimal and Strings are automatically detected
 * and added to a filter. Other elements must be annotated with
 * {@link AutoFilterInclude} to be included in the generated filter
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface AutoFilter {
    /**
     * The base filter to be used for each field of the class. The default
     * value is {@link BasicFilter}
     * 
     * @return the base filter for each the fields of the class
     */
    Class<? extends Filter> baseFilter() default BasicFilter.class;
}
