package jbr.json.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlXpath2Json {
  public static void main(String[] args) {

    try {
      FileInputStream fis = new FileInputStream(new File("input/Employees.xml"));
      DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
      Document xmlDocument = docBuilder.parse(fis);
      XPath xPathObj = XPathFactory.newInstance().newXPath();

      System.out.println("===Retrieve Email Id of one employee===");
      String xpath = "/Employees/Employee[@empId='100']/email";
      System.out.println("xpath: " + xpath);
      String email = xPathObj.compile(xpath).evaluate(xmlDocument);
      System.out.println(email);

      System.out.println("\n===Retrieve all Employees firstname====");
      xpath = "/Employees/Employee/firstname";
      System.out.println("xpath: " + xpath);
      NodeList nodeList = (NodeList) xPathObj.compile(xpath).evaluate(xmlDocument, XPathConstants.NODESET);
      for (int i = 0; i < nodeList.getLength(); i++) {
        System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
      }

     /* System.out.println("\n===Retrieve all Employees lastname====");
      xpath = "/Employees/Employee/lastname";
      System.out.println("xpath: " + xpath);
      nodeList = (NodeList) xPathObj.compile(xpath).evaluate(xmlDocument, XPathConstants.NODESET);
      for (int i = 0; i < nodeList.getLength(); i++) {
        System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
      }

      System.out.println("\n===Retrieve all Admin's or Manager's firstname====");
      xpath = "/Employees/Employee[@type='admin' or @type='manager']/firstname";
      System.out.println("xpath: " + xpath);
      nodeList = (NodeList) xPathObj.compile(xpath).evaluate(xmlDocument, XPathConstants.NODESET);
      for (int i = 0; i < nodeList.getLength(); i++) {
        System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
      }

      System.out.println("\n===Retrieve all Elements of an Employee====");
      xpath = "/Employees/Employee[@empId='300']";
      System.out.println("xpath: " + xpath);
      Node node = (Node) xPathObj.compile(xpath).evaluate(xmlDocument, XPathConstants.NODE);
      if (null != node) {
        nodeList = node.getChildNodes();

        for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
          Node subNode = nodeList.item(i);
          if (subNode.getNodeType() == Node.ELEMENT_NODE)
            System.out.println(nodeList.item(i).getNodeName() + " : " + subNode.getFirstChild().getNodeValue());
        }
      }

      System.out.println("\n===Retrieve all Employee whose age is over 40====");
      xpath = "/Employees/Employee[age>40]/firstname";
      nodeList = (NodeList) xPathObj.compile(xpath).evaluate(xmlDocument, XPathConstants.NODESET);
      System.out.println("xpath: " + xpath);
      for (int i = 0; i < nodeList.getLength(); i++) {
        System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
      }

      System.out.println("\n===Retrieve first Employee====");
      xpath = "/Employees/Employee[1]/firstname";
      System.out.println("xpath: " + xpath);
      nodeList = (NodeList) xPathObj.compile(xpath).evaluate(xmlDocument, XPathConstants.NODESET);
      for (int i = 0; i < nodeList.getLength(); i++) {
        System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
      }

      System.out.println("\n===Retrieve first 2 employees's firstname====");
      xpath = "/Employees/Employee[position() <= 2]/firstname";
      System.out.println("xpath: " + xpath);
      nodeList = (NodeList) xPathObj.compile(xpath).evaluate(xmlDocument, XPathConstants.NODESET);
      for (int i = 0; i < nodeList.getLength(); i++) {
        System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
      }

      System.out.println("\n===Retrieve last employees's firstname====");
      xpath = "/Employees/Employee[last()]/firstname";
      System.out.println("xpath: " + xpath);
      nodeList = (NodeList) xPathObj.compile(xpath).evaluate(xmlDocument, XPathConstants.NODESET);
      for (int i = 0; i < nodeList.getLength(); i++) {
        System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
      }

      // And Condition on attributes
      System.out.println("\n===Retrieve firstname of employee whose emp id=100 and type is admin====");
      xpath = "/Employees/Employee[@empId='100' and @type='admin']/firstname";
      System.out.println("xpath: " + xpath);
      String firstName = xPathObj.compile(xpath).evaluate(xmlDocument);
      System.out.println(firstName);

      // Retrieve based on attribute name (way 1)
      System.out.println("\n===Retrieve employee who is specialist====");
      xpath = "/Employees/Employee";
      System.out.println("xpath: " + xpath);
      NodeList employeeList = (NodeList) xPathObj.compile(xpath).evaluate(xmlDocument, XPathConstants.NODESET);

      for (int i = 0; i < employeeList.getLength(); i++) {
        Node empElement = employeeList.item(i);

        NodeList childNodes = employeeList.item(i).getChildNodes();

        if (empElement.getAttributes().getNamedItem("specialist") != null) {
          for (int j = 0; null != childNodes && j < childNodes.getLength(); j++) {
            Node subNode = childNodes.item(j);
            if (subNode.getNodeType() == Node.ELEMENT_NODE)
              System.out.println(childNodes.item(j).getNodeName() + " : " + subNode.getFirstChild().getNodeValue());
          }
        }
      }
      // Retrieve based on attribute name (way 2)
      System.out.println("\n===Retrieve employee who is specialist====");
      xpath = "//Employee[@specialist='false']/email"; // "/Employees/Employee[@specialist and @specialist='true']/email";
      System.out.println("xpath: " + xpath);
      email = xPathObj.compile(xpath).evaluate(xmlDocument);
      System.out.println(email);*/


      System.out.println("\n===Retrieve employee who is specialist====");
      //xpath = "//firstname[@lang='english' and @native='north']"; // "/Employees/Employee[@specialist and @specialist='true']/email";
      //xpath = "//*[name()='firstname']";
      //xpath = "//firstname";
      xpath = "//firstname[@lang='english']";
      System.out.println("xpath: " + xpath);
      email = xPathObj.compile(xpath).evaluate(xmlDocument);
      System.out.println(email);
      
    } catch (IOException | SAXException | ParserConfigurationException | XPathExpressionException e) {
      e.printStackTrace();
    }
  }
}
