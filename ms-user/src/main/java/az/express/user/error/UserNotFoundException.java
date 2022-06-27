package az.express.user.error;

public class UserNotFoundException extends CommonException {

    public UserNotFoundException() {
        super("USER_NOT_FOUND", "User not found");
    }

    public UserNotFoundException(String msg) {
        super("USER_NOT_FOUND", msg);
    }

}
