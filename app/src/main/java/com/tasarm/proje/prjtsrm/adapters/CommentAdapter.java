package com.tasarm.proje.prjtsrm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tasarm.proje.prjtsrm.models.CommentModel;
import com.tasarm.proje.prjtsrm.R;

import java.util.ArrayList;

/**
 * Created by User on 03.03.2018.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {


    Context context;
    ArrayList<CommentModel> data;

    LayoutInflater inflater;
    public CommentAdapter(Context context, ArrayList<CommentModel> data) {
        this.context=context;
        this.data=data;
        notifyItemRangeChanged(0,data.size());
        inflater=LayoutInflater.from(context);

    }
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.comment_item,parent,false);

        CommentAdapter.MyViewHolder holder=new CommentAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CommentAdapter.MyViewHolder holder, int position) {

        CommentModel commentModel=data.get(position);
        holder.gonderen.setText(commentModel.getGonderen());
        holder.icerik.setText(commentModel.getIcerik());
        holder.tarih.setText(commentModel.getTarih());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView gonderen,icerik,tarih;

        public MyViewHolder(View itemView) {
            super(itemView);
            gonderen=(TextView)itemView.findViewById(R.id.textView6);
            icerik=(TextView)itemView.findViewById(R.id.textView7);
            tarih=(TextView)itemView.findViewById(R.id.textView8);

        }
    }
}
