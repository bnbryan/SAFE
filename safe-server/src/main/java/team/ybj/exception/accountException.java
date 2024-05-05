package team.ybj.exception;

public class accountException extends RuntimeException {
    public accountException() {
        super();
    }

    public accountException(String message) {
        super(message);
    }

    public accountException(String message, Throwable cause) {
        super(message, cause);
    }

    public accountException(Throwable cause) {
        super(cause);
    }

    protected accountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
