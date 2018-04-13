package com.umbrella.umbrella;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.function.Function;

/**
 * Created by samdoiron on 2018-04-09.
 */

public class LectureLabRow extends LinearLayout implements Checkable {
    private CourseRegistrationInfoViewModel viewModel;
    private Function<Boolean, Void> checkedListener;
    private TextView mainContent;
    private RadioButton radio;

    public LectureLabRow(Context context, CourseRegistrationInfoViewModel viewModel) {
        super(context);
        this.viewModel = viewModel;
        this.checkedListener = (_checked) -> null;
        init();
    }

    public void setViewModel(CourseRegistrationInfoViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private void init() {
        inflate(getContext(), R.layout.lecture_lab_row, this);
        mainContent = findViewById(R.id.main_content);
        radio = (RadioButton)findViewById(R.id.radio);
        // TODO: Un-check if viewmodel say to
        update();
    }

    public void update() {
        mainContent.setText(viewModel.title);
    }

    @Override
    public void setChecked(boolean checked) {
        radio.setChecked(checked);
    }

    @Override
    public boolean isChecked() {
        return radio.isChecked();
    }

    @Override
    public void toggle() {
        radio.toggle();
    }

    public void setCheckedListener(Function<Boolean, Void> checkedListener) {
        this.checkedListener = checkedListener;
    }
}
