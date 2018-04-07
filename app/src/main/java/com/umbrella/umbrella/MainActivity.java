package com.umbrella.umbrella;

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
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    FragmentManager manager;

    private DrawerLayout drawerLayout;
    private NavigationView navView;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        ActiveUser activeUser = intent.getParcelableExtra("USER");
        super.onCreate(savedInstanceState);

        FakeRegistrationInfoRepo infoRepo = new FakeRegistrationInfoRepo(new Date());
        presenter = new MainPresenter(infoRepo);

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

    public void showMyCoursesFragment(){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment,new MyCourseFragment(), "MyCourses");
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
