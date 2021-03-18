package com.example.firebasetuts.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasetuts.R;

import java.util.List;

public class recViewAdapter extends RecyclerView.Adapter<recViewAdapter.ViewHolder> {

    List<Model> list ;

    public recViewAdapter(List<Model> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row , parent , false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView1.setText(list.get(position).getUid());
        holder.textView2.setText(list.get(position).getLoginInfo());
        //holder.imageView.setImageResource(list.get(position).getImageView());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView1 , textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


             //imageView = itemView.findViewById(R.id.imageView);
             textView1 = itemView.findViewById(R.id.textView);
             textView2 = itemView.findViewById(R.id.textView2);

        }
    }
}
