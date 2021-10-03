package baseball.game;

import baseball.error.IllegalInputException;
import baseball.error.IllegalQueryException;
import baseball.game.type.BaseBallResult;
import baseball.game.type.NumberContainer;

import java.util.HashSet;
import java.util.Set;

public class BaseBallEvaluator implements Evaluable<NumberContainer, BaseBallResult>{


    private final NumberContainer answers;

    public BaseBallEvaluator(NumberContainer numberContainer) {
        validateAnswers(numberContainer);
        this.answers = numberContainer;
    }

    private void validateAnswers(NumberContainer numberContainer) {
        Set<Integer> numbers = new HashSet<>(numberContainer.getNumberLength());
        for (int i = 0; i < numberContainer.getNumberLength(); i++) {
            int numberAt = numberContainer.getNumberAt(i);
            throwIfOutOfRange(numberAt);
            throwIfContains(numbers, numberAt);
            numbers.add(numberAt);
        }
    }

    private void throwIfOutOfRange(int numberAt) {
        if(numberAt <= 0 || numberAt > 9){
            throw new IllegalInputException("입력은 1에서 9사이의 정수여야합니다.");
        }
    }

    private void throwIfContains(Set<Integer> container, int number) {
        if(container.contains(number)){
            throw new IllegalInputException("각 숫자는 서로 다른 수로 이루어져야 합니다.");
        }
    }

    @Override
    public BaseBallResult evaluate(NumberContainer numberContainer) {
        validateAnswers(numberContainer);
        return new BaseBallResult(
                strikeCount(numberContainer), ballCount(numberContainer), numberContainer.getNumberLength());
    }

    private int ballCount(NumberContainer numberContainer) {
        int ballCount = 0;
        for (int i = 0; i < answers.getNumberLength(); i++) {
            ballCount += getBallCountEach(numberContainer, i);
        }
        return ballCount;
    }

    private int getBallCountEach(NumberContainer numberContainer, int answerIndex) {
        int ballCount = 0;
        for (int i = 0; i < numberContainer.getNumberLength(); i++) {
            ballCount += getOneIfBall(numberContainer, answerIndex, i);
        }
        return ballCount;
    }

    private int getOneIfBall(NumberContainer numberContainer, int answerIndex, int i) {
        if(answerIndex != i
                && answers.getNumberAt(answerIndex) == numberContainer.getNumberAt(i)) {
            return 1;
        }
        return 0;
    }

    private int strikeCount(NumberContainer numberContainer){
        int strikeCount = 0;
        for (int i = 0; i < answers.getNumberLength(); i++) {
            strikeCount += getOneIfStrike(numberContainer, i);
        }
        return strikeCount;
    }

    private int getOneIfStrike(NumberContainer numberContainer, int i) {
        if(answers.getNumberAt(i) == numberContainer.getNumberAt(i)) {
            return 1;
        }
        return 0;
    }
}
