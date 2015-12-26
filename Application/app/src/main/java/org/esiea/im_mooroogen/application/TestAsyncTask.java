package org.esiea.im_mooroogen.application;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Daren on 07/12/2015.
 */
public class TestAsyncTask extends AsyncTask<String, Integer, String> {
    private static final String TAG = "TAG";

    @Override
    protected String doInBackground(String... args) {
        publishProgress(args.length);
        Log.d(TAG, "Thread async doInBg name:"+ Thread.currentThread().getName());
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
        Log.d(TAG, "Thread async"+progress[0]+" name:"+Thread.currentThread().getName());
    }

    protected void onPostExecute(String... result) {
        Log.d(TAG, "Thread async"+result+" name:"+Thread.currentThread().getName());
    }
}
