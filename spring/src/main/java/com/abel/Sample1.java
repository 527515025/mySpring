package com.abel;

/**
 * Created by yangyibo on 17/7/5.
 */

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.util.*;


public class Sample1 {
    public static void main(String[] args) throws Exception {
        SAXBuilder sb = new SAXBuilder();
//        Document doc = sb.build("src/main/resources/test.xml"); //构造文档对象
        Document doc = sb.build(Sample1.class.getClassLoader().getResourceAsStream("test.xml")); //构造文档对象
        Element root = doc.getRootElement(); //获取根元素
        List list = root.getChildren("disk");//取名字为disk的所有元素
        for (int i = 0; i < list.size(); i++) {
            Element element = (Element) list.get(i);
            String name = element.getAttributeValue("name");
            String capacity = element.getChildText("capacity");//取disk子元素capacity的内容
            String directories = element.getChildText("directories");
            String files = element.getChildText("files");
            System.out.println("磁盘信息:");
            System.out.println("分区盘符:" + name);
            System.out.println("分区容量:" + capacity);
            System.out.println("目录数:" + directories);
            System.out.println("文件数:" + files);
            System.out.println("-----------------------------------");
        }
    }
}