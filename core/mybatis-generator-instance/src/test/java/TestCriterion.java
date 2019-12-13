import org.junit.Test;
import org.mybatis.generator.sqlexample.Criteria;
import org.mybatis.generator.sqlexample.Criterion;
import pojo.Brand;
import pojo.Person;

import java.sql.PseudoColumnUsage;
import java.util.List;

import static org.junit.Assert.*;

public class TestCriterion {

    @Test
    public void testWithFieldValue() {
        Criterion<Brand> criterion = Brand.NAME.equal(Brand.FIRST_CHAR);

        assertEquals("name = first_char", criterion.getPrefix());
    }

    @Test
    public void testBetween() {
        List<Criterion<Person>> criterionList = Person.AGE.between(10, 20);

        assertEquals("age between ", criterionList.get(0).getPrefix());
        assertEquals(10, criterionList.get(0).getValue());

        assertEquals(" and ", criterionList.get(1).getPrefix());
        assertEquals(20, criterionList.get(1).getValue());
    }

    @Test
    public void testNestingCondition() {
        Criteria<Person> criteria = new Criteria<Person>()
                .and(c -> c
                        .and(Person.AGE.equal(13))
                        .or(Person.NAME.like("%abc%")))
                .or(c -> c
                        .and(Person.AGE.equal(13))
                        .or(Person.NAME.like("%abc%")));

        assertEquals(2, criteria.getActualCriterionConut());

        List<Criterion<Person>> criterionList = criteria.getCriterionList();

        assertEquals("(", criterionList.get(0).getPrefix());

        assertEquals("age = ", criterionList.get(1).getPrefix());
        assertEquals(13, criterionList.get(1).getValue());

        assertEquals(" or ", criterionList.get(2).getPrefix());

        assertEquals("name like ", criterionList.get(3).getPrefix());
        assertEquals("%abc%", criterionList.get(3).getValue());

        assertEquals(")", criterionList.get(4).getPrefix());

        assertEquals(" or ", criterionList.get(5).getPrefix());

        assertEquals("(", criterionList.get(6).getPrefix());

        assertEquals("age = ", criterionList.get(7).getPrefix());
        assertEquals(13, criterionList.get(7).getValue());

        assertEquals(" or ", criterionList.get(8).getPrefix());

        assertEquals("name like ", criterionList.get(9).getPrefix());
        assertEquals("%abc%", criterionList.get(9).getValue());

        assertEquals(")", criterionList.get(10).getPrefix());
    }
}
