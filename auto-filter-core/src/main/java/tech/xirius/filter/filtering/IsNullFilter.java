package tech.xirius.filter.filtering;

public class IsNullFilter implements SingleFilter<Boolean> {
    private Boolean value = null;

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

}