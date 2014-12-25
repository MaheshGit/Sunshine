package com.example.android.sunshine.app;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by MKS on 25-12-2014.
 */
public class FetchWeatherTask extends AsyncTask<Void , Void ,String> {
    private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();
    @Override
    protected String doInBackground(Void... voids) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String forecastJsonStr = null;
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null){
                return  null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine())!= null){
                buffer.append(line + "\n");
            }
            if(buffer.length()==0){
                return  null;
            }
            forecastJsonStr = buffer.toString();
            return  forecastJsonStr;
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error ", e);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }

            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return null;
    }
}
