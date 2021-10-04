package baseball;

import baseball.io.impl.ConsoleInput;
import baseball.io.impl.ConsoleOutput;
import baseball.lifecycle.BaseBallGameProgram;

public class Application {
    public static void main(String[] args) {
        new BaseBallGameProgram(new ConsoleInput(), new ConsoleOutput());
    }
}
