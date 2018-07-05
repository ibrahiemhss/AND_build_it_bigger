package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.udacity.gradle.builditbigger.util.AsyncTaskCompleteListener;
import com.udacity.gradle.builditbigger.util.EndpointsAsyncTask;

/*
 * Created by ibrahim on 05/07/18.
 */

class GetValueFromLoader implements LoaderManager.LoaderCallbacks<String> {
    private static final int LOADER_ID = 2;

    private final MainActivity mainActivity;
    private final Context context;
    public String getJoke;

    public GetValueFromLoader(MainActivity mainActivity, Context context) {
        this.mainActivity = mainActivity;
        this.context = context;
    }


    public Loader<String> initLoader() {

        return mainActivity.getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        switch (id) {

            case LOADER_ID:

         /*any thing not true  data will returned  from EndpointsAsyncTaskLoader class*/

                return new EndpointsAsyncTask(context, new AsyncTaskCompleteListener<String>() {
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

}
