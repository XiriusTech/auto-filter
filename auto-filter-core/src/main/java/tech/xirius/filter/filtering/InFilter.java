package tech.xirius.filter.filtering;

import java.util.List;

public class InFilter<T> implements SingleFilter<List<T>> {
    private List<T> value = null;

    @Override
    public List<T> getValue() {
        return value;
    }

    @Override
    public void setValue(List<T> value) {
        this.value = value;
    }

}