package tech.xirius.filter.auto.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import tech.xirius.filter.filtering.Filter;

/**
 * This annotation indicates that a field must be included
 * in the generated filter of an element annotated with
 * {@link AutoFilter}
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface AutoFilterInclude {
    /**
     * The base filter to be used for the annotated field. This overrides
     * the value of {@link AutoFilter#baseFilter()}
     * 
     * @return the base filter of the field
     */
    Class<? extends Filter> baseFilter();
}
