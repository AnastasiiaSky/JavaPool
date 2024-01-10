package edu.school21.reflection.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

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
        if(Program.sc.hasNext()) {
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
                }
            } catch (NoSuchFieldException noSuchFieldException) {
                noSuchFieldException.printStackTrace();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
        return object;
    }

    private static boolean checkFields(String changingFieldName, Class<?> currentClass) {
        boolean check = false;
        Field[] fields = currentClass.getDeclaredFields();
        for (Field field : fields) {
            if(changingFieldName.equals(field.getName())) check = true;
        }
        return check;
    }
}
