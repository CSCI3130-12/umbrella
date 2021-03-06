package com.umbrella.umbrella;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by samdoiron on 2018-03-07.
 * The presenter for the initial screen of the app.
 */

public class StartPresenter {
    private final RegistrationInfoRepo repo;
    SimpleDateFormat dateFormat;

    public StartPresenter(RegistrationInfoRepo repo) {
        this.repo = repo;
        this.dateFormat = new SimpleDateFormat("MMMM d yyyy");
    }

    /** @return the view model for the main screen */
    public MainViewModel getViewModel() {
        MainViewModel viewModel = new MainViewModel();
        viewModel.deadlineMessage = getDeadlineMessage();
        return viewModel;
    }

    private String getDeadlineMessage() {
        Date deadline = repo.getRegistrationDeadline();
        if(deadline==null){
            return "Loading...";
        }
        String dateString = dateFormat.format(deadline);
        return "Registration ends on " + dateString;
    }
}
