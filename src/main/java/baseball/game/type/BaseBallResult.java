package baseball.game.type;

import static baseball.game.type.BaseBallResult.ResultSymbol.*;

public class BaseBallResult {

    public enum ResultSymbol{
        STRIKE("스트라이크"),
        BALL("볼"),
        NOTHING("낫씽");

        private final String symbol;

        ResultSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    private final int ballCount;
    private final int strikeCount;

    public BaseBallResult(int strikeCount, int ballCount) {
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public boolean isNothing(){
        return ballCount <= 0 && strikeCount <= 0;
    }

    @Override
    public String toString() {
        if(strikeCount <= 0 && ballCount <= 0){
            return NOTHING.getSymbol();
        }
        StringBuilder sb = new StringBuilder();
        if(strikeCount > 0){
            sb.append(strikeCount);
            sb.append(" ");
            sb.append(STRIKE.getSymbol());
            sb.append(" ");
        }
        if(ballCount > 0){
            sb.append(ballCount);
            sb.append(" ");
            sb.append(BALL.getSymbol());
        }
        return sb.toString();
    }
}
