package com.example.gihan.backkingapp.fragment;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.activity.StepDetail;
import com.example.gihan.backkingapp.model.RecipsSteps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class StepDetailFragment extends Fragment {

    TextView mDescrption;
    Button mNext;
    Button mPrevious;
    ImageView recipImage;
    SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer player;

    LoadControl loadControl;

    List<RecipsSteps> mList = new ArrayList<>();
    int flag;
    int cursor;
    int cursor1;
    String imageUrl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_step_detail, container, false);

        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = getActivity().getIntent().getExtras();
        }
        mList.clear();
        mList = (List<RecipsSteps>) bundle.getSerializable("list");
        final RecipsSteps object = (RecipsSteps) bundle.getSerializable("item");

        mDescrption = (TextView) v.findViewById(R.id.item_detail_tv_fulldesc);
        mNext = (Button) v.findViewById(R.id.item_detail_btn_next);
        mPrevious = (Button) v.findViewById(R.id.item_detail_btn_previous);
        recipImage = (ImageView) v.findViewById(R.id.step_image);
        simpleExoPlayerView = (SimpleExoPlayerView) v.findViewById(R.id.video_display);


        //---------------------------------------------------------------------
        simpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.gigi));
        simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
        initializePlayer(Uri.parse(object.getVideoUrl()));

        if (object.getVideoUrl() != "") {

            simpleExoPlayerView.setVisibility(View.VISIBLE);


        } else {
            simpleExoPlayerView.setVisibility(View.GONE);
            recipImage.setVisibility(View.GONE);
        }


//------------------------------------------------


        mDescrption.setText(object.getFullDescrptionOfStep());

        if (object.getThumpUrl().equals("")) {
        } else {
            imageUrl = object.getThumpUrl();
            recipImage.setVisibility(View.VISIBLE);
            Picasso.with(getContext()).load(imageUrl).placeholder(R.drawable.gigi).into(recipImage);
        }
        flag = object.getStepID();
        cursor = flag + 1;
        int x = cursor;
        cursor1 = x;

        mNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cursor < mList.size()) {
                    RecipsSteps recip = mList.get(cursor);
                    mDescrption.setText(recip.getFullDescrptionOfStep());
                    if (recip.getThumpUrl().equals("")) {
                    } else {
                        recipImage.setVisibility(View.VISIBLE);

                        imageUrl = recip.getThumpUrl();
                        Picasso.with(getContext()).load(imageUrl).placeholder(R.drawable.gigi).into(recipImage);

                    }

                    ///////////////--------VIDEO ------------------
                    initializePlayer(Uri.parse(recip.getVideoUrl()));
                    simpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.gigi));
                    simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);


                    if (recip.getVideoUrl() != "") {
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

        mPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cursor1 > -1) {
                    RecipsSteps recip = mList.get(cursor1);
                    mDescrption.setText(recip.getFullDescrptionOfStep());
                    if (recip.getThumpUrl().equals("")) {

                    } else {
                        recipImage.setVisibility(View.VISIBLE);

                        imageUrl = recip.getThumpUrl();
                        Picasso.with(getContext()).load(imageUrl).placeholder(R.drawable.gigi).into(recipImage);


                    }


                    ///////////////--------VIDEO ------------------
                    initializePlayer(Uri.parse(recip.getVideoUrl()));
                    simpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.gigi));
                    simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);


                    if (recip.getVideoUrl() != "") {
                        simpleExoPlayerView.setVisibility(View.VISIBLE);
                    } else {
                        simpleExoPlayerView.setVisibility(View.GONE);
                        recipImage.setVisibility(View.GONE);
                    }
                    //----------------------------------------------

                    cursor--;
                    if (cursor1 == -1) {
                        cursor1 = mList.size() - 1;
                    }
                }
            }
        });

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
