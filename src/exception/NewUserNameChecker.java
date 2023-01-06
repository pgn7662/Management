package exception;

public class NewUserNameChecker {
    public static void checkNewUserName(String newUserName) throws InvalidNewUserName {
        int numberOfAlphabets = 0;
        int numberOfSpecialCharacters = 0;
        for(char i:newUserName.toCharArray())
        {
            if(Character.isAlphabetic(i))
                numberOfAlphabets++;
            if(((int)i>=33 && (int)i<=47) ||((int)i>=58 && (int)i<=64) || ((int)i>=91 && (int)i<=94) || ((int)i>=123 && (int)i<=126) || (int)i == 96)
                numberOfSpecialCharacters++;
            if(i==' ')
                throw new InvalidNewUserName(1);
            if(Character.isUpperCase(i))
                throw new InvalidNewUserName(6);

        }

        if(newUserName.length()<3)
            throw new InvalidNewUserName(2);
        if(newUserName.length()>20)
            throw new InvalidNewUserName(3);
        if(numberOfAlphabets<3)
            throw new InvalidNewUserName(4);
        if(numberOfSpecialCharacters>0)
            throw new InvalidNewUserName(5);
    }
    
    protected static String getMessage(int exceptionNumber)
    {
        return switch (exceptionNumber) {
            case 1 -> "model.User name should not contain a space";
            case 2 -> "model.User name is too short at least 3 characters";
            case 3 -> "model.User name must be within 20 characters only";
            case 4 -> "model.User name should at least contains 3 alphabets";
            case 5 -> "model.User name should not contain any special characters except '_'";
            case 6 -> "model.User name should not contain Uppercase characters";
            default -> null;
        };
    }

}
