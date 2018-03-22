package com.umbrella.umbrella;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Stores application data from the database
 * Created by braden11 on 3/18/2018.
 */

public class MyApplicationData extends Application {

    public DatabaseReference firebaseReference;
    public FirebaseDatabase firebaseDBInstance;

    /**
     * Stores a reference to the given child in the database
     * @param reference The child to reference
     */
    public MyApplicationData(String reference) {
        //Set-up Firebase
        firebaseDBInstance = FirebaseDatabase.getInstance();
        firebaseReference = firebaseDBInstance.getReference(reference);
    }

    /**
     * Sets  the database reference to the given child
     * @param reference The child to reference
     */
    public void setReference(String reference){
        firebaseReference = firebaseDBInstance.getReference(reference);
    }
}
