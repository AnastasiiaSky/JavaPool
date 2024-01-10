package edu.school21.reflection.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ActionMethods {
    protected static Object objectCreating(Class<?> currentClass) {
        Object newObject = null;
        System.out.println("Let’s create an object.");
        Constructor<?>[] constructors = currentClass.getConstructors();
        Field[] fields = currentClass.getDeclaredFields();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] paramTypes = constructor.getParameterTypes();
            if (paramTypes.length != 0) {
                Object[] arguments = getObjectFields(paramTypes, fields);
                try {
                    newObject = constructor.newInstance(arguments);
                    System.out.println("\nObject created:" + newObject);
                    System.out.println(Program.DIVIDER_TEMPLATE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return newObject;
    }

    private static Object[] getObjectFields(Class<?>[] paramTypes, Field[] fields) {
        Object[] arguments = new Object[paramTypes.length];
        for (int it = 0; it < paramTypes.length; ++it) {
            System.out.println(fields[it].getName() + ":");
            String type = paramTypes[it].getSimpleName();
            arguments[it] = getArgument(type);
        }
        return arguments;
    }

    private static Object getArgument(String type) {
        Object argument = null;
        if (type.equals("Integer") || type.equals("int")) {
            if (Program.sc.hasNextInt()) argument = Program.sc.nextInt();
        } else if (type.equals("Double") || type.equals("double")) {
            if (Program.sc.hasNextDouble()) argument = Program.sc.nextDouble();
        } else if (type.equals("Boolean") || type.equals("boolean")) {
            if (Program.sc.hasNextBoolean()) argument = Program.sc.nextBoolean();
        } else if (type.equals("Long") || type.equals("long")) {
            if (Program.sc.hasNextLong()) argument = Program.sc.nextLong();
        } else if (type.equals("String")) {
            if (Program.sc.hasNext()) argument = Program.sc.next();
        }
        return argument;
    }

    protected static Object changeObject(Object object, Class<?> currentClass) {
        System.out.println("Enter name of the field for changing:");
        if (Program.sc.hasNext()) {
            String changingFieldName = Program.sc.next().trim();
            try {
                if (checkFields(changingFieldName, currentClass)) {
                    Field field = currentClass.getDeclaredField(changingFieldName);
                    field.setAccessible(true);
                    String type = field.getType().getSimpleName();
                    System.out.println("Enter " + type + " value:");
                    Object argument = getArgument(type);
                    field.set(object, argument);
                    System.out.println("Object updated: " + object.toString());
                    System.out.println(Program.DIVIDER_TEMPLATE);
                }
            } catch (NoSuchFieldException | IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }
        return object;
    }

    private static boolean checkFields(String changingFieldName, Class<?> currentClass) {
        boolean check = false;
        Field[] fields = currentClass.getDeclaredFields();
        for (Field field : fields) {
            if (changingFieldName.equals(field.getName())) check = true;
        }
        return check;
    }

    protected static void callObjectMethod(Object object, Class<?> currentClass) {
        System.out.println("Enter name of the method for call:");
        if (Program.sc.hasNext()) {
            String collingMethodString = Program.sc.next().trim();
            String[] collingMethod = collingMethodString.split("\\(");
            try {
                if (collingMethodString.endsWith("()")) {
                    if (checkMethods(collingMethodString, currentClass)) {
                        Method method = currentClass.getDeclaredMethod(collingMethod[0]);
                        method.setAccessible(true);
                        String parameterString = getParametersType(method);
                        if (parameterString.length() == 0) {
                            method.invoke(object);
                        }
                    }
                } else if (!collingMethodString.endsWith(")")) {
                    throw new NoSuchMethodException();
                } else {
                    callObjectParamMethod(object, currentClass, collingMethodString);
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }
    }


    private static void callObjectParamMethod(Object object, Class<?> currentClass, String collingMethodString)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (checkMethods(collingMethodString, currentClass)) {
            Method[] methods = currentClass.getDeclaredMethods();
            for (Method method : methods) {
                String parameters = getParametersType(method);
                if (collingMethodString.equals(String.format("%s(%s)", method.getName(),
                        parameters))) {
                    method.setAccessible(true);
                    String parameterString = getParametersType(method);
                    String[] allParameters = parameterString.split(",");
                    Object[] arguments = getMethodArguments(allParameters);
                    Object result = method.invoke(object, arguments);
                    System.out.println("Method returned:\n" + result);
                }
            }
        } else {
            throw new NoSuchMethodException();
        }
    }

    private static Object[] getMethodArguments(String[] parameters) {
        Object[] arguments = new Object[parameters.length];
        for (int i = 0; i < parameters.length; ++i) {
            System.out.println("Enter " + parameters[i] + " value:");
            arguments[i] = getArgument(parameters[i]);
        }
        return arguments;
    }

    private static boolean checkMethods(String collingMethod, Class<?> currentClass) {
        boolean check = false;
        Method[] methods = currentClass.getDeclaredMethods();
        for (Method method : methods) {
            String parameters = getParametersType(method);
            if (collingMethod.equals(String.format("%s(%s)", method.getName(),
                    parameters))) check = true;
        }
        return check;
    }

    private static String getParametersType(Method method) {
        return Arrays.stream(method.getParameterTypes())
                .map(Class::getSimpleName)
                .collect(Collectors.joining(", "));
    }
}
