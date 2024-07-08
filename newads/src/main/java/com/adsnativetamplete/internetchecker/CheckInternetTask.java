package com.adsnativetamplete.internetchecker;

import android.os.AsyncTask;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class CheckInternetTask extends AsyncTask<Void, Void, Boolean> {

    private final WeakReference<TaskFinished<Boolean>> mCallbackWeakReference;

    CheckInternetTask(TaskFinished<Boolean> callback) {
        mCallbackWeakReference = new WeakReference<>(callback);
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            //parse url. if url is not parsed properly then return
            URL url;
            try {
                url = new URL("https://clients3.google.com/generate_204");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return false;
            }

            //open connection. If fails return false
            HttpURLConnection urlConnection;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            urlConnection.setRequestProperty("User-Agent", "Android");
            urlConnection.setRequestProperty("Connection", "close");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.connect();
            return urlConnection.getResponseCode() == 204 && urlConnection.getContentLength() == 0;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean isInternetAvailable) {
        TaskFinished<Boolean> callback = mCallbackWeakReference.get();
        if (callback != null) {
            callback.onTaskFinished(isInternetAvailable);
        }
    }
}
