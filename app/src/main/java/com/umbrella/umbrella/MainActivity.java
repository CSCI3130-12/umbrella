package com.umbrella.umbrella;

import android.content.Intent;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getFragmentManager();

        Intent testingIntent = new Intent(this, RegistrationActivity.class);
        startActivity(testingIntent);
    }

    public void showMyCoursesFragment(){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment,new MyCourseFragment(), "MyCourses");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
