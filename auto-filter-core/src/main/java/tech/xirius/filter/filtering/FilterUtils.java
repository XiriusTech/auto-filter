package tech.xirius.filter.filtering;

import java.util.function.Supplier;

public class FilterUtils {
    public static <T> T getWrapper(SingleFilter<T> filter) {
        return filter != null ? filter.getValue() : null;
    }

    public static <F extends SingleFilter<T>, T> F setWrapper(F filter, T value, Supplier<F> constructor) {
        if (filter == null)
            filter = constructor.get();
        filter.setValue(value);
        return (F) filter;
    }
}
