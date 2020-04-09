package rs.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQItem;
import javax.xml.xquery.XQSequence;

import com.saxonica.xqj.SaxonXQDataSource;

public class App {

  public static void main(String[] args) {
    try {
      execute();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (XQException e) {
      e.printStackTrace();
    }
  }

  private static void execute() throws FileNotFoundException, XQException {
    InputStream inputStream = new FileInputStream(new File("ex2/contri.xqy"));
    XQDataSource ds = new SaxonXQDataSource();
    XQConnection conn = ds.getConnection();
    /*
     * XQPreparedExpression exp = conn.prepareExpression(inputStream);
     * XQResultSequence result = exp.executeQuery();
     * 
     * while (result.next()) { System.out.println(result.getItemAsString(null));
     * }
     */

    XQExpression xqe = conn.createExpression();
    XQSequence xqs = xqe.executeQuery(inputStream);
    XQItem xqi = xqs.getItem();
    String st = xqi.getItemAsString(null);
    System.out.println(st);

  }
}
