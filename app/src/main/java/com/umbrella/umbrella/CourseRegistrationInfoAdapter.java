package com.umbrella.umbrella;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by samdoiron on 2018-04-09.
 */

public class CourseRegistrationInfoAdapter extends ArrayAdapter<CourseRegistrationInfoViewModel> {
    CourseRegistrationInfoViewModel viewModels[] ;
    public CourseRegistrationInfoAdapter(@NonNull Context context, CourseRegistrationInfoViewModel viewModels[]) {
        super(context, 0, viewModels);
        this.viewModels = viewModels;
    }

    @Override
    public View getView(int position, View view, ViewGroup _group) {
        if (view == null) {
            view = new LectureLabRow(getContext(), viewModels[position]);
        }
        return view;
    }
}
