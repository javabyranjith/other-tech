package jbr.jsoup;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupDemo {
  public static void main(String[] args) throws Exception {
    JsoupDemo app = new JsoupDemo();
    app.getHtmlSource();
  }

  public void getHtmlSource() throws Exception {
    // String src = Jsoup.connect("http://localhost:6060/login").get().html();
    String src = Jsoup.connect("https://jsoup.org/cookbook/extracting-data/attributes-text-html").get().html();
    System.out.println(src);
  }

  public void parseHtmlFile() throws Exception {
    Document doc = Jsoup.parse(new File("product.html"), "utf-8");
    String title = doc.title();
    String body = doc.body().text();
    Element div = doc.getElementById("prodDiv");

    System.out.println("Title: " + title);
    System.out.println("Body: " + body);
    System.out.println("Div: " + div.text());
  }

  public void parseString() {
    String htmlString = "<html><head><title>This is title</title></head><body> This is Body content</body></html>";
    Document doc = Jsoup.parse(htmlString);

    String title = doc.title();
    String body = doc.body().text();

    System.out.println("Title: " + title);
    System.out.println("Body: " + body);
  }
}
