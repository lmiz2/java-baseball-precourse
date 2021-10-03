package baseball.game;

import baseball.game.type.BaseBallResult;
import baseball.game.type.NumberContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseBallEvaluatorTest {

    @Test
    @DisplayName("정답123에 대해 입력 345는 1ball 이어야 한다.")
    public void one_ball(){
        BaseBallEvaluator baseBallEvaluator = new BaseBallEvaluator(
                NumberContainer.of(1, 2, 3)
        );

        BaseBallResult result = baseBallEvaluator.evaluate(NumberContainer.of(3, 4, 5));
        assertEquals(result.getBallCount(), 1);
        assertEquals(result.getStrikeCount(), 0);
    }

    @Test
    @DisplayName("정답123에 대해 입력 341은 2ball 이어야 한다.")
    public void two_ball(){
        BaseBallEvaluator baseBallEvaluator = new BaseBallEvaluator(
                NumberContainer.of(1, 2, 3)
        );

        BaseBallResult result = baseBallEvaluator.evaluate(NumberContainer.of(3, 4, 1));
        assertEquals(result.getBallCount(), 2);
        assertEquals(result.getStrikeCount(), 0);
    }

    @Test
    @DisplayName("정답123에 대해 입력 231은 3ball 이어야 한다.")
    public void three_ball(){
        BaseBallEvaluator baseBallEvaluator = new BaseBallEvaluator(
                NumberContainer.of(1, 2, 3)
        );

        BaseBallResult result = baseBallEvaluator.evaluate(NumberContainer.of(2, 3, 1));
        assertEquals(result.getBallCount(), 3);
        assertEquals(result.getStrikeCount(), 0);
    }

    @Test
    @DisplayName("정답123에 대해 입력 456은 낫씽이어야 한다.")
    public void nothing(){
        BaseBallEvaluator baseBallEvaluator = new BaseBallEvaluator(
                NumberContainer.of(1, 2, 3)
        );

        BaseBallResult result = baseBallEvaluator.evaluate(NumberContainer.of(4, 5, 6));
        assertTrue(result.isNothing());
        assertEquals(result.getBallCount(), 0);
        assertEquals(result.getStrikeCount(), 0);
    }

    @Test
    @DisplayName("정답123에 대해 입력 145는 1strike 이어야 한다.")
    public void one_strike(){
        BaseBallEvaluator baseBallEvaluator = new BaseBallEvaluator(
                NumberContainer.of(1, 2, 3)
        );

        BaseBallResult result = baseBallEvaluator.evaluate(NumberContainer.of(1,4,5));
        assertEquals(result.getBallCount(), 0);
        assertEquals(result.getStrikeCount(), 1);
    }

    @Test
    @DisplayName("정답123에 대해 입력 125는 2strike 이어야 한다.")
    public void two_strike(){
        BaseBallEvaluator baseBallEvaluator = new BaseBallEvaluator(
                NumberContainer.of(1, 2, 3)
        );

        BaseBallResult result = baseBallEvaluator.evaluate(NumberContainer.of(1,2,5));
        assertEquals(result.getBallCount(), 0);
        assertEquals(result.getStrikeCount(), 2);
    }

    @Test
    @DisplayName("정답123에 대해 입력 123은 3strike 이어야 한다.")
    public void three_strike(){
        BaseBallEvaluator baseBallEvaluator = new BaseBallEvaluator(
                NumberContainer.of(1, 2, 3)
        );

        BaseBallResult result = baseBallEvaluator.evaluate(NumberContainer.of(1,2,3));
        assertEquals(result.getBallCount(), 0);
        assertEquals(result.getStrikeCount(), 3);
    }

    @Test
    @DisplayName("정답123에 대해 입력 134는 1strike-1ball 이어야 한다.")
    public void one_strike_one_ball(){
        BaseBallEvaluator baseBallEvaluator = new BaseBallEvaluator(
                NumberContainer.of(1, 2, 3)
        );

        BaseBallResult result = baseBallEvaluator.evaluate(NumberContainer.of(1,3,4));
        assertEquals(result.getBallCount(), 1);
        assertEquals(result.getStrikeCount(), 1);
    }

    @Test
    @DisplayName("정답123에 대해 입력 132는 1strike-2ball 이어야 한다.")
    public void one_strike_two_ball(){
        BaseBallEvaluator baseBallEvaluator = new BaseBallEvaluator(
                NumberContainer.of(1, 2, 3)
        );

        BaseBallResult result = baseBallEvaluator.evaluate(NumberContainer.of(1,3,2));
        assertEquals(result.getBallCount(), 2);
        assertEquals(result.getStrikeCount(), 1);
    }

}