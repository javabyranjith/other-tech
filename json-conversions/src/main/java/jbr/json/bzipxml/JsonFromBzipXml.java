package jbr.json.bzipxml;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JsonFromBzipXml {

  public static FileInputStream fis;
  public static DocumentBuilderFactory docBuilderFactory;
  public static DocumentBuilder docBuilder;
  public static Document xmlDocument;
  public static XPath xpathObj;

  public void loadConfig(File inputFile) {
    try {
      fis = new FileInputStream(inputFile);
      docBuilderFactory = DocumentBuilderFactory.newInstance();
      docBuilder = docBuilderFactory.newDocumentBuilder();
      xmlDocument = docBuilder.parse(fis);
      xpathObj = XPathFactory.newInstance().newXPath();
    } catch (IOException | ParserConfigurationException | SAXException e) {
      e.printStackTrace();
    }
  }

  /**
   * Start the Json Conversion
   * 
   * @param args
   * @throws IOException
   * @throws CompressorException
   */
  public static void main(String[] args) throws IOException, CompressorException, XPathExpressionException {
    JsonFromBzipXml converter = new JsonFromBzipXml();
    converter.generateJson();
  }

  public void generateJson() throws XPathExpressionException {
    Map<String, List<Map<String, String>>> employees = new TreeMap<>();
    Map<String, String> employee = null;

    File xmlFile = null;
    FileInputStream fileInputStream = null;
    BufferedInputStream bufferedInputStream = null;
    CompressorInputStream compressorInputStream = null;
    BufferedReader bufferedReader = null;

    try {
      File[] bzipFiles = new File(JsonConstants.INPUT_BZIP_DIRECTORY).listFiles();

      for (File bzipFile : bzipFiles) {

        List<Map<String, String>> empList = new ArrayList<>();

        // Unzip bzip Files
        fileInputStream = new FileInputStream(bzipFile);
        bufferedInputStream = new BufferedInputStream(fileInputStream);
        compressorInputStream = new CompressorStreamFactory().createCompressorInputStream(bufferedInputStream);
        bufferedReader = new BufferedReader(new InputStreamReader(compressorInputStream));

        String line = null;

        // System.out.println(bzipFile);

        while ((line = bufferedReader.readLine()) != null) {
          employee = new TreeMap<>();
          xmlFile = new File(bzipFile.getName().replace(".bz2", ""));
          xmlFile.createNewFile();
          FileOutputStream fileOutputStream = new FileOutputStream(xmlFile);
          fileOutputStream.write(line.getBytes());
          // System.out.println(line);

          // Load the xml file
          loadConfig(xmlFile);

          System.out.println("\n===Retrieve all Elements of an Employee====");
          String xpath = "/Employees/Employee";
          System.out.println("xpath: " + xpath);
          Node node = (Node) xpathObj.compile(xpath).evaluate(xmlDocument, XPathConstants.NODE);
          if (null != node) {
            NodeList nodeList = node.getChildNodes();

            for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
              Node subNode = nodeList.item(i);
              if (subNode.getNodeType() == Node.ELEMENT_NODE)
                System.out.println(nodeList.item(i).getNodeName() + " : " + subNode.getFirstChild().getNodeValue());
              employee.put(nodeList.item(i).getNodeName(), subNode.getFirstChild().getNodeValue());
            }
          }

          fileOutputStream.close();
        } // end while

        empList.add(employee);
        employees.put("employees", empList);
        toJsonFromMap(employees, JsonConstants.OUTPUT_JSON_DIRECTORY + xmlFile.getName().replace(".xml", ".json"));

      } // end for

    } catch (IOException ioException) {
      ioException.printStackTrace();
    } catch (CompressorException ce) {
      ce.printStackTrace();
    }
  }

  public static void toJsonFromMap(Map map, String outputFile) {
    try {
      new ObjectMapper().writeValue(new File(outputFile), map);
    } catch (JsonGenerationException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String toJsonFromList1(List list) {
    String value = "";
    try {
      value = new ObjectMapper().writeValueAsString(list);
    } catch (JsonGenerationException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return value;
  }

  public interface JsonConstants {
    String INPUT_BZIP_DIRECTORY = "input/bzip";
    String OUTPUT_JSON_DIRECTORY = "output/bzip-jsons/";
  }
}
