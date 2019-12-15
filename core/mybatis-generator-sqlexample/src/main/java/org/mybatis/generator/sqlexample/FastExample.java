package org.mybatis.generator.sqlexample;

import java.util.List;
import java.util.function.Function;

public class FastExample {
    @SafeVarargs
    public static <TEntity> SqlExample<TEntity> fields(FieldBase<TEntity, ?>... fields) {
        return new SqlExample<TEntity>().setFields(fields);
    }

    @SafeVarargs
    public static <TEntity> SqlExample<TEntity> orderBy(OrderByItem<TEntity>... items) {
        return new SqlExample<TEntity>().orderBy(items);
    }

    public static <TEntity> SqlExample<TEntity> where(Criteria<TEntity> criteria) {
        return new SqlExample<TEntity>().where(criteria);
    }

    public static <TEntity> SqlExample<TEntity> where(List<Criterion<TEntity>> list) {
        return new SqlExample<TEntity>().where(list);
    }

    public static <TEntity> SqlExample<TEntity> where(Criterion<TEntity> criterion) {
        return new SqlExample<TEntity>().where(criterion);
    }

    public static <TEntity> SqlExample<TEntity> where(Function<Criteria<TEntity>, Criteria<TEntity>> func) {
        return new SqlExample<TEntity>().where(func);
    }
}
