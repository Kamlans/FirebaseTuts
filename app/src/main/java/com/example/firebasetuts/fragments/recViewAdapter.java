package com.example.firebasetuts.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebasetuts.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class recViewAdapter extends RecyclerView.Adapter<recViewAdapter.ViewHolder> {

    ArrayList<Model> list ;

    public recViewAdapter(ArrayList<Model> list) {
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



        holder.textView1.setText(list.get(position).getFirst());
        holder.textView2.setText(list.get(position).getLast());
        Picasso.get().load(list.get(position).getImg()).into(holder.imageView);


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


             imageView = itemView.findViewById(R.id.imageView);
             textView1 = itemView.findViewById(R.id.textView);
             textView2 = itemView.findViewById(R.id.textView2);

        }
    }
}
