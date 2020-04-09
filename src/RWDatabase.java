import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RWDatabase {
    public String connectionUrl = "jdbc:sqlserver://localhost:1433;integratedSecurity=true";
    // Account
    private String email;
    private String fullname;
    private String username;
    private String postalCode;
    private String address;
    private byte admin;
    // Profile
    private String age;
    public boolean validated = false;

    public void makeConnection(String sqlCode) {
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);

            Statement statement = connection.createStatement();
            System.out.println(sqlCode);
            ResultSet rs = statement.executeQuery(sqlCode);
            while (rs.next()) {
                email = rs.getString("Email");
                username = rs.getString("Username");
                fullname = rs.getString("FullName");
                address = rs.getString("Address");
                postalCode = rs.getString("postalCode");
                admin = rs.getByte("Admin");
                // If there is something in the list, it will return true
                validated = true;
                System.out.format("| %24s | %-24s | %-24s| %-24s | %-24s | %-2s |\n", email, fullname, username, address, postalCode, admin);
            }
            System.out.println(String.format("| %24s| %-24s | %-24s | %-24s | %-24s | %-2s |\n", " ", " ", " ", " ", " ", " ").replace(" ", "-"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void setAccount(String email, String username, String fullname, String address, String postalCode, String pass, byte admin) {
        String tempCode = "USE [Netflix Statistix Database];" +
                "INSERT INTO Account(Email,Username,Fullname,Address,Postalcode,Password,Admin) " +
                "VALUES (" +
                "'" + email + "'" +
                "," + "'" + username + "'" +
                "," + "'" + fullname + "'" +
                "," + "'" + address + "'" +
                "," + "'" + postalCode + "'" +
                "," + "'" + pass + "'" +
                "," + admin + ")";
        makeConnection(tempCode);
    }

    public void checkAccount(String email, String pass) {
        String tempCode = "USE [Netflix Statistix Database];" +
                "SELECT * FROM Account " +
                "WHERE Email = " + "'" + email + "'" + " AND " +
                "Password = " + "'" + pass + "'" + " OR " +
                "Username = " + "'" + email + "'" + " AND " +
                "Password = " + "'" + pass + "'";
        makeConnection(tempCode);
    }

    public byte checkAdmin(String email, String pass) {
        String tempCode = "USE [Netflix Statistix Database];" +
                "SELECT * FROM Account " +
                "WHERE Email = " + "'" + email + "'" + " AND " +
                "Password = " + "'" + pass + "'";
        makeConnection(tempCode);
        return admin;
    }

    public void delAccount(String position) {
        String tempCode = "USE [Netflix Statistix Database];" +
                "DELETE FROM Account " +
                "WHERE AccountID = " + "'" + position + "'";
        makeConnection(tempCode);
    }

    public void delSerie(String position) {
        String tempCode = "USE [Netflix Statistix Database];" +
                "DELETE FROM Series " +
                "WHERE ShowID = " + "'" + position + "'";
        makeConnection(tempCode);
    }

    public void delMovie(String position) {
        String tempCode = "USE [Netflix Statistix Database];" +
                "DELETE FROM Movies " +
                "WHERE MovieID = " + "'" + position + "'";
        makeConnection(tempCode);
    }

    public void delEpisode(String position) {
        String tempCode = "USE [Netflix Statistix Database];" +
                "DELETE FROM Episode " +
                "WHERE EpisodeID = " + "'" + position + "'";
        makeConnection(tempCode);
    }

    public void delProfile(String position) {
        String tempCode = "USE [Netflix Statistix Database];" +
                "DELETE FROM Profile " +
                "WHERE ProfileID = " + "'" + position + "'";
        makeConnection(tempCode);
    }

}
