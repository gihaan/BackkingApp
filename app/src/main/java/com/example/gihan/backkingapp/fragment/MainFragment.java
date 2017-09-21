package com.example.gihan.backkingapp.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.gihan.backkingapp.adapter.RecyclerAdapterRecips;
import com.example.gihan.backkingapp.model.Recips;
import com.example.gihan.backkingapp.model.RecipsIngerdiant;
import com.example.gihan.backkingapp.model.RecipsSteps;
import com.example.gihan.backkingapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class MainFragment extends Fragment {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapterRecips mAdapter;
    List<Recips> mList;
    List<RecipsIngerdiant> ingerdiant;
    List<RecipsSteps> steps;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mToolbar = (Toolbar) v.findViewById(R.id.backing_time_toolbar);
        mToolbar.setTitle("Baking Time");

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        GetDataFromJson ob = new GetDataFromJson();
        mList = ob.doInBackground();

//            mAdapter = new RecyclerAdapterRecips(mList, getContext());
//            mRecyclerView.setAdapter(mAdapter);




        return v;
    }


    private class GetDataFromJson extends AsyncTask<Object, Object, List<Recips>> {


        @Override
        protected List<Recips> doInBackground(Object... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String recipJsonSt = null;
            try {
                URL url = new URL("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                try {
                    urlConnection.connect();

                }catch (Exception ex){
                    String y=ex.toString();

                }

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    recipJsonSt = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    //READ LINE
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    recipJsonSt = null;
                }
                recipJsonSt = buffer.toString();
                try {
                    return getRecipsFromString(recipJsonSt);


                } catch (Exception ex) {

                    Log.e(ex.toString(), "Error while handel Json ", ex);

                }

            } catch (IOException e) {
                Log.e(e.toString(), "movie json string ", e);
                // If the code didn't successfully get the weather data
                String x = e.getMessage().toString();
                recipJsonSt = null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(e.toString(), "Error closing stream", e);
                    }
                }

                return null;
            }


        }

        //--------------------------------------------------------------------------------

        private List<Recips> getRecipsFromString(String recipsJsonSt)
                throws JSONException {

            //---------DATA FOR RECIPS----------------------
            final String RECIP_ID = "id";
            final String NAME = "name";
            final String RECIPS_INGERDIANT = "ingredients";
            final String RECIPS_STEPS = "steps";

            //---------DATA FOR RECIPS STEPS----------------
            final String SHORT_DESCRPTION = "shortDescription";
            final String DESCRPTION = "description";
            final String VIDEO_URL = "videoURL";
            final String THUMP_NAIL_URL = "thumbnailURL";

            //---------DATA FOR RECIPS INGERDIANT-----------

            final String QUANTITY = "quantity";
            final String MEAURE = "measure";
            final String INGERDIANT = "ingredient";

            JSONObject recipJson = new JSONObject(recipsJsonSt);
            JSONArray recipsArray = recipJson.getJSONArray("");


            //---------------RECIPS----------------------

            for (int i = 0; i < recipsArray.length(); i++) {

                JSONObject rec = recipsArray.getJSONObject(i);
                Recips recips = new Recips();
                recips.setRecipsID(rec.getString(RECIP_ID));
                recips.setRecipsName(rec.getString(NAME));


                //---------------INGERDIANT----------------------
                JSONObject recipIngerdiantJson = new JSONObject(recipsJsonSt);
                JSONArray recipIngerdiantsArray = recipJson.getJSONArray("ingredients");
                for (int k = 0; k < recipIngerdiantsArray.length(); k++) {


                    RecipsIngerdiant ob = new RecipsIngerdiant();
                    JSONObject recipIntegr = recipIngerdiantsArray.getJSONObject(k);

                    ob.setIngerdiantName(recipIntegr.getString(INGERDIANT));
                    ob.setMeaureOfIngerdiant(recipIntegr.getString(MEAURE));
                    ob.setIngrediantQuality(recipIntegr.getString(QUANTITY));

                    ingerdiant.add(ob);

                }

                //---------------STEPS----------------------

                JSONObject recipStepstJson = new JSONObject(recipsJsonSt);
                JSONArray recipStepsArray = recipJson.getJSONArray("steps");
                for (int j = 0; j < recipIngerdiantsArray.length(); j++) {


                    RecipsSteps object = new RecipsSteps();
                    JSONObject recipStep = recipIngerdiantsArray.getJSONObject(j);

                    object.setFullDescrptionOfStep(recipStep.getString(DESCRPTION));
                    object.setShortDescrptionOfStep(recipStep.getString(SHORT_DESCRPTION));
                    object.setVideoUrl(recipStep.getString(VIDEO_URL));
                    object.setThumpUrl(recipStep.getString(THUMP_NAIL_URL));
                    object.setStepID(recipStep.getString(RECIP_ID));

                    steps.add(object);
                }


                recips.setRecipsIngerdiant(ingerdiant);
                recips.setRecipsSteps(steps);

                mList.add(recips);
            }

            return mList;
        }
    }


}