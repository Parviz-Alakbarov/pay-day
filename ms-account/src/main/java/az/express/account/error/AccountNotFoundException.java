package az.express.account.error;

public class AccountNotFoundException extends CommonException {

    public AccountNotFoundException() {
        super("DATA_NOT_FOUND", "Account not found");
    }
}
