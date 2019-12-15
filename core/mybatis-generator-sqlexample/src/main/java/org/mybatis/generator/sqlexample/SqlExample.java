package org.mybatis.generator.sqlexample;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SqlExample<TEntity> {

    private Criteria<TEntity> criteria;

    protected String orderByClause;

    protected boolean distinct;

    protected String fields;

    private List<Criterion<TEntity>> criterionList = new ArrayList<>();


    public SqlExample() {

    }

    public SqlExample(String entityAlias) {

    }

    private Criteria<TEntity> getCriteria() {
        if(criteria == null) {
            criteria = new Criteria<TEntity>();
        }
        return criteria;
    }

    private Criteria<TEntity> newCriteria() {
        criteria = new Criteria<TEntity>();
        return criteria;
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
        if(fields.length == 0) {
            return this;
        }

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

    @SafeVarargs
    public final SqlExample<TEntity> addFields(FieldBase<TEntity, ?>... fields) {
        if(fields.length == 0) {
            return this;
        }

        List<String> temp = new ArrayList<>();
        if(this.fields != null) temp.add(this.fields);

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
        this.criterionList = newCriteria().and(criteria).getCriterionList();
        return this;
    }

    public SqlExample<TEntity> where(List<Criterion<TEntity>> list) {
        this.criterionList = newCriteria().and(list).getCriterionList();
        return this;
    }

    public SqlExample<TEntity> where(Criterion<TEntity> criterion) {
        this.criterionList = newCriteria().and(criterion).getCriterionList();
        return this;
    }

    public SqlExample<TEntity> where(Function<Criteria<TEntity>, Criteria<TEntity>> func) {
        this.criterionList = newCriteria().and(func.apply(newCriteria())).getCriterionList();
        return this;
    }

    public SqlExample<TEntity> andWhere(Criteria<TEntity> criteria) {
        this.criterionList = getCriteria().and(criteria).getCriterionList();
        return this;
    }

    public SqlExample<TEntity> andWhere(List<Criterion<TEntity>> list) {
        this.criterionList = getCriteria().and(list).getCriterionList();
        return this;
    }

    public SqlExample<TEntity> andWhere(Criterion<TEntity> criterion) {
        this.criterionList = getCriteria().and(criterion).getCriterionList();
        return this;
    }

    public SqlExample<TEntity> andWhere(Function<Criteria<TEntity>, Criteria<TEntity>> func) {
        this.criterionList = getCriteria().and(func.apply(new Criteria<TEntity>())).getCriterionList();
        return this;
    }

    public SqlExample<TEntity> orWhere(Criteria<TEntity> criteria) {
        this.criterionList = getCriteria().or(criteria).getCriterionList();
        return this;
    }

    public SqlExample<TEntity> orWhere(List<Criterion<TEntity>> list) {
        this.criterionList = getCriteria().or(list).getCriterionList();
        return this;
    }

    public SqlExample<TEntity> orWhere(Criterion<TEntity> criterion) {
        this.criterionList = getCriteria().or(criterion).getCriterionList();
        return this;
    }

    public SqlExample<TEntity> orWhere(Function<Criteria<TEntity>, Criteria<TEntity>> func) {
        this.criterionList = getCriteria().or(func.apply(new Criteria<TEntity>())).getCriterionList();
        return this;
    }

    @SafeVarargs
    public final SqlExample<TEntity> orderBy(OrderByItem<TEntity>... items) {
        if(items.length == 0) {
            return this;
        }

        List<String> temp = new ArrayList<>();
        for (OrderByItem<TEntity> item : items) {
            temp.add(item.getFieldName() + " " + item.getSort());
        }
        this.orderByClause = String.join(", ", temp);
        return this;
    }

    @SafeVarargs
    public final SqlExample<TEntity> andOrderBy(OrderByItem<TEntity>... items) {
        if(items.length == 0) {
            return this;
        }

        List<String> temp = new ArrayList<>();
        if(this.orderByClause != null) {
            temp.add(orderByClause);
        }

        for (OrderByItem<TEntity> item : items) {
            temp.add(item.getFieldName() + " " + item.getSort());
        }
        this.orderByClause = String.join(", ", temp);
        return this;
    }
}