package org.mybatis.generator.sqlexample;

import java.util.ArrayList;
import java.util.List;

public class Criteria<TEntity> {
    private List<Criterion<TEntity>> criterionList = new ArrayList<>();

    public Criteria() {

    }

    public Criteria(Criterion<TEntity> item) {
        and(item);
    }

    public Criteria(Criteria<TEntity> criteria) {
        and(criteria);
    }

    public List<Criterion<TEntity>> getCriterionList() {
        return criterionList;
    }

    public Criteria<TEntity> and(Criterion<TEntity> item) {
        List<Criterion<TEntity>> list = new ArrayList<>();
        list.add(item);
        append(list, " and ");
        return this;
    }

    public Criteria<TEntity> or(Criterion<TEntity> item) {
        List<Criterion<TEntity>> list = new ArrayList<>();
        list.add(item);
        append(list, " or ");
        return this;
    }

    public Criteria<TEntity> and(Criteria<TEntity> criteria) {
        append(criteria.getCriterionList(), " and ");
        return this;
    }
    public Criteria<TEntity> or(Criteria<TEntity> criteria) {
        append(criteria.getCriterionList(), " or ");
        return this;
    }

    private void append(List<Criterion<TEntity>> list, String join) {
        if(list.size() == 0) {
            return;
        }

        if(criterionList.size() > 0) {
            criterionList.add(new Criterion<TEntity>(join, null, ""));
        }

        if(list.size() > 1) {
            criterionList.add(new Criterion<TEntity>("(", null, ""));
        }

        criterionList.addAll(list);

        if(list.size() > 1) {
            criterionList.add(new Criterion<TEntity>(")", null, ""));
        }
    }
}

