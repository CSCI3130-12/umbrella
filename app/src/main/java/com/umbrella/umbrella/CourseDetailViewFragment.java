package com.umbrella.umbrella;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class CourseDetailViewFragment extends Fragment {
    public static String COURSE = "COURSE";

    private CourseDetailViewPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Course course = ((Course)getArguments().get(COURSE));
        presenter = new CourseDetailViewPresenter(course);

        View view =  inflater.inflate(R.layout.fragment_course_detail_view, container, false);
        prepareView(view);
        return view;
    }

    public void prepareView(View view){
        CourseDetailViewModel vm = presenter.getViewModel();
        ((TextView)view.findViewById(R.id.course_detail_name)).setText(vm.name);
        ((TextView)view.findViewById(R.id.course_detail_course_id)).setText(vm.id);
        ((TextView)view.findViewById(R.id.course_detail_description)).setText(vm.description);
    }

}
