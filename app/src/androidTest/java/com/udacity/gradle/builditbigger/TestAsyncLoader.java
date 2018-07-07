package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.udacity.gradle.builditbigger.util.AsyncTaskCompleteListener;
import com.udacity.gradle.builditbigger.util.EndpointsAsyncTask;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

/*
 * Created by ibrahim on 07/07/18.
 */
@RunWith(AndroidJUnit4.class)
public class TestAsyncLoader implements LoaderManager.LoaderCallbacks<String>{
    private String getJoke;
    private static final int LOADER_ID = 2;
    private Activity activity;
    private MainActivity mainActivity;
    @Rule
    public final ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Before
    public void setUp() throws Throwable {
        activity=activityActivityTestRule.getActivity();
        mainActivity=activityActivityTestRule.getActivity();
        runThread();

    }
    @Test
    public void clickButton() throws Exception {
        Loader<?> loader =
                mainActivity.getSupportLoaderManager().getLoader(LOADER_ID);
        LoaderUtils.waitForLoader(loader);

        if (getJoke != null) {
            onView(withText(R.string.button_text)).perform(click());
        }
        else {
                throw new Exception("value from Loader =" + null);
        }

    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        switch (id) {

            case LOADER_ID:

         /*any thing not true  data will returned  from EndpointsAsyncTaskLoader class*/

                return new EndpointsAsyncTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {

                    }
                });

            default:
                throw new RuntimeException("Loader Not Implemented: " + id);
        }

    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if (data != null) {
            getJoke = data;
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    private void runThread() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               initializeLoader();
            }
        });
    }

    private void initializeLoader(){
        mainActivity.getSupportLoaderManager().restartLoader(LOADER_ID, null, this);

    }
}

