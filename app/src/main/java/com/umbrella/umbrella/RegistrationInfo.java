package com.umbrella.umbrella;

import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by justin on 14/03/18.
 */

public class RegistrationInfo implements RegistrationInfoRepo  {
    private Date deadline;
    private DatabaseReference db;


    public RegistrationInfo(final TextView text, ApplicationData data) {
        db=data.dbReference.child("Semester").child("Semester info").child("Registration dates").child("End");

        ValueEventListener listener = (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String deadlineString = dataSnapshot.getValue(String.class);
                try {
                    deadline = new SimpleDateFormat("dd/mm/yyyy").parse(deadlineString);
                } catch (ParseException e) {
                    deadline=new Date();
                    e.printStackTrace();
                }

                //Somehow trigger a UI update from here
                text.setText(MainActivity.presenter.getViewModel().deadlineMessage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.println(Log.ERROR,"DB error","DB Error");
            }
        });

        db.addListenerForSingleValueEvent(listener);
    }


    @Override
    public Date getRegistrationDeadline(){

        return deadline;
    }
}
