package baseball.game;

import baseball.error.IllegalQueryException;
import baseball.game.type.NumberContainer;
import baseball.game.type.BaseBallResult;

public class BaseBallEvaluator implements Evaluable<NumberContainer, BaseBallResult>{


    private final NumberContainer answers;

    public BaseBallEvaluator(NumberContainer numberContainer) {
        validateAnswers(numberContainer);
        this.answers = numberContainer;
    }

    private void validateAnswers(NumberContainer numberContainer) {
        int a = -1;
        for (int i = 0; i < numberContainer.NUMBERS_LENGTH; i++) {
            int numberAt = numberContainer.getNumberAt(i);
            throwIfOutOfRange(numberAt);
            throwIfEquals(a, numberAt);
            a = numberAt;
        }
    }

    private void throwIfOutOfRange(int numberAt) {
        if(numberAt <= 0 || numberAt > 9){
            throw new IllegalQueryException("입력은 1에서 9사이의 정수여야합니다.");
        }
    }

    private void throwIfEquals(int a, int numberAt) {
        if(a == numberAt){
            throw new IllegalQueryException("각 숫자는 서로 다른 수로 이루어져야 합니다.");
        }
    }

    @Override
    public BaseBallResult evaluate(NumberContainer numberContainer) {
        return new BaseBallResult(strikeCount(numberContainer), ballCount(numberContainer));
    }

    private int ballCount(NumberContainer numberContainer) {
        int ballCount = 0;
        for (int i = 0; i < answers.NUMBERS_LENGTH; i++) {
            ballCount += getBallCountEach(numberContainer, i);
        }
        return ballCount;
    }

    private int getBallCountEach(NumberContainer numberContainer, int answerIndex) {
        int ballCount = 0;
        for (int i = 0; i < numberContainer.NUMBERS_LENGTH; i++) {
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
        for (int i = 0; i < answers.NUMBERS_LENGTH; i++) {
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
