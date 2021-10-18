package jpabook.jpashop.exception;

public class NotEnoughStcokExceoption extends  RuntimeException{

    public NotEnoughStcokExceoption() {
        super();
    }

    public NotEnoughStcokExceoption(String message) {
        super(message);
    }

    public NotEnoughStcokExceoption(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStcokExceoption(Throwable cause) {
        super(cause);
    }

    protected NotEnoughStcokExceoption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
