package baseball.lifecycle;

import baseball.io.Input;
import baseball.io.Output;

import java.util.function.Consumer;

public abstract class AbstractProgram implements Program{

    private final Input input;
    private final Output output;
    private final Processor processor;

    public AbstractProgram(Input input, Output output, Processor processor) {
        this.input = input;
        this.output = output;
        this.processor = processor;
        processor.start();
    }

    protected void addRunnable(Runnable runnable){
        processor.add(runnable);
    }

    protected void readInput(Consumer<String> consumer){
        processor.add(() -> consumer.accept(input.read()));
    }

    protected void writeOutput(String text){
        processor.add(() -> output.write(text));
    }
}
