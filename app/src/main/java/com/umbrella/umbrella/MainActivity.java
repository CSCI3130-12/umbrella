package com.umbrella.umbrella;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        ActiveUser activeUser = intent.getParcelableExtra("USER");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent testingIntent = new Intent(this, RegistrationActivity.class);
        startActivity(testingIntent);
    }

    public void logout(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
