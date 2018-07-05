package com.udacity.gradle.builditbigger;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.example.javaprovider.GetJokeFromJava;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
//
public class TestMainLoader extends ActivityInstrumentationTestCase2<MainActivity> {
    private static final String TAG = "TestMainLoader";


    public TestMainLoader() {
        super(MainActivity.class);
    }

    public void testTryingOutTheLoader() {
        MainActivity activity = getActivity();

        GetJokeFromJava getJokeFromJava = new GetJokeFromJava();
        //the value from javaLibrary
        String mJokeValFromJavalib = getJokeFromJava.tellJoke();

        //call class responsible for fetching data from the loader
        GetValueFromLoader getValueFromLoader = new GetValueFromLoader(activity, activity);

        //force wait loader to finish
        LoaderUtils.waitForLoader(getValueFromLoader.initLoader());

        //the value from BackEnd
        String mJokeValFromBackEnd = getValueFromLoader.getJoke;
        Log.d(TAG, "stepsvalue :while testing \nbackend value =" + mJokeValFromBackEnd + "\njava value =" + mJokeValFromJavalib);

        /* Comparison between the result that comes from the
        javaLibrary directly and direct result from the Backend*/
        assertEquals(mJokeValFromBackEnd, mJokeValFromJavalib);
    }


}