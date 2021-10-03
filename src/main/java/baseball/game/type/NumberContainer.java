package baseball.game.type;

import baseball.error.IllegalInputException;
import nextstep.utils.NumberUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NumberContainer {

    private final List<Integer> numbers;

    public static NumberContainer of(int... numbers) {
        return from(NumberUtil.arrayToList(numbers));
    }

    public static NumberContainer from(List<Integer> numbers) {
        if(Objects.isNull(numbers)){
            return new NumberContainer(Collections.emptyList());
        }
        return new NumberContainer(numbers);
    }

    public static NumberContainer from(String numberString) {
        return from(convertStringToList(numberString));
    }

    private static List<Integer> convertStringToList(String numberString) {
        if(Objects.isNull(numberString)){
            return Collections.emptyList();
        }
        List<Integer> numbers = new ArrayList<>(numberString.length());
        for (int i = 0; i < numberString.length(); i++) {
            numbers.add(NumberUtil.characterToInteger(numberString.charAt(i)));
        }
        return numbers;
    }

    private NumberContainer(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int getNumberAt(int idx){
        if(idx < 0 || idx >= getNumberLength()){
            throw new IllegalInputException(String.format("잘못된 인덱스 입니다 : %d", idx));
        }
        return this.numbers.get(idx);
    }

    public int getNumberLength() {
        return this.numbers.size();
    }

}
