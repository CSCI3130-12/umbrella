package com.umbrella.umbrella;

import android.app.Application;
import android.content.Intent;
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

import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ApplicationData appData;
    private static RegistrationInfo infoRepo;

    public static MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        ActiveUser activeUser = intent.getParcelableExtra("USER");
        super.onCreate(savedInstanceState);
        appData = (ApplicationData)getApplicationContext();
        appData.firebaseDatabase= FirebaseDatabase.getInstance();
        appData.dbReference=appData.firebaseDatabase.getReference();





        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();

                if (item.getItemId() == R.id.nav_browse) {
                    Intent myIntent = new Intent(MainActivity.this, RegistrationActivity.class);
                    startActivity(myIntent);
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

        if(infoRepo==null)
            infoRepo = new RegistrationInfo(deadlineText,appData);
        presenter = new MainPresenter(infoRepo);
        deadlineText.setText(presenter.getViewModel().deadlineMessage);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
