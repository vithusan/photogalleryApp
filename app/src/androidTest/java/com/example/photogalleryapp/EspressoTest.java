package com.example.photogalleryapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class EspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);
    @Test
    public void Testfilter() {
        onView(withId(R.id.videobtn)).perform(click());
        onView(isRoot()).perform(pressBack());
        onView(withId(R.id.buttonhome)).perform(click());

        onView(withId(R.id.camerabtn)).perform(click());
        onView(isRoot()).perform(pressBack());
        onView(withId(R.id.btnLeft)).perform(click());
        onView(withId(R.id.btnRight)).perform(click());
        onView(withId(R.id.imageButton3)).perform(click());

        onView(withId(R.id.gallerybtn)).perform(click());
        onView(withId(R.id.ghomebtn)).perform(click());

    }


}
