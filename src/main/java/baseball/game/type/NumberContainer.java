package baseball.game.type;

import baseball.error.IllegalInputException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NumberContainer {

    public final static int NUMBERS_LENGTH = 3;

    private final List<Integer> numbers;

    public static NumberContainer of(int firstNumber, int secondNumber, int thirdNumber) {
        return new NumberContainer(firstNumber, secondNumber, thirdNumber);
    }

    public static NumberContainer from(List<Integer> numbers) {
        if(Objects.isNull(numbers) || numbers.size() != NUMBERS_LENGTH) {
            throw new IllegalInputException(String.format("잘못된 갯수의 숫자들입니다. 숫자는 %d개 여야 합니다.", NUMBERS_LENGTH));
        }
        return new NumberContainer(numbers.get(0), numbers.get(1), numbers.get(2));
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
