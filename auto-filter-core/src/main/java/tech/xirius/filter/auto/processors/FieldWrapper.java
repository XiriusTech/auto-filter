package tech.xirius.filter.auto.processors;

public class FieldWrapper {
    private String name;
    private ClassData type;
    private ClassData baseFilter;
    private String finalFilterString;
    private boolean isEnum;

    public FieldWrapper(String name, ClassData type, ClassData baseFilter, boolean isEnum) {
        this.baseFilter = baseFilter;
        this.name = name;
        this.type = type;
        this.isEnum = isEnum;
        this.finalFilterString = baseFilter.getSimpleClassName();
    }

    public FieldWrapper() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassData getType() {
        return type;
    }

    public void setType(ClassData type) {
        this.type = type;
    }

    public ClassData getBaseFilter() {
        return baseFilter;
    }

    public void setBaseFilter(ClassData filter) {
        this.baseFilter = filter;
    }

    public boolean isEnum() {
        return isEnum;
    }

    public void setEnum(boolean isEnum) {
        this.isEnum = isEnum;
    }

    public String getFinalFilterString() {
        return finalFilterString;
    }

    public void setFinalFilterString(String finalFilterString) {
        this.finalFilterString = finalFilterString;
    }

}