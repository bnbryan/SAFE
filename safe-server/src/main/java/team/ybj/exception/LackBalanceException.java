package team.ybj.exception;

public class LackBalanceException extends RuntimeException{
    public LackBalanceException() {
        super();
    }

    public LackBalanceException(String message) {
        super(message);
    }

    public LackBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LackBalanceException(Throwable cause) {
        super(cause);
    }

    protected LackBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
