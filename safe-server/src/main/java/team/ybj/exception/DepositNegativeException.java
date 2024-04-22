package team.ybj.exception;

public class DepositNegativeException extends RuntimeException {
    public DepositNegativeException() {
        super();
    }

    public DepositNegativeException(String message) {
        super(message);
    }

    public DepositNegativeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepositNegativeException(Throwable cause) {
        super(cause);
    }

    protected DepositNegativeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
