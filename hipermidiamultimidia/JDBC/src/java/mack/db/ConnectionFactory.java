package mack.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo Bernardelli
 */
public class ConnectionFactory {

    private static Connection connection;

    static {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        } catch (SQLException exception) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, exception);
        } catch (ClassNotFoundException exception) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    public static Connection getConnection(){
        if(connection == null)
            try {
              Class.forName("org.apache.derby.jdbc.ClientDriver");
              connection = DriverManager.getConnection("jdbc:mysql//localhost:1527/sample", "app", "app");
          } catch (SQLException exception) {
              Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, exception);
          } catch (ClassNotFoundException exception) {
              Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, exception);
          }
        return connection;
    }

    public static void main(String args[]){
        ConnectionFactory.registerUser("admin", "password");
    }

    public static boolean userExists(String username){
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            int counter = 0;
            while(result.next()){
                counter++;
            }

            return (counter == 1) ? true: false;
        } catch (SQLException exception) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, exception);
        }
        return false;
    }

    public static boolean authenticate(String username, String password){
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            int counter = 0;
            while(result.next()){
                counter++;
            }

            return (counter == 1) ? true: false;
        } catch (SQLException exception) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, exception);
        }
        return false;
    }

    public static void registerUser(String username, String password){
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?,?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
}
