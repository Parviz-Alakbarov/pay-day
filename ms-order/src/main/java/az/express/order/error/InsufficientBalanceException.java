package az.express.order.error;

public class InsufficientBalanceException extends CommonException {

    public InsufficientBalanceException() {
        super("INSUFFICIENT_BALANCE", "Account balance is not enough to make such order");
    }

    public InsufficientBalanceException(String msg) {
        super("INSUFFICIENT_BALANCE", msg);
    }
}
