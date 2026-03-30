package com.iweb.reflection;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;


public class Demo08 {
    public static void main(String[] args) {
        //1. 创建xml解析器
        SAXReader reader = new SAXReader();
        //2. 创建xml文档对象
        try {
            Document document = reader.read("src/com/iweb/XML/data.xml");
            System.out.println("xml文档加载成功");
            //开始解析
            //1. 解析出根节点
            Element root = document.getRootElement();
            System.out.println("根节点名" + root.getName());
            //2. 用根节点解析出名为student的子节点
            List<Element> elementList = root.elements("student");
            System.out.println("[student字节的个数:]" + elementList.size());
            //3.遍历子节点的集合，解析每个子节点的信息
            for (Element element : elementList) {
                //读取节点的属性id
                String id = element.attributeValue("id");
                //读取字节点name中的文本
                String name = element.elementText("name");
                //读取子节点age中的文本
                String age = element.elementText("age");
                System.out.println("========================");
                System.out.println("[id]:" + id);
                System.out.println("[name]:" + name);
                System.out.println("[age]:" + age);
            }
        } catch (DocumentException e) {
            System.out.println("xml文档加载失败");
            throw new RuntimeException(e);
        }

    }
}
