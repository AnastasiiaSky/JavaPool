package edu.school21.ORM.manager;

import edu.school21.ORM.annotations.OrmColumn;
import edu.school21.ORM.annotations.OrmColumnId;
import edu.school21.ORM.annotations.OrmEntity;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
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
        for(Field field : fields) {
            if(!field.getName().equals("id") && !field.getName().equals("idGenerator")) {
                List<Object> fieldData = new ArrayList<>();
                fieldData.add(field.getAnnotation(OrmColumn.class).name());
                fieldData.add(field.getAnnotation(OrmColumn.class).length());
                columnsData.add(fieldData);
            }
        }
        return columnsData;
    }

    protected List<Object> getClassDataForSave(Object entity) {
        List<Object> data = new ArrayList<>();
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try{
            for (Field field : fields) {
                if(!field.getName().equals("idGenerator")) {
                    field.setAccessible(true);
                    data.add(field.get(entity));
                }
            }
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
        return data;
    }
}
