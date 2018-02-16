package com.umbrella.umbrella;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;


import static org.junit.Assert.*;

/**
 * Created by justin on 16/02/18.
 */

public class LoginTest {
    private static HashMap<String,String> users;

    @BeforeClass
    public static void init(){
        //hashmap of user/password combinations for temp database
        users = new HashMap<>();

        users.put("user1","pass1");
        users.put("user2","pass2");
        users.put("timothy","hunter2");

        LoginManager.initialiseDB(users);
    }
    @Test
    public void verifyDB(){
        assertEquals(users,LoginManager.getDB());
    }

    @Test
    public void incorrectLogin(){
        assertFalse(LoginManager.login("blah","pass"));
    }

    @Test
    public void correctLogin(){
        assertTrue(LoginManager.login("user1","pass1"));
    }

    @Test
    public void verifyAllUsers() {
        for(String user:users.keySet()){
            LoginManager.login(user,users.get(user));
        }
    }
}
