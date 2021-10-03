package baseball.game.type;

import baseball.error.IllegalInputException;

import java.util.Arrays;
import java.util.List;

public class NumberContainer {

    public final int NUMBERS_LENGTH = 3;

    private final List<Integer> numbers;

    public static NumberContainer of(int firstNumber, int secondNumber, int thirdNumber) {
        return new NumberContainer(firstNumber, secondNumber, thirdNumber);
    }

    private NumberContainer(int firstNumber, int secondNumber, int thirdNumber) {
        this.numbers = Arrays.asList(firstNumber, secondNumber, thirdNumber);
    }

    public int getNumberAt(int idx){
        if(idx < 0 || idx >= 3){
            throw new IllegalInputException(String.format("잘못된 인덱스 입니다 : %d", idx));
        }
        return this.numbers.get(idx);
    }

}
