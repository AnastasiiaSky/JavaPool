package edu.school21.ORM.manager;

import java.sql.*;
import java.util.List;
import java.util.Set;

import edu.school21.ORM.annotations.OrmEntity;
import edu.school21.ORM.app.DataSourceConfiguration;
import edu.school21.ORM.models.Cat;
import edu.school21.ORM.models.User;


import javax.sql.DataSource;

public class OrmManager {
    private static final String SELECT_TEMPLATE = "SELECT * from %s;\n";
    private AnnotationsWorker annotations;
    private final DataSource dataSource;

    private final Set<Class<?>> classes;
    public OrmManager() {
        annotations = new AnnotationsWorker();
        dataSource = DataSourceConfiguration.dataSource;
        classes = annotations.getClasses();
        tablesCreation();
    }


    public void save(Object entity) {
        String insertTemplate = "INSERT INTO %s (%s, %s, %s) VALUES (\'%s\', \'%s\', \'%d\');";
        Class<?> clazz = entity.getClass();
        String table = annotations.getOrmEntity(clazz).table();
        List<List<Object>> columnsData =  annotations.getColumnsList(clazz);
        List<Object> data = annotations.getClassDataForSave(entity);
        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format(insertTemplate, table,
                    columnsData.get(0).get(0), columnsData.get(1).get(0), columnsData.get(2).get(0),
                    data.get(1), data.get(2), data.get(3)));
            System.out.println(String.format(insertTemplate, table,
                    columnsData.get(0).get(0), columnsData.get(1).get(0), columnsData.get(2).get(0),
                    data.get(1), data.get(2), data.get(3)));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void update(Object entity) {
        String updateTemplate = "UPDATE %s SET %s = \'%s\', %s = \'%s\', %s = \'%d\' WHERE id = %d";
        Class<?> clazz = entity.getClass();
        String table = annotations.getOrmEntity(clazz).table();
        List<List<Object>> columnsData =  annotations.getColumnsList(clazz);
        List<Object> data = annotations.getClassDataForSave(entity);
        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format(updateTemplate, table,
                    columnsData.get(0).get(0), data.get(1),
                    columnsData.get(1).get(0), data.get(2),
                    columnsData.get(2).get(0), data.get(3),
                    data.get(0)));
            System.out.println(String.format(updateTemplate, table,
                    columnsData.get(0).get(0), data.get(1),
                    columnsData.get(1).get(0), data.get(2),
                    columnsData.get(2).get(0), data.get(3),
                    data.get(0)));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public <T> T findById(Long id, Class<T> aClass) {
        String tableName = annotations.getOrmEntity(aClass).table();
        String query = "SELECT * FROM %s + WHERE id = %d;";
        Long id1 = 0L;
        String str1 = "";
        String str2 = "";
        int val = 0;

        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(query, tableName, id));
            while (resultSet.next()) {
                id1 = resultSet.getLong(1);
                str1 = resultSet.getString(2);
                str2 = resultSet.getString(3);
                val = resultSet.getInt(4);
                Object
                Object created = new Object(id1, str1, str2, val);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // если сущность не найдена
    }


    private void tablesCreation() {
        for(Class element : classes) {
            String tableName = annotations.getOrmEntity(element).table();
            dropTable(tableName);
            List<List<Object>> columnsData =  annotations.getColumnsList(element);
            createTable(tableName, columnsData);
        }
    }

    private void dropTable(String table) {
        String queryFormat = "DROP TABLE IF EXISTS %s CASCADE";
        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format(queryFormat, table));
            System.out.println(String.format(queryFormat, table));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    private void createTable(String table, List<List<Object>> columnsData) {
        String queryFormat = "CREATE TABLE IF  NOT EXISTS %s (%n" +
                "id bigserial NOT NULL PRIMARY KEY,%n" +
                "%s VARCHAR(%d) NOT NULL,%n" +
                "%s VARCHAR(%d) NOT NULL,%n" +
                "%s INT NOT NULL%n);";
        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format(queryFormat, table,
                    columnsData.get(0).get(0), columnsData.get(0).get(1),
                    columnsData.get(1).get(0), columnsData.get(1).get(1),
                    columnsData.get(2).get(0), columnsData.get(2).get(1)));
            System.out.println(String.format(queryFormat, table,
                    columnsData.get(0).get(0), columnsData.get(0).get(1),
                    columnsData.get(1).get(0), columnsData.get(1).get(1),
                    columnsData.get(2).get(0), columnsData.get(2).get(1)));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}


