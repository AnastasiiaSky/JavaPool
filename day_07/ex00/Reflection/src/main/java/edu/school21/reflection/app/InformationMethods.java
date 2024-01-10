package edu.school21.reflection.app;

import edu.school21.reflection.exceptions.NoClassFoundException;
import edu.school21.reflection.exceptions.NoSuchClassException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Class.forName;

public class InformationMethods {
    protected static Class<?> printAllClasses(Set<Class<?>> classes) {
        Class<?> currentClass = null;
        try {
            if (!classes.isEmpty()) {
                System.out.println("Classes:");
                for (Class<?> aClass : classes) {
                    System.out.println(aClass.getSimpleName());
                }
                System.out.println(Program.DIVIDER_TEMPLATE);
                currentClass = makeActionsAfterClassesPrint(classes);
            } else throw new NoClassFoundException();
        } catch (NoClassFoundException noClassFoundException) {
            noClassFoundException.printStackTrace();
        }
        return currentClass;
    }

    protected static Class<?> makeActionsAfterClassesPrint(Set<Class<?>> classes) {
        Class<?> currentClass = null;
        try {
            currentClass = chooseClass(classes);
        } catch (NoSuchClassException | ClassNotFoundException noSuchClassException) {
            System.out.println(noSuchClassException.getMessage());
            System.out.println(Program.DIVIDER_TEMPLATE);
            printAllClasses(classes);
        }
        return currentClass;
    }

    private static Class<?> chooseClass(Set<Class<?>> classes) throws NoSuchClassException, ClassNotFoundException {
        System.out.println("Enter class name:");
        String className = Program.sc.next();
        className = className.trim();
        if (checkClassName(classes, className)) {
            Class<?> currentClass = forName("edu.school21.reflection.models." + className);
            printClassFields(currentClass);
            printClassMethods(currentClass);
            System.out.println(Program.DIVIDER_TEMPLATE);
            return currentClass;
        } else throw new NoSuchClassException(String.format("This package doesn't contain " +
                "the class with name \"%s\"", className));
    }

    private static boolean checkClassName(Set<Class<?>> classes, String className) {
        for (Class<?> element : classes) {
            if (element.getSimpleName().equals(className)) return true;
        }
        return false;
    }

    private static void printClassFields(Class<?> currentClass) {
        System.out.println(Program.DIVIDER_TEMPLATE);
        System.out.println("fields:");
        Field[] fields = currentClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getType().getSimpleName();
            System.out.printf("\t%s %s%n", fieldName, field.getName());
        }
    }

    private static void printClassMethods(Class<?> currentClass) {
        System.out.println("methods:");
        Method[] methods = currentClass.getDeclaredMethods();
        for (Method method : methods) {
            if (!method.getDeclaringClass().equals(Object.class)) {
                String returnType = method.getReturnType().getSimpleName();
                if (returnType.equals("void")) returnType = "";
                System.out.printf("\t%s %s", returnType, method.getName());
                String parameters = Arrays.stream(method.getParameterTypes())
                        .map(Class::getSimpleName)
                        .collect(Collectors.joining(", "));
                System.out.printf("(%s)%n", parameters);
            }
        }
    }
}
