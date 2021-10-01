package baseball.io.impl;

import baseball.io.Input;
import nextstep.utils.Console;

public class ConsoleInput implements Input {

    @Override
    public String read() {
        return Console.readLine();
    }
}
