package team.ybj.exception;

public class AccountTypeException extends RuntimeException {
    public AccountTypeException() {
        super();
    }

    public AccountTypeException(String message) {
        super(message);
    }

    public AccountTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountTypeException(Throwable cause) {
        super(cause);
    }

    protected AccountTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
