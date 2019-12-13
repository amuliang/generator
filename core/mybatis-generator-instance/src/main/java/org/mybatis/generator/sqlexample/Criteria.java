package org.mybatis.generator.sqlexample;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Criteria<TEntity> {
    public static <T> Criteria<T> create(Criterion<T> item) {
        return new Criteria<T>(item);
    }

    public static <T> Criteria<T> create(List<Criterion<T>> list) {
        return new Criteria<T>(list);
    }

    public static <T> Criteria<T> create(Criteria<T> criteria) {
        return new Criteria<T>(criteria);
    }

    private int actualCriterionConut;
    private List<Criterion<TEntity>> criterionList = new ArrayList<>();

    public Criteria() {

    }

    public Criteria(Criterion<TEntity> item) {
        and(item);
    }

    public Criteria(List<Criterion<TEntity>> list) {
        and(list);
    }

    public Criteria(Criteria<TEntity> criteria) {
        and(criteria);
    }

    public Criteria(Function<Criteria<TEntity>, Criteria<TEntity>> func) {
        and(func.apply(new Criteria<TEntity>()));
    }

    public int getActualCriterionConut() {
        return actualCriterionConut;
    }

    public List<Criterion<TEntity>> getCriterionList() {
        return criterionList;
    }

    public Criteria<TEntity> and(Criterion<TEntity> item) {
        List<Criterion<TEntity>> list = new ArrayList<>();
        list.add(item);
        append(list, " and ", 1);
        return this;
    }

    public Criteria<TEntity> or(Criterion<TEntity> item) {
        List<Criterion<TEntity>> list = new ArrayList<>();
        list.add(item);
        append(list, " or ", 1);
        return this;
    }

    public Criteria<TEntity> and(List<Criterion<TEntity>> list) {
        append(list, " and ", 1);
        return this;
    }

    public Criteria<TEntity> or(List<Criterion<TEntity>> list) {
        append(list, " or ", 1);
        return this;
    }

    public Criteria<TEntity> and(Criteria<TEntity> criteria) {
        append(criteria.getCriterionList(), " and ", criteria.getActualCriterionConut());
        return this;
    }
    public Criteria<TEntity> or(Criteria<TEntity> criteria) {
        append(criteria.getCriterionList(), " or ", criteria.getActualCriterionConut());
        return this;
    }

    public Criteria<TEntity> and(Function<Criteria<TEntity>, Criteria<TEntity>> func) {
        Criteria<TEntity> criteria = func.apply(new Criteria<TEntity>());
        and(criteria);
        return this;
    }

    public Criteria<TEntity> or(Function<Criteria<TEntity>, Criteria<TEntity>> func) {
        Criteria<TEntity> criteria = func.apply(new Criteria<TEntity>());
        or(criteria);
        return this;
    }

    private void append(List<Criterion<TEntity>> list, String join, int actualCriterionConut) {
        if(list.size() == 0) {
            return;
        }

        if(criterionList.size() > 0) {
            criterionList.add(new Criterion<TEntity>(join, null, ""));
        }

        if(actualCriterionConut > 1) {
            criterionList.add(new Criterion<TEntity>("(", null, ""));
        }

        criterionList.addAll(list);

        if(actualCriterionConut > 1) {
            criterionList.add(new Criterion<TEntity>(")", null, ""));
        }

        this.actualCriterionConut++;
    }
}

