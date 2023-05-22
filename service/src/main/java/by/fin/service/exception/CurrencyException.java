package by.fin.service.exception;

public class CurrencyException extends RuntimeException {

    private final int status;

    public CurrencyException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
