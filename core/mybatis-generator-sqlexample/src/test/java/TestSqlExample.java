
import org.junit.Test;
import static org.junit.Assert.*;
import org.mybatis.generator.sqlexample.Criteria;
import org.mybatis.generator.sqlexample.Criterion;
import org.mybatis.generator.sqlexample.SqlExample;
import pojo.Brand;
import pojo.BrandQuery;

import java.util.List;

public class TestSqlExample {

    @Test
    public void testDistinct() {
        BrandQuery brandQuery = new BrandQuery();
        brandQuery.setDistinct(true);

        assertTrue(brandQuery.isDistinct());
    }

    @Test
    public void testFields() {
        BrandQuery brandQuery = new BrandQuery();
        brandQuery.setFields(Brand.ID, Brand.FIRST_CHAR.as("firstChar"));

        assertEquals("id, first_char as firstChar", brandQuery.getFields());
    }

    @Test
    public void testWhereWithCriterion() {
        BrandQuery brandQuery = new BrandQuery();
        brandQuery.where(Brand.ID.equal(1L));

        List<Criterion<Brand>> criterionList = brandQuery.getCriterionList();

        assertEquals(1, criterionList.size());

        assertEquals("id = ", criterionList.get(0).getPrefix());
        assertEquals(1L, criterionList.get(0).getValue());
    }

    @Test
    public void testWhereWithCriteria() {
        BrandQuery brandQuery = new BrandQuery();

        Criteria<Brand> criteria = new Criteria<>();
        criteria.and(Brand.NAME.like("%a%")).or(Brand.FIRST_CHAR.equal("A"));

        brandQuery.where(criteria);

        List<Criterion<Brand>> criterionList = brandQuery.getCriterionList();

        assertEquals(3, criterionList.size());

        assertEquals("name like ", criterionList.get(0).getPrefix());
        assertEquals("%a%", criterionList.get(0).getValue());

        assertEquals(" or ", criterionList.get(1).getPrefix());

        assertEquals("first_char = ", criterionList.get(2).getPrefix());
        assertEquals("A", criterionList.get(2).getValue());
    }

    @Test
    public void testWhereWithExpression() {
        SqlExample<Brand> brandQuery = new BrandQuery().where(c -> c
                .and(Brand.NAME.isNotNull())
                .and(Brand.FIRST_CHAR.isNotNull()));

        List<Criterion<Brand>> criterionList = brandQuery.getCriterionList();

        assertEquals("name is not null", criterionList.get(0).getPrefix());
        assertEquals(" and ", criterionList.get(1).getPrefix());
        assertEquals("first_char is not null", criterionList.get(2).getPrefix());
    }

    @Test
    public void testOrderBy() {
        BrandQuery brandQuery = new BrandQuery();
        brandQuery.orderBy(Brand.FIRST_CHAR.desc(), Brand.NAME.asc());

        assertEquals("first_char desc, name asc", brandQuery.getOrderByClause());
    }
}


