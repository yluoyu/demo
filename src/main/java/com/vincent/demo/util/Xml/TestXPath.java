package com.vincent.demo.util.Xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 测试XPath查找XML
 * XPath 是一门在 XML 文档中查找信息的语言， 可用来在 XML 文档中对元素和属性进行遍历。
 * XPath 是 W3C XSLT 标准的主要元素
 * 
 * @author vincent
 *
 */
public class TestXPath {

	public static void main(String[] args) {
		read();
	}
	
	public static void read() {  
        try {  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
            DocumentBuilder builder = dbf.newDocumentBuilder();  
            InputStream in = TestXPath.class.getClassLoader().getResourceAsStream("test.xml");  
            Document doc = builder.parse(in);  
            XPathFactory factory = XPathFactory.newInstance();  
            XPath xpath = factory.newXPath();  
            // 选取所有class元素的name属性   
            // XPath语法介绍： http://w3school.com.cn/xpath/   
            XPathExpression expr = xpath.compile("//class/@name");  
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);  
            for (int i = 0; i < nodes.getLength(); i++) {  
                System.out.println("name = " + nodes.item(i).getNodeValue());  
                    }  
        } catch (XPathExpressionException e) {  
            e.printStackTrace();  
        } catch (ParserConfigurationException e) {  
            e.printStackTrace();  
        } catch (SAXException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
