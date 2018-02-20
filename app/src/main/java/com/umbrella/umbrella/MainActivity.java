package com.umbrella.umbrella;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent testingIntent = new Intent(this, RegistrationActivity.class);
        startActivity(testingIntent);
    }
}
