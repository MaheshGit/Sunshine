package com.example.android.sunshine.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MKS on 25-12-2014.
 */
public class ForecastFragment extends Fragment
{
    String [] weatherDataArray= {"Today - Sunny - 88/63","Tomorrow - Foggy - 70/46","Weds - Cloudy - 72/63"};

    public ForecastFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forcast);
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(weatherDataArray));
        ArrayAdapter<String> mForecastAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_forcast,
                R.id.list_item_forcast_textview,
                weekForecast);
        listView.setAdapter(mForecastAdapter);
        new FetchWeatherTask().execute();
        return rootView;
    }
}
