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

        for (char c: chars) {
            if (c >= 48 && c <= 57) {
                hasNumbers = true;
            } else if (c > 96 && c < 123) {
                hasLetters = true;
            } else {
                return false;
            }
        }
        if (hasLetters && hasNumbers) return true;
        return false;
    }

    private boolean usernameIsValid(String username) {
        if (username.length() < USERNAME_MINIMUM_LENGTH) return false;
        return containsLettersAndNumbers(username);
    }

    private boolean passwordIsValid(String password) {
        if (password.length() < PASSWORD_MINIMUM_LENGTH) return false;
        //return containsLettersAndNumbers(password);
        char[] chars = password.toCharArray();
        boolean hasLetters = false;
        boolean hasNumbers = false;

        for (char c: chars) {
            if (c >= 48 && c <= 57) {
                hasNumbers = true;
            } else if (c > 96 && c < 123) {
                hasLetters = true;
            } else {
                return false;
            }
        }
        if (hasLetters && hasNumbers) return true;
        return false;
        
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        return !usernameIsValid(username) && !passwordIsValid(password);
    }
}
