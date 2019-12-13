package org.mybatis.generator.sqlexample;

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

    public Criteria<TEntity> in(List<TField> list) {
        Criteria<TEntity> criteria = new Criteria<>();

        if(list == null || list.size() == 0) {
            return criteria;
        }

        criteria.getCriterionList().add(
                new Criterion<TEntity>(fieldName + " in(", null, ""));

        criteria.getCriterionList().add(
                new Criterion<TEntity>("", list.get(0), ""));

        for (int i = 1; i < list.size(); i++) {
            criteria.getCriterionList().add(
                    new Criterion<TEntity>(", ", list.get(i), ""));
        }

        criteria.getCriterionList().add(
                new Criterion<TEntity>("", null, ")"));

        return criteria;
    }

    public Criteria<TEntity> notIn(List<TField> list) {
        Criteria<TEntity> criteria = new Criteria<>();

        if(list == null || list.size() == 0) {
            return criteria;
        }

        criteria.getCriterionList().add(
                new Criterion<TEntity>(fieldName + " not in(", null, ""));

        criteria.getCriterionList().add(
                new Criterion<TEntity>("", list.get(0), ""));

        for (int i = 1; i < list.size(); i++) {
            criteria.getCriterionList().add(
                    new Criterion<TEntity>(", ", list.get(i), ""));
        }

        criteria.getCriterionList().add(
                new Criterion<TEntity>("", null, ")"));

        return criteria;
    }

    public Criteria<TEntity> between(TField value1, TField value2) {
        Criteria<TEntity> criteria = new Criteria<>();

        criteria.getCriterionList().add(
                new Criterion<TEntity>(fieldName + " between ", value1, ""));

        criteria.getCriterionList().add(
                new Criterion<TEntity>(" and ", value2, ""));

        return criteria;
    }

    public Criteria<TEntity> notBetween(TField value1, TField value2) {
        Criteria<TEntity> criteria = new Criteria<>();

        criteria.getCriterionList().add(
                new Criterion<TEntity>(fieldName + " not between ", value1, ""));

        criteria.getCriterionList().add(
                new Criterion<TEntity>(" and ", value2, ""));

        return criteria;
    }

    public Criterion<TEntity> like(TField value) {
        return new Criterion<TEntity>(fieldName + " like ", value, "");
    }

    public Criterion<TEntity> notLike(TField value) {
        return new Criterion<TEntity>(fieldName + " not like ", value, "");
    }

    public Criterion<TEntity> equal(FieldBase<TEntity, TField> field) {
        return new Criterion<TEntity>(fieldName + " = ", null, field.getFieldName());
    }

    public Criterion<TEntity> notEqual(FieldBase<TEntity, TField> field) {
        return new Criterion<TEntity>(fieldName + " <> ", null, field.getFieldName());
    }

    public Criterion<TEntity> greaterThan(FieldBase<TEntity, TField> field) {
        return new Criterion<TEntity>(fieldName + " > ", null, field.getFieldName());
    }

    public Criterion<TEntity> greaterAndEqualThan(FieldBase<TEntity, TField> field) {
        return new Criterion<TEntity>(fieldName + " >= ", null, field.getFieldName());
    }

    public Criterion<TEntity> lessThan(FieldBase<TEntity, TField> field) {
        return new Criterion<TEntity>(fieldName + " < ", null, field.getFieldName());
    }

    public Criterion<TEntity> lessAndEqualThan(FieldBase<TEntity, TField> field) {
        return new Criterion<TEntity>(fieldName + " <= ", null, field.getFieldName());
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

