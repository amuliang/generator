package org.mybatis.generator.sqlexample;

import java.util.ArrayList;
import java.util.List;

public class SqlExample<TEntity> {

    protected String orderByClause;

    protected boolean distinct;

    protected String fields;

    private List<Criterion<TEntity>> criteria = new ArrayList<>();


    public SqlExample() {

    }

    public SqlExample(String entityAlias) {

    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public String getFields() {
        return fields;
    }

    public List<Criterion<TEntity>> getCriteria() {
        return criteria;
    }

    public SqlExample<TEntity> setDistinct(boolean value) {
        distinct = value;
        return this;
    }

    @SafeVarargs
    public final SqlExample<TEntity> setFields(FieldBase<TEntity, ?>... fields) {
        List<String> temp = new ArrayList<>();
        for (FieldBase<TEntity, ?> field : fields) {
            if(field.hasAlias()) {
                temp.add(field.getFieldName() + " as " + field.getAlias());
            }else {
                temp.add(field.getFieldName());
            }
        }
        this.fields = String.join(", ", temp);
        return this;
    }

    public SqlExample<TEntity> where(Criteria<TEntity> criteria) {
        this.criteria = criteria.getCriterionList();
        return this;
    }

    public SqlExample<TEntity> where(Criterion<TEntity> criterion) {
        where(new Criteria<TEntity>(criterion));
        return this;
    }

    @SafeVarargs
    public final SqlExample<TEntity> orderBy(OrderByItem<TEntity>... items) {
        List<String> temp = new ArrayList<>();
        for (OrderByItem<TEntity> field : items) {
            temp.add(field.getFieldName() + " " + field.getSort());
        }
        this.orderByClause = String.join(", ", temp);
        return this;
    }

}