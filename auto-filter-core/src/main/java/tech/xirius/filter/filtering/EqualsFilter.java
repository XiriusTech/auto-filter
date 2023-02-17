package tech.xirius.filter.filtering;

/**
 * Filter for an <b>equality</b> condition
 * 
 * @author Camilo Narváez
 */
public class EqualsFilter<T> implements SingleFilter<T> {
    private T value = null;

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

}
