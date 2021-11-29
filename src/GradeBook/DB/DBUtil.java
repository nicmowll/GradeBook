/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 2
 * Date: 11/29/2021
 */
package GradeBook.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static Connection connection;
    
    private DBUtil() {}

    //gains a connection to a DB with an inputted username and password
    public static synchronized Connection getConnection(String username, String password) {
        if (connection != null) {
            return connection;
        } else {
            try {
                // set the db url, username, and password
                String url = "jdbc:mysql://myawsdb.cno2zewghfii.us-east-1.rds.amazonaws.com";
                //String myUsername = "ctadmin"; used for testing
                //String myPassword = "Nicolas12345Benny$"; used for testing
                
                // get and return connection
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
                return connection;
              } catch (SQLException | ClassNotFoundException e) {
            	  System.out.println("\nError: Could not retrieve connection, username or password could be wrong.");
            	  return null;
              }
        }

    }

    //closes the connection
    public static synchronized void closeConnection() throws SQLException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                connection = null;
            }
        }
    }
}
