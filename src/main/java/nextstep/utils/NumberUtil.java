package nextstep.utils;

import baseball.error.IllegalInputException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NumberUtil {
    
    public static List<Integer> arrayToList(int[] arr) {
        if(Objects.isNull(arr) || arr.length == 0){
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>(arr.length);
        for (int i : arr) {
            list.add(i);
        }
        return list;
    }

    public static int characterToInteger(char c) {
        throwIfNotNumber(c);
        return c - 48;
    }

    public static void throwIfNotNumber(char c) {
        if(c < 48 || c > 57){
            throw new IllegalInputException("숫자만 입력 할 수 있습니다.");
        }
    }

}
