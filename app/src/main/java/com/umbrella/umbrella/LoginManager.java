package com.umbrella.umbrella;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by justin on 16/02/18.
 * Contains methods to manage user login
 */

public class LoginManager {

    private ArrayList<User> users;

    public LoginManager() {
        users = new ArrayList<>();

        users.add(new User("user1","pass1"));
        users.add(new User("user2","pass2"));
        users.add(new User("timothy","hunter2"));
    }

    /**
     * Attempts to login with the given information
     * @param username the users name
     * @param password the users password
     * @return a boolean indicating a successful login
     */
    public boolean login(String username, String password) {

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals((password))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Generates an auth token for the current user
     * @return a string containing the auth token
     */
    public String generateToken(){
        return "totallySecureRandomToken";
    }

    public LoginManager(ArrayList<User> users) {
        this.users = users;
    }
}
