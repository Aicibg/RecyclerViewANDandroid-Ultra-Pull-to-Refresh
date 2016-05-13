package com.example.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dellidc on 2016/5/11.
 */
public class RecyclerAdapter extends RecyclerView.Adapter {
    private List<String> strs;

    public RecyclerAdapter(List<String> strs) {
        this.strs = strs;
    }
   class ViewHolder extends RecyclerView.ViewHolder{
       public TextView tvTitle;
       public ImageView ivPhoto;
       public Button btN;

       public ViewHolder(View itemView) {
           super(itemView);
           tvTitle= (TextView) itemView.findViewById(R.id.textView);
           ivPhoto= (ImageView) itemView.findViewById(R.id.imageView);
           btN= (Button) itemView.findViewById(R.id.button);
       }
   }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder=new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item,null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
           ViewHolder holder= (ViewHolder) viewHolder;
           holder.btN.setText(strs.get(i));
           holder.tvTitle.setText(strs.get(i));
    }

    @Override
    public int getItemCount() {
        return strs.size();
    }
}
