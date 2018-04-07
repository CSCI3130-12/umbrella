package com.umbrella.umbrella;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by justin on 14/03/18.
 */

public class ApplicationData extends Application {
    public DatabaseReference dbReference;
    public FirebaseDatabase firebaseDatabase;
}