package edu.school21.ORM.manager;

import edu.school21.ORM.annotations.OrmColumn;
import edu.school21.ORM.annotations.OrmEntity;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnnotationsWorker {
    private static final String PACKAGE = "edu.school21.ORM.models";
    Set<Class<?>> classes;

    public AnnotationsWorker() {
        findClassesSet();
    }

    private void findClassesSet() {
        Reflections reflections = new Reflections(PACKAGE, Scanners.SubTypes.filterResultsBy(s -> true));
        this.classes = reflections.getSubTypesOf(Object.class);
    }

    protected Set<Class<?>> getClasses() {
        return this.classes;
    }

    protected OrmEntity getOrmEntity(Class<?> clazz) {
        return clazz.getAnnotation(OrmEntity.class);
    }

    protected List<List<Object>> getColumnsList(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<List<Object>> columnsData = new ArrayList<>();
        for (Field field : fields) {
            if (!field.getName().equals("id")) {
                List<Object> fieldData = new ArrayList<>();
                fieldData.add(field.getAnnotation(OrmColumn.class).name());
                fieldData.add(field.getAnnotation(OrmColumn.class).length());
                columnsData.add(fieldData);
            }
        }
        return columnsData;
    }

    protected void setEntityId(Long id, Object entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                if (field.getName().equals("id")) {
                    field.setAccessible(true);
                    field.set(entity, id);
                }
            }
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
    }

    protected <T> T objectCreating(Class<T> aClass, Long id, String name, String other, int value) {
        try {
            T instance = aClass.getDeclaredConstructor(Long.class, String.class, String.class, Integer.class)
                    .newInstance(id, name, other, value);
            return instance;
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException | InstantiationException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    protected List<Object> getClassDataForSave(Object entity) {
        List<Object> data = new ArrayList<>();
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                data.add(field.get(entity));
            }
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
        return data;
    }
}
