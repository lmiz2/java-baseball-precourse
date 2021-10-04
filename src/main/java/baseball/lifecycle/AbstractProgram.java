package baseball.lifecycle;

import baseball.io.Input;
import baseball.io.Output;

import java.util.function.Consumer;

public abstract class AbstractProgram implements Program{

    private final Input input;
    private final Output output;

    public AbstractProgram(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    protected String readInput(){
        return input.read();
    }

    protected void writeOutput(String text){
        output.write(text);
    }
}
