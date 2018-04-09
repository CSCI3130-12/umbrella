package com.umbrella.umbrella;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class CourseDetailViewFragment extends Fragment {
    public static String COURSE = "COURSE";
    public static final String USER_REPO = "userRepo";

    private CourseDetailViewPresenter presenter;
    private CourseDetailViewModel viewModel;
    private UserRepo userRepo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Course course = ((Course)getArguments().get(COURSE));
        userRepo = ((UserRepo)getArguments().get(USER_REPO));
        presenter = new CourseDetailViewPresenter(
                // FIXME: Hack to get the current user
                getActivity().getIntent().getParcelableExtra("USER"),
                course,
                userRepo
        );

        presenter.setOnViewModelChanged(viewModel -> {
            onViewModelChanged();
            return null;
        });

        View view =  inflater.inflate(R.layout.fragment_course_detail_view, container, false);
        prepareView(view);
        return view;
    }

    private void onViewModelChanged() {
        if (viewModel.successMessage != null) {
            showRegistrationSuccess();
        } else if (viewModel.errorMessage != null) {
            showRegistrationError();
        }
    }

    private void showRegistrationSuccess() {
        Toast.makeText(getContext(), viewModel.successMessage, Toast.LENGTH_LONG).show();
    }

    private void showRegistrationError() {
        Toast.makeText(getContext(), viewModel.errorMessage, Toast.LENGTH_LONG).show();
    }

    public void prepareView(View view){
        updateViewModel();
        ((TextView)view.findViewById(R.id.course_detail_name)).setText(viewModel.name);
        ((TextView)view.findViewById(R.id.course_detail_course_id)).setText(viewModel.id);
        ((TextView)view.findViewById(R.id.course_detail_description)).setText(viewModel.description);

        ListView lectures = (ListView) view.findViewById(R.id.course_detail_course_list);
        lectures.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lectures.setAdapter(registrationInfoDataAdapter());
        lectures.setOnItemClickListener((parent, view1, position, id) -> {
            presenter.checkViewModel(position);
        });
        Button registerButton = view.findViewById(R.id.register_button);
        registerButton.setOnClickListener(_e -> {
            presenter.register();
        });
    }

    CourseRegistrationInfoAdapter registrationInfoDataAdapter() {
        updateViewModel();

        CourseRegistrationInfoViewModel lectures[]
                = new CourseRegistrationInfoViewModel[viewModel.registrationOptions.size()];
        viewModel.registrationOptions.toArray(lectures);

        return new CourseRegistrationInfoAdapter(getContext(), lectures);
    }

    private void updateViewModel() {
        viewModel = presenter.getViewModel();
    }
}
