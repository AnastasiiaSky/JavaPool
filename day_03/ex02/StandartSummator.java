package ex02;

import java.util.ArrayList;

public class StandartSummator {
    private final ArrayList<Integer> numbers;
    private Integer totalSum;

    public StandartSummator(ArrayCreator creator) {
        this.numbers = creator.getNumbers();
        countTotalSun();
    }

    private void countTotalSun() {
        Integer total = 0;
        for (int it = 0; it < numbers.size(); ++it) {
            total += numbers.get(it);
        }
        this.totalSum = total;
    }

    public Integer getTotalSum() {
        return this.totalSum;
    }


}
