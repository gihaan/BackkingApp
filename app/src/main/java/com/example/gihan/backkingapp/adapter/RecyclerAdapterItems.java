package com.example.gihan.backkingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gihan.backkingapp.activity.RecipsDetail;
import com.example.gihan.backkingapp.model.Recips;
import com.example.gihan.backkingapp.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gihan on 7/28/2017.
 */

public class RecyclerAdapterItems extends RecyclerView.Adapter<RecyclerAdapterItems.RecyclerviewHolder> {

    private List<Recips> mList = new ArrayList<Recips>();
    Context ctx;

    public RecyclerAdapterItems(List<Recips> mList, Context ctx) {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public RecyclerviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_row, parent, false);
        RecyclerviewHolder holder = new RecyclerviewHolder(v, ctx, mList);

        return holder;
    }


    @Override
    public void onBindViewHolder(final RecyclerviewHolder holder, final int position) {

        Recips object = mList.get(position);
        holder.mRecipNmae.setText(mList.get(position).getRecipsName());
        holder.mRecipVersion.setText(mList.get(position).getServing());
        if(!mList.get(position).getImage().equals("")){
            Picasso.with(ctx).load(mList.get(position).getImage()).into(holder.mRecipImage);


        }

           }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    /***** ------------------------Creating OnItemClickListener -----------------------*****/
    // Define listener member variable
    public static  OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    //----------------------------------------------------------------------



    public static class RecyclerviewHolder extends RecyclerView.ViewHolder {

       private TextView mRecipNmae;
        private TextView mRecipVersion;
        private ImageView mRecipImage;
        private List<Recips> mList = new ArrayList<>();
        private Context ctx;

        public RecyclerviewHolder(View view, Context ctx, List<Recips> mList) {
            super(view);
            this.ctx = ctx;

            mRecipNmae = (TextView) view.findViewById(R.id.recip_name);
            mRecipVersion=(TextView)view.findViewById(R.id.recip_version);
            mRecipImage=(ImageView)view.findViewById(R.id.recip_image);

            // Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });



        }

    }
}
