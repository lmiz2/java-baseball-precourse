package baseball.lifecycle;


import baseball.game.BaseballGame;
import baseball.io.Input;
import baseball.io.Output;

public class BaseBallGameProgram extends GameProgram{

    public BaseBallGameProgram(Input input, Output output) {
        super(new BaseballGame(input, output, 3), input, output);
        super.boot();
    }
}
