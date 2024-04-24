package team.ybj.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.ybj.dto.ResponseResult;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = LackBalanceException.class)
    public ResponseResult<String> lackBalanceExceptionHandler(LackBalanceException e) {
        return new ResponseResult<>(422, "exception handler: lack balance", e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = AccountTypeException.class)
    public ResponseResult<String> accountTypeExceptionHandler(AccountTypeException e) {
        return new ResponseResult<>(422, "exception handler: account type exception", e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = DepositNegativeException.class)
    public ResponseResult<String> depositNegativeExceptionHandler(DepositNegativeException e) {
        return new ResponseResult<>(422, "exception handler: deposit negative exception", e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = LoginException.class)
    public ResponseResult<String> loginExceptionHandler(LoginException e) {
        return new ResponseResult<>(422, "exception handler: login exception", e.getMessage());
    }
}
