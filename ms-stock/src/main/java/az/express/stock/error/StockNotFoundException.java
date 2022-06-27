package az.express.stock.error;

public class StockNotFoundException extends CommonException {

    public StockNotFoundException() {
        super("DATA_NOT_FOUND", "Stock not found");
    }
}
