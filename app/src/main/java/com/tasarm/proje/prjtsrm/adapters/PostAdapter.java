package com.tasarm.proje.prjtsrm.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tasarm.proje.prjtsrm.activitys.PostComment;
import com.tasarm.proje.prjtsrm.models.PostModel;
import com.tasarm.proje.prjtsrm.R;

import java.util.ArrayList;

/**
 * Created by User on 20.02.2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    Context context;
    ArrayList<PostModel> data;

    LayoutInflater inflater;
    public PostAdapter(Context context, ArrayList<PostModel> data) {
        this.context=context;
        this.data=data;
        notifyItemRangeChanged(0,data.size());
        inflater=LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view=inflater.inflate(R.layout.post_item,parent,false);

        MyViewHolder holder=new MyViewHolder(view,position);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        PostModel post=data.get(position);
        if(post.getDurum().equals("0")){


          holder.dis.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.darkdislike1, 0);




        }
        else if(post.getDurum().equals("1")){

            holder.like.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.darklike1, 0);
        }
        else {


        }
        holder.baslik.setText(post.getBaslik());
        holder.tarih.setText(post.getTarih());
        holder.gonderen.setText(post.getGonderen());
        holder.com_sayi.setText(post.getCom_sayi());
        holder.dis.setText(post.getDislike());
        holder.like.setText(post.getLike_sayi());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        TextView baslik, tarih, gonderen,com_sayi,dis,like;


        public MyViewHolder(View itemView, int positions) {
            super(itemView);
           itemView.setOnClickListener(this);
            baslik = (TextView) itemView.findViewById(R.id.baslik);
            tarih = (TextView) itemView.findViewById(R.id.tarih);
            gonderen = (TextView) itemView.findViewById(R.id.gonderen);
            com_sayi = (TextView) itemView.findViewById(R.id.textView11);
            dis = (TextView) itemView.findViewById(R.id.dis);
            like = (TextView) itemView.findViewById(R.id.like);
        }


        @Override
        public void onClick(View view) {
            int positions=getAdapterPosition();
            PostModel postModel=data.get(positions);
            Intent inet=new Intent(context,PostComment.class);
            inet.putExtra("id",postModel.getId());
            inet.putExtra("baslik",postModel.getBaslik());
            inet.putExtra("icerik",postModel.getIcerik());
            inet.putExtra("gonderen",postModel.getGonderen());
            inet.putExtra("tarih",postModel.getTarih());
            inet.putExtra("com_sayi",postModel.getCom_sayi());
            inet.putExtra("like",postModel.getLike_sayi());
            inet.putExtra("dis",postModel.getDislike());
            inet.putExtra("durum",postModel.getDurum());

            context.startActivity(inet);

        }
    }

}
