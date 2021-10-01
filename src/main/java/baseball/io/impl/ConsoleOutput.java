package baseball.io.impl;

import baseball.io.Output;

public class ConsoleOutput implements Output {
    @Override
    public void write(String text) {
        System.out.println(text);
    }
}
