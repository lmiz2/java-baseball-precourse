package baseball;

import baseball.io.impl.ConsoleInput;
import baseball.io.impl.ConsoleOutput;
import baseball.lifecycle.BaseBallGameProgram;
import baseball.lifecycle.EventLoopProcessor;

public class Application {
    public static void main(String[] args) {
        new BaseBallGameProgram(new ConsoleInput(), new ConsoleOutput(), new EventLoopProcessor());
    }
}
