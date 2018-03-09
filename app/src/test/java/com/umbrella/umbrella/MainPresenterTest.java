package com.umbrella.umbrella;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by samdoiron on 2018-03-07.
 */
public class MainPresenterTest {
    @Test
    public void registrationDeadlineMessageUsesCorrectDate() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date expectedDate = format.parse("2018-12-25");
            FakeRegistrationInfoRepo fakeRepo = new FakeRegistrationInfoRepo(expectedDate);
            MainPresenter presenter = new MainPresenter(fakeRepo);

            MainViewModel viewModel = presenter.getViewModel();

           assertEquals("Registration ends on December 25 2018", viewModel.deadlineMessage);
        } catch (ParseException e) {
            fail("Failed to parse test date");
        }
    }

    @Test
    public void registrationDeadlineDateDoesNotPadDayWithZero() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date expectedDate = format.parse("2018-12-01");
            FakeRegistrationInfoRepo fakeRepo = new FakeRegistrationInfoRepo(expectedDate);
            MainPresenter presenter = new MainPresenter(fakeRepo);

            MainViewModel viewModel = presenter.getViewModel();

            assertEquals("Registration ends on December 1 2018", viewModel.deadlineMessage);
        } catch (ParseException e) {
            fail("Failed to parse test date");
        }
    }
}