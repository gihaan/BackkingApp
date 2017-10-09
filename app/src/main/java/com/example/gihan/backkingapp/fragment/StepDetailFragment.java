package com.example.gihan.backkingapp.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.PlaybackStateCompat;
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
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class StepDetailFragment extends Fragment implements ExoPlayer.EventListener {

    TextView mDescrption;
    Button mNext;
    ImageView recipImage;
    SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer player;
    private RecipsSteps recip;

    List<RecipsSteps> mList;
    int flag;
    int cursor;
    String imageUrl;
    private static long position = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_step_detail, container, false);

        mList = new ArrayList<>();
        mList.clear();
        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = getActivity().getIntent().getExtras();
        }
        mList = bundle.getParcelableArrayList("list");
        recip = bundle.getParcelable("item");

        mDescrption = (TextView) v.findViewById(R.id.item_detail_tv_fulldesc);
        mNext = (Button) v.findViewById(R.id.item_detail_btn_next);
        recipImage = (ImageView) v.findViewById(R.id.step_image);
        simpleExoPlayerView = (SimpleExoPlayerView) v.findViewById(R.id.video_display);


        mDescrption.setText(recip.getFullDescrptionOfStep());

        //-------------IMAGE-----------------------------------
        if (!recip.getThumpUrl().equals("")) {

            imageUrl = recip.getThumpUrl();
            Picasso.with(getContext()).load(imageUrl).placeholder(R.drawable.gigi).into(recipImage);
        } else {
            Picasso.with(getContext()).load(imageUrl).placeholder(R.drawable.example_appwidget_preview).into(recipImage);

          //  recipImage.setVisibility(View.GONE);
        }
        //----------------------Set Video Url-----------------
        simpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.widget));
        simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);

        String videoUrl = recip.getVideoUrl();
        if (!videoUrl.equals("")) {
            initializePlayer(Uri.parse(recip.getVideoUrl()));
            simpleExoPlayerView.setVisibility(View.VISIBLE);
            restExoPlayerAfterRotation(0, false);

            if ((savedInstanceState != null) && savedInstanceState.containsKey("pos")) {
                position = savedInstanceState.getLong("pos");
                player.seekTo(position);
            }

        } else {
            simpleExoPlayerView.setVisibility(View.GONE);
        }

        flag = recip.getStepID();
        cursor = flag + 1;

        mNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cursor < mList.size()) {
                    recip = mList.get(cursor);
                    mDescrption.setText(recip.getFullDescrptionOfStep());

                    if (!recip.getThumpUrl().equals("")) {
                        recipImage.setVisibility(View.VISIBLE);
                        imageUrl = recip.getThumpUrl();
                        Picasso.with(getContext()).load(recip.getThumpUrl()).placeholder(R.drawable.gigi).into(recipImage);

                    } else {

                        Picasso.with(getContext()).load(imageUrl).placeholder(R.drawable.example_appwidget_preview).into(recipImage);

                      //  recipImage.setVisibility(View.GONE);

                    }

                    ///////////////--------VIDEO ------------------
                    if (player != null) {
                        player.stop();
                        player.release();
                        player = null;
                    }

                    if (!recip.getVideoUrl().equals("")) {
                        initializePlayer(Uri.parse(recip.getVideoUrl()));
                        simpleExoPlayerView.setVisibility(View.VISIBLE);
                        restExoPlayerAfterRotation(0, false);
                    } else {
                        simpleExoPlayerView.setVisibility(View.GONE);

                    }
                    //----------------------------------------------
                    cursor++;
                    if (cursor == mList.size()) {
                        cursor = 0;
                    }
                }
            }
        });


        if (savedInstanceState != null) {
            if (player != null) {
                player.seekTo(position);
                player.setPlayWhenReady(true);
            }
        }
        //----------------landscape--------------------------------------
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        if (width > height) {
            mDescrption.setVisibility(View.GONE);
            mNext.setVisibility(View.GONE);
            recipImage.setVisibility(View.GONE);

            simpleExoPlayerView.setMinimumHeight(height);
            simpleExoPlayerView.setMinimumWidth(height);
            restExoPlayerAfterRotation(position, true);

        }
        //-------------------------------------------------------------------

        return v;
    }

    private void restExoPlayerAfterRotation(long position, boolean playWhenReady) {
        this.position = position;
        if (player != null) {
            player.seekTo(position);
            player.setPlayWhenReady(playWhenReady);
        }
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (player != null)
            outState.putLong("pos", player.getCurrentPosition());
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
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (playbackState == PlaybackStateCompat.STATE_PLAYING || playbackState == PlaybackStateCompat.STATE_PAUSED) {
            position = player.getCurrentPosition();
        }
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

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
