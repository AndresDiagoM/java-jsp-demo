import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class hola {
    
    public static void main(String[] args) {
        System.out.println("Hola Mundo");

        String url = "jdbc:mysql://localhost:3306/clientsdb";
        String dbuser = "root";
        String dbpassword = "";
        
        try {
            Connection myConn = DriverManager.getConnection(url, dbuser, dbpassword);
            Statement myStmt = myConn.createStatement();

            String sql = "SELECT * FROM clients";
            ResultSet rs = myStmt.executeQuery(sql);
            
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println(name);
                System.out.println(email);
            }
            
            rs.close();
            myStmt.close();
            myConn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
