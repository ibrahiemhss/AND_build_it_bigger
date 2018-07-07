package com.udacity.gradle.builditbigger.util;

/*
 * Created by ibrahim on 03/07/18.
 */

@SuppressWarnings("unused")
public interface AsyncTaskCompleteListener<T> {
    /**
     * Invoked when the AsyncTask has completed its execution.
     *
     * @param result The resulting object from the AsyncTask.
     */
    @SuppressWarnings("EmptyMethod")
    void onTaskComplete(T result);
}
