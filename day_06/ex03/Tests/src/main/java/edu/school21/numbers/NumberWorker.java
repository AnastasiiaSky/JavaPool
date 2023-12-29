package edu.school21.numbers;

import edu.school21.exceptions.IllegalNumberException;

public class NumberWorker {
    public static boolean isPrime(final int number) throws IllegalNumberException {
        if (number < 2) {
            throw new IllegalNumberException("The number should be >= 2!");
        }
        for (int it = 2; it <= Math.sqrt(number); ++it) {
            if (number % it == 0) return false;
        }
        return true;
    }

    public static int digitsSum(int number) {
        int result = 0;
        do {
            result += number % 10;
            number /= 10;
        } while (number != 0);
        return result;
    }
}
