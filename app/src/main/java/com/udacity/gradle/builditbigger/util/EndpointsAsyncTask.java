package com.udacity.gradle.builditbigger.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/*
 * Created by ibrahim on 03/07/18.
 */

public class EndpointsAsyncTask extends AsyncTaskLoader<String> {
    private static final String TAG = "EndpointsAsyncTask";
    private static MyApi myApiService = null;
    private final AsyncTaskCompleteListener<String> listener;

    private String joke;

    public EndpointsAsyncTask(@NonNull Context context, AsyncTaskCompleteListener<String> listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onStartLoading() {
        if (joke != null) {
            // Delivers any previously loaded data immediately
            deliverResult(joke);
        } else {
            // Force a new load
            forceLoad();
        }
    }


    @Override
    public String loadInBackground() {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            myApiService = builder.build();
        }


        try {
            joke = myApiService.getJokes().execute().getData();
            if (joke != null) {
                Log.d(TAG, "EndPoint return :" + joke);
                return joke;
            }
            return null;
        } catch (IOException e) {
            Log.d(TAG, "EndPoint  Error :" + e.toString());
            return e.getMessage();
        }
    }

    public void deliverResult(String jokes) {
        joke = jokes;
        super.deliverResult(joke);
        listener.onTaskComplete(joke);
        Log.d(TAG, "EndPoint deliverResult :" + joke);


    }
}