package ex02;

import java.util.ArrayList;

public class ArrayCreator {
    private final int size;
    private final ArrayList<Integer> numbers = new ArrayList<>();

    public ArrayCreator(int size) {
        this.size = size;
        createArray();
    }

    private void createArray() {
        for (int it = 0; it < size; ++it) {
            numbers.add((int) (Math.random() * (2000 + 1) - 1000));
        }
    }

//    private void printArray() {
//        for (int it = 0; it < size; ++it) {
//            System.out.println(numbers.get(it) + " ");
//        }
//        System.out.println();
//    }

    public ArrayList<Integer> getNumbers() {
        return this.numbers;
    }
}
