package com.umbrella.umbrella;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by justin on 16/02/18.
 * Contains the user interface for logging in
 */

public class LoginActivity extends AppCompatActivity {
    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ApplicationData appData = (ApplicationData) getApplicationContext();
        appData.firebaseDatabase = FirebaseDatabase.getInstance();
        appData.dbReference = appData.firebaseDatabase.getReference();
        DatabaseReference db = appData.dbReference;
        loginManager = new LoginManager(db);
    }


    /**
     * A button press event triggered upon the login button being pressed
     * @param view The view in which the event was triggered
     */
    public void buttonPress(View view) {
        EditText user = findViewById(R.id.username);
        EditText pass = findViewById(R.id.password);
        String username = user.getText().toString(),
                password = pass.getText().toString();

        if (loginManager.login(username, password)) {

            String token = loginManager.generateToken();
            ActiveUser activeUser = new ActiveUser(username, password, token);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("USER", activeUser);
            startActivity(intent);
            this.finish();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),"Error, incorrect username or password", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
