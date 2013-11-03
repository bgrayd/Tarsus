import java.sql.*;

public class DBConnections
{
    private static DBConnections pool = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;
 
    private DBConnections()
    {
        try
        {
            String driver = "com.mysql.jdbc.Driver";

            username = "jjc52";
            password = "xyz123";
            url = "jdbc:mysql://localhost/" + username;

            Class.forName(driver);
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("JDBC Driver not found");
            e.printStackTrace(System.err);
        }
    }

    public static DBConnections getInstance()
    {
        if (pool == null)
            pool = new DBConnections();
        return pool;
    }

    public Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection(url, username, password);
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace(System.err);
            return null;
        }
    }
    
    public void freeConnection(Connection c)
    {
        try
        {
            c.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace(System.err);
        }
    }
}