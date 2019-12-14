package org.mybatis.generator.sqlexample;

import java.util.ArrayList;
import java.util.List;

public abstract class FieldBase<TEntity, TField> {
    private String alias;
    private String fieldName;

    public FieldBase(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean hasAlias() {
        return alias != null;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Criterion<TEntity> equal(TField value) {
        return new Criterion<TEntity>(fieldName + " = ", value, "");
    }

    public Criterion<TEntity> notEqual(TField value) {
        return new Criterion<TEntity>(fieldName + " <> ", value, "");
    }

    public Criterion<TEntity> greaterThan(TField value) {
        return new Criterion<TEntity>(fieldName + " > ", value, "");
    }

    public Criterion<TEntity> greaterAndEqualThan(TField value) {
        return new Criterion<TEntity>(fieldName + " >= ", value, "");
    }

    public Criterion<TEntity> lessThan(TField value) {
        return new Criterion<TEntity>(fieldName + " < ", value, "");
    }

    public Criterion<TEntity> lessAndEqualThan(TField value) {
        return new Criterion<TEntity>(fieldName + " <= ", value, "");
    }

    public Criterion<TEntity> isNull() {
        return new Criterion<TEntity>(fieldName + " is null", null, "");
    }

    public Criterion<TEntity> isNotNull() {
        return new Criterion<TEntity>(fieldName + " is not null", null, "");
    }

    public List<Criterion<TEntity>> in(List<TField> list) {
        if(list == null || list.size() == 0) {
            throw new RuntimeException("in操作接收的参数没有值");
        }

        List<Criterion<TEntity>> criterionList = new ArrayList<>();

        criterionList.add(new Criterion<TEntity>(fieldName + " in (", list.get(0), ""));

        for (int i = 1; i < list.size(); i++) {
            criterionList.add(new Criterion<TEntity>(", ", list.get(i), ""));
        }

        criterionList.add(new Criterion<TEntity>("", null, ")"));

        return criterionList;
    }

    public List<Criterion<TEntity>> notIn(List<TField> list) {
        if(list == null || list.size() == 0) {
            throw new RuntimeException("not in操作接收的参数没有值");
        }

        List<Criterion<TEntity>> criterionList = new ArrayList<>();

        criterionList.add(new Criterion<TEntity>(fieldName + " not in (", list.get(0), ""));

        for (int i = 1; i < list.size(); i++) {
            criterionList.add(new Criterion<TEntity>(", ", list.get(i), ""));
        }

        criterionList.add(new Criterion<TEntity>("", null, ")"));

        return criterionList;
    }

    public List<Criterion<TEntity>> between(TField value1, TField value2) {
        List<Criterion<TEntity>> criterionList = new ArrayList<>();

        criterionList.add(new Criterion<TEntity>(fieldName + " between ", value1, ""));

        criterionList.add(new Criterion<TEntity>(" and ", value2, ""));

        return criterionList;
    }

    public List<Criterion<TEntity>> notBetween(TField value1, TField value2) {
        List<Criterion<TEntity>> criterionList = new ArrayList<>();

        criterionList.add(new Criterion<TEntity>(fieldName + " not between ", value1, ""));

        criterionList.add(new Criterion<TEntity>(" and ", value2, ""));

        return criterionList;
    }

    public Criterion<TEntity> like(String value) {
        return new Criterion<TEntity>(fieldName + " like ", value, "");
    }

    public Criterion<TEntity> notLike(String value) {
        return new Criterion<TEntity>(fieldName + " not like ", value, "");
    }

    public Criterion<TEntity> equal(FieldBase<TEntity, TField> field) {
        return new Criterion<TEntity>(fieldName + " = " + field.getFieldName(), null, "");
    }

    public Criterion<TEntity> notEqual(FieldBase<TEntity, TField> field) {
        return new Criterion<TEntity>(fieldName + " <> " + field.getFieldName(), null, "");
    }

    public Criterion<TEntity> greaterThan(FieldBase<TEntity, TField> field) {
        return new Criterion<TEntity>(fieldName + " > " + field.getFieldName(), null, "");
    }

    public Criterion<TEntity> greaterAndEqualThan(FieldBase<TEntity, TField> field) {
        return new Criterion<TEntity>(fieldName + " >= " + field.getFieldName(), null, "");
    }

    public Criterion<TEntity> lessThan(FieldBase<TEntity, TField> field) {
        return new Criterion<TEntity>(fieldName + " < " + field.getFieldName(), null, "");
    }

    public Criterion<TEntity> lessAndEqualThan(FieldBase<TEntity, TField> field) {
        return new Criterion<TEntity>(fieldName + " <= " + field.getFieldName(), null, "");
    }



    public OrderByItem<TEntity> asc() {
        OrderByItem<TEntity> item = new OrderByItem<>(fieldName, "asc");
        return item;
    }

    public OrderByItem<TEntity> desc() {
        OrderByItem<TEntity> item = new OrderByItem<>(fieldName, "desc");
        return item;
    }

    public FieldBase<TEntity, TField> as(String alias) {
        FieldBase<TEntity, TField> result = new FieldBase<TEntity, TField>(fieldName) {};
        result.alias = alias;
        return result;
    }

    public String getAlias() {
        return alias;
    }
}

