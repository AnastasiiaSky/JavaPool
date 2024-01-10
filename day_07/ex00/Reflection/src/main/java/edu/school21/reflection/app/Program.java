package edu.school21.reflection.app;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.Scanner;
import java.util.Set;


public class Program {
    public static String DIVIDER_TEMPLATE = "---------------------";
    protected static final String PACKAGE = "edu.school21.reflection.models";
    protected static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Reflections reflections = new Reflections(PACKAGE, Scanners.SubTypes.filterResultsBy(s -> true));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
        Class<?> currentClass = InformationMethods.printAllClasses(classes);
        Object newObject = ActionMethods.objectCreating(currentClass);
        newObject = ActionMethods.changeObject(newObject, currentClass);
        ActionMethods.callObjectMethod(newObject, currentClass);
    }
}
