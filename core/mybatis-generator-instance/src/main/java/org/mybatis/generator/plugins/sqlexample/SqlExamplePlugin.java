package org.mybatis.generator.plugins.sqlexample;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.kotlin.KotlinFile;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.MergeConstants;

import java.util.*;
import java.util.regex.Matcher;

public class SqlExamplePlugin extends PluginAdapter {
    @Override
    public void initialized(IntrospectedTable introspectedTable) {

    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // 添加引用
        topLevelClass.addImportedType("org.mybatis.generator.sqlexample.FieldBase");
        // 为了添加到类内部，选择第一个字段添加
        List<Field> fields = topLevelClass.getFields();
        if(fields.size() == 0) return true;
        Field field = fields.get(0);
        // 添加注释
        field.addJavaDocLine("// 静态字段，供SqlExample生成sql语句使用");
        // 添加静态字段
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        for (IntrospectedColumn column : allColumns) {
            String fieldString = String.format("public static final FieldBase<%s, %s> %s  = new FieldBase<%s, %s>(\"%s\") {  };",
                    topLevelClass.getType().getShortName(),
                    column.getFullyQualifiedJavaType().getShortName(),
                    column.getActualColumnName().toUpperCase(),
                    topLevelClass.getType().getShortName(),
                    column.getFullyQualifiedJavaType().getShortName(),
                    MyBatis3FormattingUtilities.getAliasedActualColumnName(column));

            field.addJavaDocLine(fieldString);
        }
        // 添加空行
        field.addJavaDocLine("\n");

        return true;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // 清空内容
        topLevelClass.getFields().clear();
        topLevelClass.getMethods().clear();
        topLevelClass.getInnerClasses().clear();
        // 继承父类
        topLevelClass.addImportedType("org.mybatis.generator.sqlexample.SqlExample");
        String classTypeName = topLevelClass.getType().getShortName();
        topLevelClass.setSuperClass(String.format(
                "SqlExample<%s>",
                introspectedTable.getBaseRecordType()));

        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {

        // 将所有原query参数换成SqlExample

        interfaze.addImportedType(new FullyQualifiedJavaType("org.mybatis.generator.sqlexample.SqlExample"));

        List<Method> methods = interfaze.getMethods();

        for (Method method : methods) {
            List<Parameter> parameters = method.getParameters();
            for (int i = 0; i < parameters.size(); i++) {
                Parameter parameter = parameters.get(i);
                String testType = parameter.getType().getFullyQualifiedName();
                String exampleType = introspectedTable.getExampleType();

                if(testType.equals(exampleType)) {
                    List<String> annotations = parameter.getAnnotations();
                    parameters.remove(parameter);

                    String sqlExampleType = String.format(
                            "org.mybatis.generator.sqlexample.SqlExample<%s>",
                            introspectedTable.getBaseRecordType());

                    Parameter example = new Parameter(
                            new FullyQualifiedJavaType(sqlExampleType),
                            "example");
                    for (String annotation : annotations) {
                        example.addAnnotation(annotation);
                    }
                    parameters.add(i, example);
                }

            }
        }

        return true;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        // TODO 我们废除原来的where子句处理，换成SqlExample的where子句处理方法

        List<VisitableElement> elements = document.getRootElement().getElements();

        for (VisitableElement element : elements) {
            XmlElement elem = (XmlElement) element;

            if(elem.getName() == "sql") {

                if(testElement(elem, "id", "Example_Where_Clause")) {
                    XmlElement where = (XmlElement)elem.getElements().get(0);
                    where.getElements().clear();

                    XmlElement forEach = new XmlElement("foreach");
                    forEach.addAttribute(new Attribute("collection", "criterionList"));
                    forEach.addAttribute(new Attribute("item", "criterion"));

                    forEach.addElement(new TextElement("${criterion.prefix}"));

                    XmlElement hasValueElem = new XmlElement("if");
                    hasValueElem.addAttribute(new Attribute("test", "criterion.hasValue"));
                    hasValueElem.addElement(new TextElement("#{criterion.value}"));
                    forEach.addElement(hasValueElem);

                    forEach.addElement(new TextElement("${criterion.suffix}"));

                    where.addElement(forEach);

                }else if(testElement(elem, "id", "Update_By_Example_Where_Clause")) {
                    XmlElement where = (XmlElement)elem.getElements().get(0);
                    where.getElements().clear();

                    XmlElement forEach = new XmlElement("foreach");
                    forEach.addAttribute(new Attribute("collection", "example.criterionList"));
                    forEach.addAttribute(new Attribute("item", "criterion"));

                    forEach.addElement(new TextElement("${criterion.prefix}"));

                    XmlElement hasValueElem = new XmlElement("if");
                    hasValueElem.addAttribute(new Attribute("test", "criterion.hasValue"));
                    hasValueElem.addElement(new TextElement("#{criterion.value}"));
                    forEach.addElement(hasValueElem);

                    forEach.addElement(new TextElement("${criterion.suffix}"));

                    where.addElement(forEach);
                }
            }
            else {
                List<Attribute> attributes = elem.getAttributes();
                for (int i = 0; i < attributes.size(); i++) {
                    Attribute attribute = attributes.get(i);
                    if(attribute.getName().equals("parameterType") && attribute.getValue().equals(introspectedTable.getExampleType())) {
                        attributes.remove(attribute);
                        attributes.add(new Attribute("parameterType", "org.mybatis.generator.sqlexample.SqlExample"));
                    }
                }
            }

            // 解决生成重复问题，是由于未删除旧元素导致的，我们保证这次生成的元素对于下一次来说是旧元素
            elem.addElement(new TextElement("<!-- ELEMENT FOR GENERATOR MERGE - "
                    + MergeConstants.NEW_ELEMENT_TAG + " -->"));

        }


        return true;
    }

    private boolean testElement(XmlElement element, String key, String value) {
        List<Attribute> attributes = element.getAttributes();
        for (Attribute attribute : attributes) {
            if(attribute.getName().equals(key) && attribute.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
