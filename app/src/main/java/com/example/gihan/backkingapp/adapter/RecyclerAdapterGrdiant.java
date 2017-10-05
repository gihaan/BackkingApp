package com.example.gihan.backkingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.model.Recips;
import com.example.gihan.backkingapp.model.RecipsIngerdiant;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Gihan on 9/21/2017.
 */


public class RecyclerAdapterGrdiant extends RecyclerView.Adapter<RecyclerAdapterGrdiant.RecyclerviewHolder> {

    private List<RecipsIngerdiant> mList = new ArrayList<RecipsIngerdiant>();
    private Context ctx;

    public RecyclerAdapterGrdiant(List<RecipsIngerdiant> mList, Context ctx) {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public RecyclerviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingerdiant_item, parent, false);

        RecyclerviewHolder holder = new RecyclerviewHolder(v, ctx, mList);


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerviewHolder holder, int position) {

        try{
            String vv=mList.get(position).getIngrediantQuality();
            holder.mGrdiantQuantity.setText( mList.get(position).getIngrediantQuality());

        }catch (Exception e){
            String dd=e.toString();
        }

        holder.mGrdiantMeaure.setText(mList.get(position).getMeaureOfIngerdiant());
        holder.mGrdiantName.setText(mList.get(position).getIngerdiantName());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class RecyclerviewHolder extends RecyclerView.ViewHolder {

        TextView mGrdiantQuantity;
        TextView mGrdiantMeaure;
        TextView mGrdiantName;

        private List<RecipsIngerdiant> mList = new ArrayList<>();
        private Context ctx;


        public RecyclerviewHolder(View view, Context ctx, List<RecipsIngerdiant> mList) {
            super(view);
            this.ctx = ctx;
            this.mList = mList;


            mGrdiantQuantity = (TextView) view.findViewById(R.id.grdiant_item_quantity);
            mGrdiantMeaure = (TextView) view.findViewById(R.id.grdiant_item_measure);
            mGrdiantName = (TextView) view.findViewById(R.id.grdiant_item_ingridient_name);


        }

    }
}