package com.uleth.flickrbrowser;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by michellele on 2018-03-17.
 */

enum DownloadStatus{ IDLE, PROCESSING, NOT_INITIALIZED, FAILED_OR_EMPTY, OK}

class GetRawData extends AsyncTask<String, Void, String>
{
    private static final String TAG = "GetRawData";
    private DownloadStatus mDownloadStatus;
    private final OnDownloadComplete mCallback;

    interface OnDownloadComplete{
     void onDownloadComplete(String data, DownloadStatus status );
    }


    public GetRawData(OnDownloadComplete callback)
    {

        this.mDownloadStatus = DownloadStatus.IDLE;
        mCallback = callback;

    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: parameter=" + s);
        if(mCallback != null){
            mCallback.onDownloadComplete(s, mDownloadStatus);
        }
        Log.d(TAG, "onPostExecute: ends");
        //super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection= null;
        BufferedReader reader = null;
        if(strings==null){
            mDownloadStatus =DownloadStatus.NOT_INITIALIZED;
            return null;
        }

        try{

            mDownloadStatus = DownloadStatus.PROCESSING;
            URL url = new URL( strings[0]);

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.connect();
            int response = connection.getResponseCode();
            Log.d(TAG, "doInBackground: The response code was" + response);

            StringBuilder result = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String Line;

            while (null != (Line=reader.readLine())) {
                result.append(Line).append("\n");
            }

            mDownloadStatus = DownloadStatus.OK;
            return result.toString();

        }catch (MalformedURLException e){
            Log.e(TAG, "doInBackground: url error" + e.getMessage());
        }catch (IOException e){
            Log.e(TAG,"doinBackground: IO exception reading dta" +e.getMessage());
        }catch(SecurityException e){
            Log.e(TAG, "doinBackground: needs permission?" + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "doInBackground: error closing stream" + e.getMessage());

                }
            }

        }


        mDownloadStatus = DownloadStatus.FAILED_OR_EMPTY;

    return null;
    }
}
