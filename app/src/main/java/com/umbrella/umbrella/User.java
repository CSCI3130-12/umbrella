package com.umbrella.umbrella;

/**
 * Created by wauch on 2018-02-19.
 * A user class for storing general information about users.
 */

public class User  {
    private String username;
    private String password;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

}
