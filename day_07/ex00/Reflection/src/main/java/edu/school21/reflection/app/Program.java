package edu.school21.reflection.app;

import edu.school21.reflection.exceptions.NoClassFoundException;
import edu.school21.reflection.exceptions.NoSuchClassException;
import edu.school21.reflection.models.Car;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Class.forName;


public class Program {
    private static final String PACKAGE = "edu.school21.reflection.models";

    public static void main(String[] args) {
        Reflections reflections = new Reflections(PACKAGE, Scanners.SubTypes.filterResultsBy(s -> true));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
        printAllClasses(classes);
    }

    private static void objectCreating(Class currentClass) {
        System.out.println("Let’s create an object.");
        Field[] fields = currentClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName() + ":");

        }

    }

    private static void printClassMethods(Class currentClass) {
        System.out.println("methods:");
        Method[] methods = currentClass.getDeclaredMethods();
        for(Method method : methods) {
            if (!method.getDeclaringClass().equals(Object.class)) {
                String returnType = method.getReturnType().getSimpleName();
                if(returnType.equals("void")) returnType = "";
                System.out.printf("\t%s %s", returnType, method.getName());
                String parameters = Arrays.stream(method.getParameterTypes())
                        .map(Class::getSimpleName)
                        .collect(Collectors.joining(", "));
                System.out.printf("(%s)\n", parameters);
            }
        }

    }

    private static void printClassFields(Class currentClass) {
        System.out.println("---------------------");
        System.out.println("fields:");
        Field[] fields = currentClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getType().getSimpleName();
            System.out.printf("\t%s %s\n", fieldName, field.getName());
        }
    }

    private static void chooseClass(Set<Class<?>> classes) throws NoSuchClassException, ClassNotFoundException {
        System.out.println("Enter class name:");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String className = reader.readLine();
            className = className.trim();
            if(checkClassName(classes, className)) {
                Class currentClass = forName("edu.school21.reflection.models." + className);
               printClassFields(currentClass);
               printClassMethods(currentClass);
               System.out.println("---------------------");
                objectCreating(currentClass);
            } else throw new NoSuchClassException(String.format("This package doesn't contain " +
                    "the class with name \"%s\"", className));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static boolean checkClassName(Set<Class<?>> classes, String className) {
        for (Class element : classes) {
            if(element.getSimpleName().equals(className)) return true;
        }
        return false;
    }

    private static void makeActionsAfterClassesPrint(Set<Class<?>> classes) {
        try {
            chooseClass(classes);
        } catch (NoSuchClassException | ClassNotFoundException noSuchClassException) {
            System.out.println(noSuchClassException.toString());
            System.out.println("---------------------");
            printAllClasses(classes);
        }
    }

    private static void printAllClasses(Set<Class<?>> classes) {
        try {
            if (classes.size() != 0) {
                System.out.println("Classes:");
                for (Class<?> aClass : classes) {
                    System.out.println(aClass.getSimpleName());
                }
                System.out.println("---------------------");
                makeActionsAfterClassesPrint(classes);
            } else throw new NoClassFoundException();
        } catch (NoClassFoundException noClassFoundException) {
            noClassFoundException.printStackTrace();
        }
    }
}
