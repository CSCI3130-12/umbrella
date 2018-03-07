package com.umbrella.umbrella;

import android.content.Intent;
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
        EditText user = findViewById(R.id.username);
        EditText pass = findViewById(R.id.password);
        String username = user.getText().toString(),
                password = pass.getText().toString();

        if (loginManager.login(username, password)) {

            String token = loginManager.generateToken();
            ActiveUser activeUser = new ActiveUser(username,password,token);
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("USER", activeUser);
            startActivity(intent);
        } else {
            TextView output = findViewById(R.id.output);
            output.setText("Error, incorrect username or password");
        }
    }
}
