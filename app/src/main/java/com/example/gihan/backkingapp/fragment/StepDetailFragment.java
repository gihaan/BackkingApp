package com.example.gihan.backkingapp.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gihan.backkingapp.ContentProvider.RecipsProvider;
import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.model.RecipsSteps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class StepDetailFragment extends Fragment {

    TextView mDescrption;
    Button mNext;
    ImageView recipImage;
    SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer player;
    private RecipsSteps recip;

    LoadControl loadControl;

    List<RecipsSteps> mList ;
    int flag;
    int cursor;

    String imageUrl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_step_detail, container, false);

        mList=new ArrayList<>();
        mList.clear();
        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = getActivity().getIntent().getExtras();
        }
        mList = bundle.getParcelableArrayList("list");
        recip =  bundle.getParcelable("item");

        mDescrption = (TextView) v.findViewById(R.id.item_detail_tv_fulldesc);
        mNext = (Button) v.findViewById(R.id.item_detail_btn_next);
        recipImage = (ImageView) v.findViewById(R.id.step_image);
        simpleExoPlayerView = (SimpleExoPlayerView) v.findViewById(R.id.video_display);


        mDescrption.setText(recip.getFullDescrptionOfStep());

        //----------------------Set Video Url-----------------
        simpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.gigi));
        simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
        //initializePlayer(Uri.parse(recip.getVideoUrl()));

        String videoUrl=recip.getVideoUrl();
        if (! videoUrl.equals("")) {
            initializePlayer(Uri.parse(recip.getVideoUrl()));
            simpleExoPlayerView.setVisibility(View.VISIBLE);
        } else {
            simpleExoPlayerView.setVisibility(View.GONE);
            recipImage.setVisibility(View.GONE);
        }
//------------------------------------------------

        if (recip.getThumpUrl().equals("")) {
        } else {
            imageUrl = recip.getThumpUrl();
            recipImage.setVisibility(View.VISIBLE);
            Picasso.with(getContext()).load(imageUrl).placeholder(R.drawable.gigi).into(recipImage);
        }
        flag = recip.getStepID();
        cursor = flag + 1;

        mNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cursor < mList.size()) {
                     recip = mList.get(cursor);
                    mDescrption.setText(recip.getFullDescrptionOfStep());

                    if (recip.getThumpUrl().equals("")) {
                    } else {
                        recipImage.setVisibility(View.VISIBLE);
                        imageUrl = recip.getThumpUrl();
                        Picasso.with(getContext()).load(imageUrl).placeholder(R.drawable.gigi).into(recipImage);

                    }

                    ///////////////--------VIDEO ------------------
                    if (player != null) {
                        player.stop();
                        player.release();
                        player = null;
                    }
//                    simpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.widget));
//                    simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
//                    initializePlayer(Uri.parse(recip.getVideoUrl()));
                    if (!recip.getVideoUrl() .equals("")) {
                        initializePlayer(Uri.parse(recip.getVideoUrl()));
                        simpleExoPlayerView.setVisibility(View.VISIBLE);
                    } else {
                        simpleExoPlayerView.setVisibility(View.GONE);
                        recipImage.setVisibility(View.GONE);
                    }
                    //----------------------------------------------
                    cursor++;
                    if (cursor == mList.size()) {
                        cursor = 0;
                    }
                }
            }
        });
        //----------------landscape--------------------------------------
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if (width > height) {
            mDescrption.setVisibility(View.INVISIBLE);
            mNext.setVisibility(View.INVISIBLE);
            recipImage.setVisibility(View.INVISIBLE);
            simpleExoPlayerView.setMinimumWidth(height);
            simpleExoPlayerView.setMinimumHeight(width);

        }
        //-------------------------------------------------------------------

        /////////////SAVE DATA for widget---------------
        try {


            for (int j = 0; j < mList.size(); j++) {
                recip = mList.get(j);

                ContentValues values = new ContentValues();

                values.put(RecipsProvider.RECIP_ID, j);
                values.put(RecipsProvider.STEP_ID, recip.getStepID());
                values.put(RecipsProvider.SHORT_DESC, recip.getShortDescrptionOfStep());
                values.put(RecipsProvider.FULL_DESC, recip.getFullDescrptionOfStep());
                values.put(RecipsProvider.VIDEO_URL, recip.getVideoUrl());
                values.put(RecipsProvider.THUMP_URL, recip.getThumpUrl());


                //////////
                Cursor CR = getContext().getContentResolver().query(RecipsProvider.CONTENT_URI, null, null, null, null);
                int flag = 0;
                CR.moveToFirst();
                if (CR == null)

                    while ((CR.moveToNext())) {
                        if ( recip.getStepID() == CR.getInt(3)) {
                            flag = 1;
                        }
                    }
                if (flag == 0) {

                    Uri uri = getContext().getContentResolver().insert(RecipsProvider.CONTENT_URI, values);
                }
            }


        } catch (Exception e) {
            String uu = e.toString();
        }

        //---------------------------------------------------------------------

        return v;
    }


    private void initializePlayer(Uri mediaUri) {
        if (player == null) {
            //create an instance of the Exoplayer
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            player = ExoPlayerFactory.newSimpleInstance(this.getContext(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(player);
            //prepare the MediaSource
            String userAgent = Util.getUserAgent(this.getContext(), "RecipeStep");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri,
                    new DefaultDataSourceFactory(this.getContext(), userAgent)
                    , new DefaultExtractorsFactory(), null, null);
            player.prepare(mediaSource);
            player.setPlayWhenReady(true);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (player != null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (player != null) {
            player.stop();
            player.release();
        }

    }

}
