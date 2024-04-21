package team.ybj.exception;

public class PassResetException extends RuntimeException{
    public PassResetException() {
        super();
    }

    public PassResetException(String message) {
        super(message);
    }

    public PassResetException(String message, Throwable cause) {
        super(message, cause);
    }

    public PassResetException(Throwable cause) {
        super(cause);
    }

    protected PassResetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
