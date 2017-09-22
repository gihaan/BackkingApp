package com.example.gihan.backkingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gihan.backkingapp.activity.RecipsDetail;
import com.example.gihan.backkingapp.model.Recips;
import com.example.gihan.backkingapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gihan on 7/28/2017.
 */

public class RecyclerAdapterItems extends RecyclerView.Adapter<RecyclerAdapterItems.RecyclerviewHolder> {

    private List<Recips> mList=new ArrayList<Recips>();
     Context ctx;

    public RecyclerAdapterItems(List<Recips>mList, Context ctx){
        this.ctx=ctx;
       this.mList=mList;
    }

    @Override
    public RecyclerviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_row,parent,false);
        RecyclerviewHolder holder=new RecyclerviewHolder(v,ctx,mList);

        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerviewHolder holder, final int position) {

        Recips object=mList.get(position);
        holder.mRecipNmae.setText(mList.get(position).getRecipsName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recips r=mList.get(position);
                Intent i=new Intent(v.getContext(), RecipsDetail.class);
                i.putExtra("recip", (Serializable) r);


                try {
                   holder.ctx.startActivity(i);


                }catch (Exception e){
                    String ss=e.toString();
                }


            }
        });
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class RecyclerviewHolder extends RecyclerView.ViewHolder {

        private TextView mRecipNmae;

        private List<Recips> mList=new ArrayList<>();
        private Context ctx;


        public RecyclerviewHolder(View view,Context ctx,List<Recips> mList){
            super(view);
            this.ctx=ctx;
            this.mList=mList;


           mRecipNmae=(TextView)view.findViewById(R.id.recip_name);



        }

    }
}
