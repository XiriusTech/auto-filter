package tech.xirius.filter.filtering;

/**
 * Interface to define filters for a single field and a single condition, e.g. myField.equals=1
 */
public interface SingleFilter<T> extends Filter {
    T getValue();

    void setValue(T value);
}
