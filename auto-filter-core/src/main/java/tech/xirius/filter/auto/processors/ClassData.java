package tech.xirius.filter.auto.processors;

public class ClassData {
    private String fullClassName;
    private String packageName;
    private String simpleClassName;
    private Integer genericTypeCount;

    public ClassData(String fullClassName) {
        genericTypeCount = calculateGenericTypeCount(fullClassName);
        this.fullClassName = fullClassName.replaceAll("<.*>", "");
        int lastDot = getLastDot(fullClassName);
        this.packageName = lastDot > 0 ? this.fullClassName.substring(0, lastDot) : null;
        this.simpleClassName = lastDot > 0 ? this.fullClassName.substring(lastDot + 1) : fullClassName;
    }

    private int calculateGenericTypeCount(String fullClassName) {
        return fullClassName.split("<|,").length - 1;
    }

    private int getLastDot(String className) {
        return className.lastIndexOf('.');
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    public void setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }

    public Integer getGenericTypeCount() {
        return genericTypeCount;
    }

    public void setGenericTypeCount(Integer genericTypeCount) {
        this.genericTypeCount = genericTypeCount;
    }

}
