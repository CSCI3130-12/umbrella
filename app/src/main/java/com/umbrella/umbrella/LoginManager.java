package com.umbrella.umbrella;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by justin on 16/02/18.
 * Contains methods to manager user login
 */

public class LoginManager {

    private ArrayList<User> users;

    public LoginManager() {

    }

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

    public LoginManager(ArrayList<User> users) {
        this.users = users;
    }
}
