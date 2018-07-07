package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


import com.example.displayjokes.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.util.AsyncTaskCompleteListener;
import com.udacity.gradle.builditbigger.util.EndpointsAsyncTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    private static final String JOKE_KEY = "Joke key";
    private static final int LOADER_ID = 2;
    private String mJoke;
    private InterstitialAd mInterstitialAd;
    private ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mProgressBar = root.findViewById(R.id.progress);
        AdView mAdView = root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        Button mTelJokeBtn = root.findViewById(R.id.btn_get_Jokes);
        mTelJokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                initLoader();
            }
        });
        initializeInterstitialAds();

        return root;
    }
    private void initLoader() {
        if (getActivity() != null) {

            getActivity().getSupportLoaderManager().restartLoader(LOADER_ID, null, this);



        }
    }

    private void initializeInterstitialAds() {
        if (getActivity() != null) {
            mInterstitialAd = new InterstitialAd(getActivity());
            mInterstitialAd.setAdUnitId(getResources().getString(R.string.ca_app_pub));
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    requestNewInterstitial();
                    if (mJoke != null) {
                        startActivity(new Intent(getActivity(), JokeActivity.class).putExtra(JOKE_KEY, mJoke));

                    }
                }
            });
            requestNewInterstitial();
        }

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }


    private void addJokes(String mJoke) {


        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            if (mJoke != null)
                startActivity(new Intent(getActivity(), JokeActivity.class).putExtra(JOKE_KEY, mJoke));

        }

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        switch (id) {

            case LOADER_ID:

         /*any thing not true  data will returned  from EndpointsAsyncTaskLoader class*/

                if (getActivity() != null) {
                    return new EndpointsAsyncTask(getActivity(), new AsyncTaskCompleteListener<String>() {
                        @Override
                        public void onTaskComplete(String result) {

                        }
                    });
                }


            default:
                throw new RuntimeException("Loader Not Implemented: " + id);
        }

    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if (data != null) {
            mJoke = data;
            addJokes(data);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

}
