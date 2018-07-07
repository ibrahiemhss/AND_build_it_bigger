package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.displayjokes.JokeActivity;
import com.udacity.gradle.builditbigger.util.AsyncTaskCompleteListener;
import com.udacity.gradle.builditbigger.util.EndpointsAsyncTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    public static final int LOADER_ID = 2;
    public static String JOKE_KEY = "Joke key";
    private String mJoke;
    private Button mTelJokeBtn;
    private ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);


        mProgressBar = root.findViewById(R.id.progress);
        mTelJokeBtn = root.findViewById(R.id.btn_get_Jokes);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTelJokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                initLoader();
            }
        });
    }

    public void initLoader() {
        getActivity().getSupportLoaderManager().restartLoader(LOADER_ID, null, this);

    }


    public void addJokes(String mJoke) {

        if (mJoke != null) {
            startActivity(new Intent(getActivity(), JokeActivity.class).putExtra(JOKE_KEY, mJoke));

        }

    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        switch (id) {

            case LOADER_ID:

         /*any thing not true  data will returned  from EndpointsAsyncTaskLoader class*/

                return new EndpointsAsyncTask(getActivity(), new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {

                    }
                });

            default:
                throw new RuntimeException("Loader Not Implemented: " + id);
        }

    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data != null) {
            mJoke = data;
            addJokes(data);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

}
