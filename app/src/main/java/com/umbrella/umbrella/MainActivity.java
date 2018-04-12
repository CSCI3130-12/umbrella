package com.umbrella.umbrella;

import android.content.Intent;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    FragmentManager manager;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    public static final String COURSE_REPO = "courseRepo";

    private ApplicationData appData;
    private StudentRepo studentRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        ActiveUser activeUser = intent.getParcelableExtra("USER");
        super.onCreate(savedInstanceState);


        manager = getFragmentManager();
        appData = (ApplicationData) getApplicationContext();
        appData.firebaseDatabase = FirebaseDatabase.getInstance();
        appData.dbReference = appData.firebaseDatabase.getReference();

        studentRepo = new DatabaseUserRepo(appData.dbReference);

        setContentView(R.layout.activity_main);
        manager = getFragmentManager();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, new StartFragment());
        transaction.commit();

        drawerLayout = findViewById(R.id.drawer_layout);

        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_add_drop:
                        switchToBrowseCourses();
                        break;
                    case R.id.nav_main:
                        switchToHome();
                        break;
                    case R.id.nav_view_my_courses:
                        showMyCoursesFragment();
                        break;
                    case R.id.nav_logout:
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                return true;
            }
        });
    }

    /**
     * The method triggered when an option item is selected
     * @param item The item that has been selected
     * @return True if home was selected, otherwise passes it to the super method
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void switchToFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    public void switchToHome() {
        switchToFragment(new StartFragment());
    }

    public void switchToBrowseCourses() {
        BrowseCoursesFragment fragment = new BrowseCoursesFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(COURSE_REPO, new DatabaseCourseRepo(appData.dbReference));
        fragment.setArguments(arguments);
        switchToFragment(fragment);
    }

    public void switchToCourseDetails(Course course) {
        CourseDetailViewFragment fragment = new CourseDetailViewFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(CourseDetailViewFragment.COURSE, course);
        arguments.putSerializable(CourseDetailViewFragment.USER_REPO, studentRepo);
        fragment.setArguments(arguments);
        switchToFragment(fragment);
    }

    /**
     * Creates a transaction for the ViewMyCourses fragment and pushes it to the stack
     */
    public void showMyCoursesFragment() {
        switchToFragment(new MyCourseFragment());
    }
}
