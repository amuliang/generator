package org.mybatis.generator.sqlexample;

public class OrderByItem<TEntity> {
    private String fieldName;
    private String sort;

    public OrderByItem(String fieldName, String sort) {
        this.fieldName = fieldName;
        this.sort = sort;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getSort() {
        return sort;
    }
}
