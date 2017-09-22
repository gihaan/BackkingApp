package com.example.gihan.backkingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.model.RecipsSteps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gihan on 9/21/2017.
 */

public class  RecyclerAdapterRecipsDetail extends RecyclerView.Adapter< RecyclerAdapterRecipsDetail.RecyclerviewHolder> {

    private List<RecipsSteps> mList=new ArrayList<RecipsSteps>();
    private Context ctx;

    public  RecyclerAdapterRecipsDetail(List<RecipsSteps>mList, Context ctx){
        this.ctx=ctx;
        this.mList=mList;
    }

    @Override
    public RecyclerviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent,false);

        RecyclerviewHolder holder=new RecyclerviewHolder(v,ctx,mList);



        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerviewHolder holder, int position) {

       holder.mItempNmae.setText(mList.get(position).getShortDescrptionOfStep());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /***** ------------------------Creating OnItemClickListener -----------------------*****/
    // Define listener member variable
    public static RecyclerAdapterRecipsDetail.OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(RecyclerAdapterRecipsDetail.OnItemClickListener listener) {
        this.listener = listener;
    }
    //----------------------------------------------------------------------


    public static class RecyclerviewHolder extends RecyclerView.ViewHolder {

       TextView mItempNmae;

        private List<RecipsSteps> mList=new ArrayList<>();
        private Context ctx;


        public RecyclerviewHolder(View view,Context ctx,List<RecipsSteps> mList){
            super(view);
            this.ctx=ctx;
            this.mList=mList;


            mItempNmae=(TextView)view.findViewById(R.id.step_item_short_desc);

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