package baseball.lifecycle;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class EventLoopProcessor implements Processor{

    private final Thread eventLoop;
    private final Queue<Runnable> eventQueue;
    private final AtomicBoolean stopRequired;

    public EventLoopProcessor() {
        this.eventQueue = new LinkedBlockingQueue<>();
        this.stopRequired = new AtomicBoolean(false);
        this.eventLoop = new Thread(() -> {
            while (!this.stopRequired.get()) {
                Runnable runnable = this.eventQueue.poll();
                if (Objects.nonNull(runnable)) {
                    runnable.run();
                }
            }
        });
    }

    @Override
    public void start() {
        eventLoop.start();
    }

    @Override
    public void add(Runnable runnable) {
        this.eventQueue.offer(runnable);
    }

    @Override
    public void stop() {
        this.stopRequired.set(true);
        this.eventQueue.clear();
    }
}
