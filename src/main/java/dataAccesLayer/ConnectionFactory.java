package dataAccesLayer;

import java.sql.*;

/**
 * Clasa ConnectionFactory este utilizata pentru a manipula conexiunea dintre baza de
 * date si aplicatia Java.
 */

public class ConnectionFactory {
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String DBURL="jdbc:mysql://localhost:3306/tpDatabase";
    private static final String USER="user";
    private static final String PASSWORD="1234";

    private static ConnectionFactory singleInstance=new ConnectionFactory();

    private ConnectionFactory()
    {
        try
        {
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private Connection createConnection()
    {
        try
        {
            return DriverManager.getConnection(DBURL,USER,PASSWORD);
        } catch (SQLException e)
        {
            return null;
        }
    }

    public static Connection getConnection()
    {
        return singleInstance.createConnection();
    }

    public static void close(Connection connection)
    {
        try
        {
            connection.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void close(Statement statement)
    {
        try
        {
            statement.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet)
    {
        try
        {
            resultSet.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }





}
