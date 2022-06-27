package az.express.user.error;

public class DataMismatchException extends CommonException {

    public DataMismatchException() {
        super("DATA_NOT_FOUND", "Data mismatch");
    }

    public DataMismatchException(String msg) {
        super("DATA_NOT_FOUND", msg);
    }

}
