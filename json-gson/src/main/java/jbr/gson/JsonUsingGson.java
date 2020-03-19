package jbr.gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;

public class JsonUsingGson {

  public static void main(String[] args) {
    convertCollectionToJson();
    convertObjectToJson();
  }

  public static void convertCollectionToJson() {
    Map<String, Object> map = new TreeMap<>();

    map.put("EmpId", "100");
    map.put("FirstName", "Ranjith");
    map.put("LastName", "Sekar");
    map.put("Address", "Chennai");

    List<String> mobNo = new ArrayList<String>();
    mobNo.add("9545454");
    mobNo.add("3434234");

    map.put("mobile", mobNo);

    Gson gson = new Gson();
    System.out.println(gson.toJson(map));
  }

  public static void convertObjectToJson() {

    Employee employee = new Employee();

    employee.setEmpId("100");
    employee.setFirstName("Ranjith");
    employee.setLastName("Sekar");
    employee.setAddress(new Address("Nehru street", "123", "chennai", "tamilnadu", "india"));

    Gson gson = new Gson();
    System.out.println(gson.toJson(employee));
  }

}
