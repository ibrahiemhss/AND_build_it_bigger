package com.example.displayjokes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke, container, false);
        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            String joke = intent.getStringExtra(JokeActivity.JOKE_KEY);
            TextView jokeTextView = root.findViewById(R.id.joke_textview);
            if (joke != null && joke.length() != 0) {
                jokeTextView.setText(joke);
            }
        }


        return root;
    }
}
