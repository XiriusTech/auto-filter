package tech.xirius.filter.filtering;

public class GreaterThanFilter<T> implements SingleFilter<T> {
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