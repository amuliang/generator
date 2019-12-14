package org.mybatis.generator.sqlexample;

public class Criterion<TEntity> {
    private boolean hasValue;
    private String prefix;
    private Object value;
    private String suffix;

    public Criterion(String prefix, Object value, String suffix) {
        this.hasValue = value != null;
        this.prefix = prefix;
        this.value = value;
        this.suffix = suffix;
    }

    public boolean isHasValue() {
        return hasValue;
    }

    public String getPrefix() {
        return prefix;
    }

    public Object getValue() {
        return value;
    }

    public String getSuffix() {
        return suffix;
    }
}
