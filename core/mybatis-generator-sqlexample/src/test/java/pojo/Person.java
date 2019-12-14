package pojo;

import org.mybatis.generator.sqlexample.FieldBase;

public class Person {
    public static final FieldBase<Person, Integer> ID  = new FieldBase<Person, Integer>("id") {  };
    public static final FieldBase<Person, String> NAME  = new FieldBase<Person, String>("name") {  };
    public static final FieldBase<Person, Integer> AGE  = new FieldBase<Person, Integer>("age") {  };

    private Integer id;
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
