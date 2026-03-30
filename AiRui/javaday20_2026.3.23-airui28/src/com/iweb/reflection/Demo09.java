package com.iweb.reflection;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Demo09 {

    static final String PATH = "src/com/iweb/XML/data.xml";

    //创建一个学生对象
    public static void main(String[] args) {
        //对象中存储数据，通过内部类不需要setter和getter
        Student student = new Student();
        student.id = 8;
        student.name = "张三";
        student.id = 22;
        //把stu对象插入到xml文档对象里面，作为一个student节点
        addStudentToXML(student);
    }
    //1. 创建解析器

    static void addStudentToXML(Student student) {
        SAXReader reader = new SAXReader();
        //2.解析得到xml文档
        Document document = null;
        try {
            document = reader.read(PATH);
            //解析根节点（）
            Element root = document.getRootElement();
            //在根节点下插入一堆名为“student”的子节点
            Element element = root.addElement("student");
            System.out.println("子节点添加成功");
            //给子节点设置数据
            element.addAttribute("id", student.id + "");
            element.addAttribute("name", student.name + "");
            element.addAttribute("age", student.id + "");
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    //默认是咋i内存中添加节点，如果没有缓存xml文件，则xml文件不会更新
    static void savexml(Document document) {
        //指定输出的字符集格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        try (FileOutputStream fileOutputStream = new FileOutputStream(PATH);
             OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);) {
            //不支持自动close，写在try大括号里面
            XMLWriter xmlWriter = new XMLWriter(writer, format);
            xmlWriter.write(document);
            xmlWriter.flush();
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //对应xml结构的Student类
    static class Student {
        Integer id;
        String name;
        Integer age;
    }
}