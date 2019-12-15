
import org.junit.Test;
import org.mybatis.generator.sqlexample.Criterion;
import org.mybatis.generator.sqlexample.FastExample;
import org.mybatis.generator.sqlexample.SqlExample;
import pojo.Brand;
import pojo.Person;

import java.util.List;

import static org.junit.Assert.*;

public class TestFastExample {
    @Test
    public void testFields() {
        SqlExample<Brand> example = FastExample.fields(Brand.ID, Brand.NAME);

        assertEquals("id, name", example.getFields());
    }

    @Test
    public void testWhere() {
        SqlExample<Person> example = FastExample
                .where(Person.AGE.greaterAndEqualThan(10))
                .andWhere(Person.AGE.lessAndEqualThan(20));

        List<Criterion<Person>> criterionList = example.getCriterionList();

        assertEquals(3, criterionList.size());

        assertEquals("age >= ", criterionList.get(0).getPrefix());
        assertEquals(10, criterionList.get(0).getValue());
        assertEquals(" and ", criterionList.get(1).getPrefix());
        assertEquals("age <= ", criterionList.get(2).getPrefix());
        assertEquals(20, criterionList.get(2).getValue());
    }

    @Test
    public void testOrderBy() {
        SqlExample<Person> example = FastExample.orderBy(Person.AGE.desc());

        assertEquals("age desc", example.getOrderByClause());
    }
}
