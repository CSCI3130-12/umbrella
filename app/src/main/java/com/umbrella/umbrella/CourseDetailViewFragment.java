package com.umbrella.umbrella;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;


public class CourseDetailViewFragment extends Fragment {

    public CourseDetailViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_course_detail_view, container, false);
        prepareView(view);
        return view;
    }

    public void prepareView(View view){
    }

}