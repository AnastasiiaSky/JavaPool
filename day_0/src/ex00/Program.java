
public class Program {
    public static void main(String[] args) {
        int startNumber = 479598;
        int resultNumber;
        int numberOne, numberTwo, numberThree, numberFour, numberFive, numberSix;
        if(startNumber > 0) {
            numberOne = startNumber % 10;
            numberTwo = startNumber % 100 / 10;
            numberThree = startNumber % 1000 / 100;
            numberFour = startNumber % 10000 / 1000;
            numberFive = startNumber % 100000 / 10000;
            numberSix = startNumber / 100000;
            System.out.println(numberOne + numberTwo + numberThree + numberFour + numberFive + numberSix);
        }
    }
}