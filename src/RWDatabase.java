import java.sql.*;

public class RWDatabase {
    public String connectionUrl = "jdbc:sqlserver://localhost:1433;integratedSecurity=true";
    // Account
    private String email;
    private String pass;
    private String name;
    private String postalCode;
    private String address;
    // Profile
    private String age;
    public boolean validated = false;

    public void makeConnection(String sqlCode) {
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);

            Statement statement = connection.createStatement();
//            System.out.println(sqlCode);
            ResultSet rs = statement.executeQuery(sqlCode);
                while (rs.next()) {


                    String email = rs.getString("Email");
                    String name = rs.getString("Name");
                    String address = rs.getString("Address");
                    String postalcode = rs.getString("PostalCode");
                    validated = true;
                    System.out.format("| %16s | %-24s | %-16s | %-24s |\n", email,name,address,postalcode);
                }  System.out.println(String.format("| %16s | %-24s | %-16s | %-24s |\n", " ", " ", " ", " ").replace(" ", "-"));
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
    public void setAccount(String email, String pass, String name, String address, String postalcode) {
        this.email = "'"+email+"'";
        this.pass = "'"+pass+"'";
        this.name = "'"+name+"'";
        this.address = "'"+address+"'";
        this.postalCode = "'"+postalcode+"'";

        String tempCode = "USE [Netflix Statistix Database];" +
                          "INSERT INTO Account " +
                          "VALUES ("+this.email+","+this.name+","+this.address+","+this.postalCode+","+this.pass+");";
        makeConnection(tempCode);
    }
    public void readAccount(String email, String pass) {
        this.email = "'"+email+"'";
        this.pass = "'"+pass+"'";

        String tempCode = "USE [Netflix Statistix Database];" +
                "SELECT * FROM Account " +
                "WHERE Email = "+this.email+" AND " +
                "Password = "+this.pass;
        makeConnection(tempCode);
    }

    public void delAccount(String email, String pass) {
        this.email = "'"+email+"'";
        this.pass = "'"+pass+"'";

        String tempCode = "USE [Netflix Statistix Database];" +
                "DELETE FROM Account " +
                "WHERE Email = "+this.email+" AND " +
                "WHERE Password = "+this.pass;
        makeConnection(tempCode);
    }
    public void requestPass(String name) {
        /* TODO */
    }

}
