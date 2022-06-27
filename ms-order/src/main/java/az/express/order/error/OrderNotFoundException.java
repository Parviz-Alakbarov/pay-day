package az.express.order.error;

public class OrderNotFoundException extends CommonException {

    public OrderNotFoundException() {
        super("DATA_NOT_FOUND", "Order not found");
    }
}
