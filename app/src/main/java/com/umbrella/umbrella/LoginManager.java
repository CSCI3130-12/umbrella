package com.umbrella.umbrella;

import java.util.HashMap;


/**
 * Created by justin on 16/02/18.
 */

public class LoginManager {

    private static HashMap<String, String> db;

    public static boolean login(String username, String password){
        if(db.containsKey(username)){
            if(db.get(username).equals(password)){
                return  true;
            }
        }
        return false;

    }

    public static void initialiseDB(HashMap<String,String> users){
        db = users;
    }

    public static HashMap<String,String> getDB(){
        return db;
    }
}
