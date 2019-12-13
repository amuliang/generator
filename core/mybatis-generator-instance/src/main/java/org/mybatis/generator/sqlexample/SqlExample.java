package org.mybatis.generator.sqlexample;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SqlExample<TEntity> {

    protected String orderByClause;

    protected boolean distinct;

    protected String fields;

    private List<Criterion<TEntity>> criterionList = new ArrayList<>();


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

    public List<Criterion<TEntity>> getCriterionList() {
        return criterionList;
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
        this.criterionList = criteria.getCriterionList();
        return this;
    }

    public SqlExample<TEntity> where(List<Criterion<TEntity>> list) {
        where(new Criteria<TEntity>(list));
        return this;
    }

    public SqlExample<TEntity> where(Criterion<TEntity> criterion) {
        where(new Criteria<TEntity>(criterion));
        return this;
    }

    public SqlExample<TEntity> where(Function<Criteria<TEntity>, Criteria<TEntity>> func) {
        where(func.apply(new Criteria<TEntity>()));
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