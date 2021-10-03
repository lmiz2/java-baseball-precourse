package baseball.error;

public class BaseException extends RuntimeException{
    public BaseException(String message) {
        super("[ERROR] " + message);
    }
}
