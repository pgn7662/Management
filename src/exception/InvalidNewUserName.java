package exception;

public class InvalidNewUserName extends Exception {
    protected InvalidNewUserName(int exceptionNumber)
    {
        super(NewUserNameChecker.getMessage(exceptionNumber));
    }
}
