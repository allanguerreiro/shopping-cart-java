package br.com.altran.carrinho.exception;

import org.springframework.stereotype.Component;

@Component
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable throwable) {
        super(throwable.getMessage() + " - " + throwable.getLocalizedMessage());
    }

    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public Throwable getCause() {
        return super.getCause();
    }
}
