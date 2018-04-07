package com.umbrella.umbrella;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;


/**
 * Created by justin on 16/02/18.
 * Contains methods to manage user login
 */

public class LoginManager {

    private ArrayList<User> users;

    /**
     * Constructor which asynchronously connects to the database and fetches user info
     * @param db A reference to the Firebase db
     */
    public LoginManager(DatabaseReference db) {
        users = new ArrayList<>();
        ValueEventListener listener = new ValueEventListener() {
            /**
             * Asynchronously fetches all users and their passwords from the current data snapshot
             * @param dataSnapshot
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = new User();
                    user.setUsername(data.child("Username").getValue(String.class));
                    user.setPassword(data.child("Password").getValue(String.class));
                    users.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.println(Log.ERROR,"DB Error",databaseError.getMessage());
            }
        };
        db = db.child("Users").child("Student");
        db.addListenerForSingleValueEvent(listener);

        users.add(new User("user1", "pass1"));
        users.add(new User("user2", "pass2"));
        users.add(new User("timothy", "hunter2"));
    }

    /**
     * Attempts to login with the given information
     *
     * @param username the users name
     * @param password the users password
     * @return a boolean indicating a successful login
     */
    public boolean login(String username, String password) {

        for (User user : users)
            if (user.getUsername().equals(username)) {
                return user.getPassword().equals((password));
            }

        return false;
    }

    /**
     * Generates an auth token for the current user
     *
     * @return a string containing the auth token
     */
    public String generateToken() {
        return "totallySecureRandomToken";
    }

    /**
     * An alternate constructor where the list of users is given
     * @param users A list of users to be able to login in as
     */
    public LoginManager(ArrayList<User> users) {
        this.users = users;
    }
}
