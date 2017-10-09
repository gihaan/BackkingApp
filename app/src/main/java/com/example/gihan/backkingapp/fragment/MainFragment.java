package com.example.gihan.backkingapp.fragment;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.gihan.backkingapp.ContentProvider.RecipsProvider;
import com.example.gihan.backkingapp.activity.RecipsDetail;
import com.example.gihan.backkingapp.adapter.RecyclerAdapterItems;
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
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.gihan.backkingapp.activity.MainActivity.TABLET_MODE;


public class MainFragment extends Fragment {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapterItems mAdapter;
    List<Recips> mList = new ArrayList<>();
    List<RecipsIngerdiant> ingerdiant = new ArrayList<>();
    List<RecipsSteps> steps = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mToolbar = (Toolbar) v.findViewById(R.id.backing_time_toolbar);
        mToolbar.setTitle("Baking Time");

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler);

        if (TABLET_MODE == 0) {
            mLayoutManager = new GridLayoutManager(getContext(), 1);


        } else {
            mLayoutManager = new GridLayoutManager(getContext(), 3);

        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        GetDataFromJson ob = new GetDataFromJson();
        ob.execute();


        return v;
    }


    private class GetDataFromJson extends AsyncTask<Void, Void, List<Recips>> {


        @Override
        protected void onPostExecute(List<Recips> recipses) {
            mAdapter = new RecyclerAdapterItems(mList, getContext());
            mRecyclerView.setAdapter(mAdapter);

            mAdapter.setOnItemClickListener(new RecyclerAdapterItems.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {

                    Recips r = mList.get(position);
                    Intent i = new Intent(getContext(), RecipsDetail.class);
                    i.putParcelableArrayListExtra("reciprecip", r);
                    i.putParcelableArrayListExtra("step", (ArrayList<? extends Parcelable>) mList.get(position).getRecipsSteps());

                    i.putExtra("gerdiant", (Serializable) mList.get(position).getRecipsIngerdiant());
                    startActivity(i);


                }
            });

            super.onPostExecute(mList);
        }

        @Override
        protected List<Recips> doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String recipJsonSt = null;
            try {
                URL url = new URL("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

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

                    String e = ex.toString();
                    Log.e(ex.toString(), "Error while handel Json ", ex);


                }

            } catch (IOException e) {
                Log.e(e.toString(), "recips json string ", e);
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
            final String SERVINGS="servings";
            final String IMAGE="image";
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

            try {

                //  JSONObject recipJson = new JSONObject(recipsJsonSt);
                JSONArray recipsArray = new JSONArray(recipsJsonSt);


                //---------------RECIPS----------------------

                for (int i = 0; i < recipsArray.length(); i++) {

                    JSONObject rec = recipsArray.getJSONObject(i);

                    Recips recips = new Recips();
                    recips.setRecipsID(rec.getInt(RECIP_ID));
                    recips.setRecipsName(rec.getString(NAME));



                    //---------------INGERDIANT----------------------
                    // JSONObject recipIngerdiantJson = new JSONObject(recipsJsonSt);
                    JSONArray recipIngerdiantsArray = rec.getJSONArray("ingredients");
                    ingerdiant.clear();
                    for (int k = 0; k < recipIngerdiantsArray.length(); k++) {


                        RecipsIngerdiant ob = new RecipsIngerdiant();
                        JSONObject recipIntegr = recipIngerdiantsArray.getJSONObject(k);

                        ob.setIngrediantQuality(recipIntegr.getString(QUANTITY));
                        String ff=recipIntegr.getString(QUANTITY);
                        ob.setMeaureOfIngerdiant(recipIntegr.getString(MEAURE));
                        ob.setIngerdiantName(recipIntegr.getString(INGERDIANT));

                        ingerdiant.add(ob);

                    }

                    //---------------STEPS----------------------

                    JSONArray recipStepsArray = rec.getJSONArray("steps");
                    steps.clear();
                    for (int j = 0; j < recipStepsArray.length(); j++) {
                        RecipsSteps object = new RecipsSteps();

                        JSONObject recipStep = recipStepsArray.getJSONObject(j);

                        object.setStepID(recipStep.getInt(RECIP_ID));
                        object.setShortDescrptionOfStep(recipStep.getString(SHORT_DESCRPTION));
                        object.setFullDescrptionOfStep(recipStep.getString(DESCRPTION));
                        object.setVideoUrl(recipStep.getString(VIDEO_URL));
                        object.setThumpUrl(recipStep.getString(THUMP_NAIL_URL));

                        steps.add(object);
                    }


                    recips.setRecipsIngerdiant(ingerdiant);
                    recips.setRecipsSteps(steps);
                    recips.setServing(rec.getString(SERVINGS));
                    recips.setImage(rec.getString(IMAGE));

                    mList.add(i, recips);


                }

            } catch (Exception e) {
                String x = e.toString();

            }


            return mList;
        }
    }


}