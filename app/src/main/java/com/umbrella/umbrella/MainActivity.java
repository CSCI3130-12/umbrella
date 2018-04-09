package com.umbrella.umbrella;

import android.app.Application;
import android.content.Intent;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;


public class MainActivity extends AppCompatActivity {
    FragmentManager manager;

    private DrawerLayout drawerLayout;
    private NavigationView navView;

    private ApplicationData appData;
    public static final String COURSE_REPO = "REPO";
    public static StartPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        ActiveUser activeUser = intent.getParcelableExtra("USER");
        //Toast toast = Toast.makeText(getApplicationContext(),activeUser.getUsername(), Toast.LENGTH_SHORT);
        //toast.show();
        super.onCreate(savedInstanceState);
        appData = (ApplicationData) getApplicationContext();
        appData.firebaseDatabase = FirebaseDatabase.getInstance();
        appData.dbReference = appData.firebaseDatabase.getReference();

        setContentView(R.layout.activity_main);
        manager = getFragmentManager();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        navView = (NavigationView) findViewById(R.id.nav_view);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                manager.popBackStack();

                if (item.getItemId() == R.id.nav_browse) {
                    Intent myIntent = new Intent(MainActivity.this, RegistrationActivity.class);
                    startActivity(myIntent);

                }else if(item.getItemId() == R.id.nav_view_my_courses){
                    showMyCoursesFragment();
                }else if(item.getItemId() == R.id.nav_logout){
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

                return true;
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);

        TextView deadlineText = (TextView) findViewById(R.id.registration_deadline);


        RegistrationInfo infoRepo = new RegistrationInfo(appData);
        presenter = new StartPresenter(infoRepo);
        deadlineText.setText(presenter.getViewModel().deadlineMessage);
    }

    /**
     * The method triggered when an option item is selected
     * @param item The item that has been selected
     * @return True if home was selected, otherwise passes it to the super method
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        navView.bringToFront();
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Creates a transaction for the ViewMyCourses fragment and pushes it to the stack
     */
    public void showMyCoursesFragment(){
        FragmentTransaction transaction = manager.beginTransaction();
        MyCourseFragment myCourseFragment = new MyCourseFragment();
        myCourseFragment.setArguments(getIntent().getExtras());
        transaction.add(R.id.fragment,myCourseFragment, "MyCourses");
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
