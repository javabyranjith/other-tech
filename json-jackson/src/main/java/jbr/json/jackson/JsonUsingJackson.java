package jbr.json.jackson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUsingJackson {

  public static void main(String[] args) {
    Map<String, Object> map = new TreeMap<>();

    map.put("EmpId", "100");
    map.put("FirstName", "Ranjith");
    map.put("LastName", "Sekar");
    map.put("Address", "Chennai");

    List<String> mobNo = new ArrayList<String>();
    mobNo.add("9545454");
    mobNo.add("3434234");

    map.put("mobile", mobNo);

    convertCollectionToJson(map, "output/employee.json");

    System.out.println(convertMapToJsonString(map));
  }

  public static void convertCollectionToJson(Map map, String outputFile) {
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

  public static String convertMapToJsonString(Object map) {
    String output = "";
    try {
      output = new ObjectMapper().writeValueAsString(map);
    } catch (JsonGenerationException | JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return output;
  }

}
