package tech.xirius.filter.auto.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation indicates that a field must be excluded
 * from the generated filter of an element annotated with
 * {@link AutoFilter}
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface AutoFilterExclude {

}

