package edu.school21.numbers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {
    @ParameterizedTest
    @ValueSource(ints = {23, 7, 89, 101, 211})
    void isPrimeForPrimes(int argument) {
        try {
            assertTrue(NumberWorker.isPrime(argument));
        } catch (IllegalNumberException illegalNumberException) {
            illegalNumberException.printStackTrace();
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {55, 8, 555, 100, 15})
    void isPrimeForNotPrimes(int argument) {
        try {
            assertFalse(NumberWorker.isPrime(argument));
        } catch (IllegalNumberException illegalNumberException) {
            illegalNumberException.printStackTrace();
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 0, 1})
    void isPrimeForIncorrectNumbers(int argument) {
        Throwable exception = assertThrows(
                IllegalNumberException.class,
                () -> NumberWorker.isPrime(argument)
        );
        assertEquals("IllegalNumberException: The number should be >= 2!", exception.toString());
    }


    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"})
    void numberSumTest(int argument, int expectedOutput) {
        assertEquals(expectedOutput, NumberWorker.digitsSum(argument));
    }

}
