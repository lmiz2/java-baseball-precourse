package baseball.lifecycle;


import baseball.game.BaseballGame;
import baseball.io.Input;
import baseball.io.Output;

public class BaseBallGameProgram extends GameProgram{

    public BaseBallGameProgram(Input input, Output output, Processor processor) {
        super(new BaseballGame(), input, output, processor);
        super.boot();
    }
}
