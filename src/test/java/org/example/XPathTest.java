package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

public class XPathTest {

    private Document xmlDocument;
    private XPath xPath;

    @Before
    public void init() throws ParserConfigurationException, IOException, SAXException {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("inventory.xml");
        Assert.assertNotNull(inputStream);

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        xmlDocument = builder.parse(inputStream);
        xPath = XPathFactory.newInstance().newXPath();
    }

    @Test
    public void testSimpleXPatchExpression() throws XPathExpressionException {
        // See https://howtodoinjava.com/java/xml/java-xpath-tutorial-example/
        String expression = "//book/title/text()";

        NodeList nodes = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        Assert.assertEquals(3, nodes.getLength());
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }
    }

    @Test
    public void testSimpleOperatorXPatchExpression() throws XPathExpressionException {
        // See https://howtodoinjava.com/java/xml/java-xpath-tutorial-example/
        String expression = "//book[price<8]/title/text()";

        NodeList nodes = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        Assert.assertEquals(2, nodes.getLength());
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }
    }

    @Test
    public void testStartWithXPatchExpression() throws XPathExpressionException {
        // See https://howtodoinjava.com/java/xml/java-xpath-tutorial-example/
        String expression = "//book[author='Neal Stephenson']/title/text()";

        NodeList nodes = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        Assert.assertEquals(2, nodes.getLength());
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }
    }
}
