package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;



public class AuthenticationService {

    private UserDao userDao;
    int PASSWORD_MINIMUM_LENGTH = 8;
    int USERNAME_MINIMUM_LENGTH = 3;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {

        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean containsLettersAndNumbers(String input) {
        char[] chars = input.toCharArray();
        boolean hasLetters = false;
        boolean hasNumbers = false;

        //for (char c: chars) {
            for (int i = 0; i < chars.length; i++) {
            Character ch = new Character (chars[i]);
            if (Character.getType(ch) == Character.DECIMAL_DIGIT_NUMBER) {
                hasNumbers = true;
            } else if (Character.getType(ch) == Character.LOWERCASE_LETTER) {
                hasLetters = true;
            } else {
                return false;
            }
        }
        return (hasLetters && hasNumbers);
    }

    private boolean containsLettersOnly(String input) {
        char[] chars = input.toCharArray();
        for (char c: chars) {
            Character ch = new Character (c);
            if (!(Character.getType(ch) == Character.LOWERCASE_LETTER)) {
                return false;
            }
        }
        return true;
    }

    private boolean usernameIsValid(String username) {
        if (username.length() < USERNAME_MINIMUM_LENGTH) return false;
        return containsLettersOnly(username);
    }

    private boolean passwordIsValid(String password) {
        if (password.length() < PASSWORD_MINIMUM_LENGTH) return false;
        return containsLettersAndNumbers(password);
    }

    private boolean invalid(String username, String password) {
        return (!usernameIsValid(username) || !passwordIsValid(password));
    }
}
