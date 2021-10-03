package baseball.game;

import baseball.error.BaseException;
import baseball.error.IllegalInputException;
import baseball.error.IllegalQueryException;
import baseball.game.type.BaseBallResult;
import baseball.game.type.NumberContainer;
import baseball.io.Input;
import baseball.io.Output;
import nextstep.utils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BaseballGame implements Game {

    private final static int MAX_RETRY_RANDOM_GENERATE = 100;

    private final int numberLength;
    private final Input input;
    private final Output output;

    public BaseballGame(Input input, Output output, int numberLength) {
        this.input = input;
        this.output = output;
        this.numberLength = numberLength;
    }

    @Override
    public void start() {
        Evaluable<NumberContainer, BaseBallResult> evaluable = createUntilCorrectly();

        boolean isFinished = false;
        while(!isFinished){
            Optional<BaseBallResult> result = tryGetEvaluatedResult(evaluable, getInputNumbers());
            if(result.isPresent()){
                BaseBallResult baseBallResult = result.get();
                writeOutputWithLinefeed(baseBallResult.toString());
                isFinished = baseBallResult.isFinished();
            }
        }
        writeOutputWithLinefeed("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
    }

    private Optional<BaseBallResult> tryGetEvaluatedResult(
            Evaluable<NumberContainer, BaseBallResult> evaluable, NumberContainer numberContainer) {

        try {
            return Optional.of(evaluable.evaluate(numberContainer));
        } catch (BaseException be) {
            writeOutputWithLinefeed(be.getMessage());
        }
        return Optional.empty();
    }

    private Evaluable<NumberContainer, BaseBallResult> createUntilCorrectly() {
        return recursiveCreate(0);
    }

    private Evaluable<NumberContainer, BaseBallResult> recursiveCreate(int depth){
        if(depth >= MAX_RETRY_RANDOM_GENERATE){
            throw new IllegalArgumentException("랜덤 변수 생성의 너무 많은 재시도 발생");
        }
        try{
            return new BaseBallEvaluator(NumberContainer.from(getRandomIntegers(numberLength)));
        }catch (IllegalInputException ignored){
            return recursiveCreate(depth + 1);
        }
    }

    private NumberContainer getInputNumbers() {
        output.write("숫자를 입력해주세요 : ");
        return toNumberContainerWithCatch(input.read())
                .orElseGet(this::getInputNumbers);
    }

    private Optional<NumberContainer> toNumberContainerWithCatch(String readInput) {
        try {
            return Optional.of(toNumberContainer(readInput));
        } catch (BaseException be) {
            writeOutputWithLinefeed(be.getMessage());
        }
        return Optional.empty();
    }

    private NumberContainer toNumberContainer(String readInput) {
        if(Objects.isNull(readInput) || readInput.length() != numberLength) {
            throw new IllegalQueryException(String.format("잘못된 입력입니다. 숫자 %d개를 입력해주세요.", numberLength));
        }
        return NumberContainer.from(readInput);
    }

    private List<Integer> getRandomIntegers(int size){
        List<Integer> results = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            results.add(Randoms.pickNumberInRange(1, 9));
        }
        return results;
    }

    private void writeOutputWithLinefeed(String text) {
        output.write(text + "\n");
    }
}
