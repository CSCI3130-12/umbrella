package com.umbrella.umbrella;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;


import static org.junit.Assert.*;

/**
 * Created by justin on 16/02/18.
 */

public class LoginTest {
    private static ArrayList<User> users;
    private static LoginManager loginManager;

    @BeforeClass
    public static void init(){
        //hashmap of user/password combinations for temp database
        users = new ArrayList<>();

        users.add(new User("user1","pass1"));
        users.add(new User("user2","pass2"));
        users.add(new User("timothy","hunter2"));
        loginManager = new LoginManager(users);

    }

    @Test
    public void incorrectLogin(){
        assertFalse(loginManager.login("blah","pass"));
    }

    @Test
    public void correctLogin(){
        assertTrue(loginManager.login("user1","pass1"));
    }

    @Test
    public void verifyAllUsers() {
        for(User user:users){
            loginManager.login(user.getUsername(),user.getPassword());
        }
    }
}
