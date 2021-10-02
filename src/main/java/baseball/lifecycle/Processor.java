package baseball.lifecycle;

public interface Processor {
    void start();
    void add(Runnable runnable);
    void stop();
}
