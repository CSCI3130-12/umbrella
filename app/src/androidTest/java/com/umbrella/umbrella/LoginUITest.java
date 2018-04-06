package com.umbrella.umbrella;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by justin on 22/02/18.
 */

@RunWith(AndroidJUnit4.class)
public class LoginUITest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    private LoginActivity loginActivity;

    @Before
    public void init(){
        loginActivity = mActivityRule.getActivity();
    }

    @Test
    public void incorrectLogin(){
        onView(withId(R.id.username)).perform(typeText("helloworld"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("hunter2"));
        closeSoftKeyboard();

        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.output)).check(matches(withText("Error, incorrect username or password")));
    }

    @Test
    public void noInput(){
        onView(withId(R.id.username)).perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText(""));
        closeSoftKeyboard();

        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.output)).check(matches(withText("Error, incorrect username or password")));
    }

    @Test
    public void correctLogin(){
        onView(withId(R.id.username)).perform(typeText("user1"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("pass1"));
        closeSoftKeyboard();

        onView(withId(R.id.button)).perform(click());
        
    }
}
