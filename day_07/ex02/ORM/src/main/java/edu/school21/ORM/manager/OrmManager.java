package edu.school21.ORM.manager;

import java.sql.*;
import java.util.List;
import java.util.Set;

import edu.school21.ORM.app.DataSourceConfiguration;

import javax.sql.DataSource;

public class OrmManager {
    private final AnnotationsWorker annotations;
    private final DataSource dataSource;

    private final Set<Class<?>> classes;

    public OrmManager() {
        annotations = new AnnotationsWorker();
        dataSource = DataSourceConfiguration.dataSource;
        classes = annotations.getClasses();
        tablesCreation();
    }


    public void save(Object entity) {
        Class<?> clazz = entity.getClass();
        String table = annotations.getOrmEntity(clazz).table();
        List<List<Object>> columnsData = annotations.getColumnsList(clazz);
        List<Object> data = annotations.getClassDataForSave(entity);
        try (Connection connection = dataSource.getConnection()) {
            String insertTemplate = String.format("INSERT INTO %s (%s, %s, %s) VALUES " +
                            "(\'%s\', \'%s\', \'%d\');", table, columnsData.get(0).get(0),
                    columnsData.get(1).get(0), columnsData.get(2).get(0), data.get(1),
                    data.get(2), data.get(3));
            PreparedStatement preparedStatement = connection.prepareStatement(insertTemplate);
            preparedStatement.executeUpdate();
            System.out.println(insertTemplate);
            String searchingIdTemplate = String.format("SELECT max(id) FROM %s", table);
            preparedStatement = connection.prepareStatement(searchingIdTemplate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) annotations.setEntityId(resultSet.getLong(1), entity);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void update(Object entity) {
        Class<?> clazz = entity.getClass();
        String table = annotations.getOrmEntity(clazz).table();
        List<List<Object>> columnsData = annotations.getColumnsList(clazz);
        List<Object> data = annotations.getClassDataForSave(entity);
        try (Connection connection = dataSource.getConnection()) {
            String updateTemplate = String.format("UPDATE %s SET %s = \'%s\', %s = \'%s\', %s = \'%d\' " +
                            "WHERE id = %d;", table, columnsData.get(0).get(0), data.get(1), columnsData.get(1).get(0),
                    data.get(2), columnsData.get(2).get(0), data.get(3), data.get(0));
            PreparedStatement statement = connection.prepareStatement(updateTemplate);
            statement.executeUpdate();
            System.out.println(updateTemplate);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public <T> T findById(Long id, Class<T> aClass) {
        try (Connection connection = dataSource.getConnection()) {
            String tableName = annotations.getOrmEntity(aClass).table();
            String query = String.format("SELECT * FROM %s WHERE id = %d;", tableName, id);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(2);
                String otherData = resultSet.getString(3);
                int value = resultSet.getInt(4);
                return annotations.objectCreating(aClass, id, name, otherData, value);
            }
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private void tablesCreation() {
        for (Class<?> element : classes) {
            String tableName = annotations.getOrmEntity(element).table();
            dropTable(tableName);
            List<List<Object>> columnsData = annotations.getColumnsList(element);
            createTable(tableName, columnsData);
        }
    }

    private void dropTable(String table) {
        String queryFormat = "DROP TABLE IF EXISTS %s CASCADE";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(String.format(queryFormat, table));
            statement.executeUpdate();
            System.out.println(String.format(queryFormat, table));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    private void createTable(String table, List<List<Object>> columnsData) {
        try (Connection connection = dataSource.getConnection()) {
            String query = String.format("CREATE TABLE IF  NOT EXISTS %s (%n" +
                            "id bigserial NOT NULL PRIMARY KEY,%n" +
                            "%s VARCHAR(%d) NOT NULL,%n" +
                            "%s VARCHAR(%d) NOT NULL,%n" +
                            "%s INT NOT NULL%n);", table,
                    columnsData.get(0).get(0), columnsData.get(0).get(1),
                    columnsData.get(1).get(0), columnsData.get(1).get(1),
                    columnsData.get(2).get(0), columnsData.get(2).get(1));
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}


