package dataAccesLayer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa AbstractDAO este utilizata pentru a realiza operatiile necesare asupra bazei
 * de date.
 * @param <T> una din clasele Client, Product sau CustomersOrder
 */

public abstract class AbstractDAO<T> {
    private final Class<T> type;

    public AbstractDAO()
    {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public int insert(T object)
    {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String query=QueryMaker.createInsertQuery(type);
        int result=0;
        int index=1;
        try
        {
            connection=ConnectionFactory.getConnection();
            preparedStatement=connection.prepareStatement(query);
            Object value;
            Field[] fields=type.getDeclaredFields();
            for(int i=1;i<fields.length;i++)
            {
                fields[i].setAccessible(true);
                value=fields[i].get(object);
                if(value instanceof Integer)
                    preparedStatement.setInt(index++,(Integer)value);
                if(value instanceof Double)
                    preparedStatement.setDouble(index++,(Double)value);
                if(value instanceof String)
                    preparedStatement.setString(index++,(String)value);
            }
            result=preparedStatement.executeUpdate();
        }
        catch (SQLException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(preparedStatement);
        }
        return result;
    }

    public int updateById(String field,Object value,int id)
    {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String query=QueryMaker.createUpdateQuery(type,field,"id");
        int result=0;
        try
        {
            connection=ConnectionFactory.getConnection();
            preparedStatement=connection.prepareStatement(query);
            if(value instanceof Integer)
                preparedStatement.setInt(1,(Integer)value);
            if(value instanceof Double)
                preparedStatement.setDouble(1,(Double)value);
            if(value instanceof String)
                preparedStatement.setString(1,(String)value);
            preparedStatement.setInt(2,id);
            result=preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(preparedStatement);
        }
        return result;
    }

    public int deleteById(int id)
    {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String query=QueryMaker.createDeleteQuery(type,"id");
        int result=0;
        try
        {
            connection=ConnectionFactory.getConnection();
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            result=preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(preparedStatement);
        }
        return result;
    }

    public T selectById(int id)
    {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query=QueryMaker.createSelectQuery(type,"id");
        try
        {
            connection=ConnectionFactory.getConnection();
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            List<T> objectsList=createObjects(resultSet);
            if(objectsList.size()==0)
                return null;
            else
                return objectsList.get(0);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(preparedStatement);
        }
        return null;
    }

    public List<T> selectAll()
    {
        Connection connection=null;
        Statement statement =null;
        ResultSet resultSet=null;
        String query=new String("SELECT * FROM ").concat(type.getSimpleName());
        try
        {
            connection=ConnectionFactory.getConnection();
            statement =connection.createStatement();
            resultSet= statement.executeQuery(query);
            return createObjects(resultSet);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor constructor = null;
        for (int i = 0; i < constructors.length; i++) {
            constructor = constructors[i];
            if (constructor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                constructor.setAccessible(true);
                T instance = (T) constructor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | SQLException | IntrospectionException | InvocationTargetException | IllegalArgumentException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

}
