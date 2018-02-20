package com.umbrella.umbrella;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
        loginManager = new LoginManager();
    }

    public void buttonPress(View view) {
       /*EditText user = findViewById(R.id.userInput);
        EditText pass = findViewById(R.id.passInput);
        String username = user.getText().toString(),
                password = pass.getText().toString();

        if (loginManager.login(username, password)) {
            //TODO: Open activity for main page
        } else {
            TextView output = findViewById(R.id.output);
            output.setText("Error, incorrect username or password");
        }*/
    }
}
