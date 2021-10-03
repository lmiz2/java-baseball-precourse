package baseball.game;

public interface Evaluable<V, T> {
    T evaluate(V v);
}
