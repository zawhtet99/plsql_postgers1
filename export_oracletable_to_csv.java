import java.io.*;
import java.sql.*;
import java.util.*;
 
public class TestDBOracle {
 
    public static void main(String[] args) {
        generateCsvFile("c:\\test.csv");
 
    }
 
    private static void generateCsvFile(String filename) {
        DatabaseMetaData dbMetaData = null;
        String columnNameQuote = "";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@//hostname:portno/SID";
            Connection conn = DriverManager.getConnection(url, "username",
                    "pasword");
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt
                    .executeQuery("select * from table_name");
            ResultSetMetaData rsmd = rset.getMetaData();
            rset.next();
            FileWriter cname = new FileWriter("filename");
            System.out.println("No of columns in the table:"
                    + rsmd.getColumnCount());
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i) + " ");
                                
                   cname.append(rsmd.getColumnName(i)); cname.append(",");
                   cname.flush();
                 
            }
 
            System.out.println();
            while (rset.next()) {
                System.out.println(rset.getString(1) + " " + rset.getString(2)
                        + " " + rset.getString(3) + " " + rset.getString(4));
            }
            stmt.close();
 
        }
 
        catch (Exception e) {
            System.err.println("Unable to connect to database: " + e);
 
        }
 
    }
}
