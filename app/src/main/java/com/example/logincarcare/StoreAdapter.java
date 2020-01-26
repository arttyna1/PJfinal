package com.example.logincarcare;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder>{
    private List<modelstore> car;
    private View carview;
    private Context context;

    public StoreAdapter(List<modelstore> car, Context context){
        this.car = car;
        this.context = context;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatar;
        private TextView name,country;
        public MyViewHolder(@NonNull View view) {
            super(view);
            avatar = view.findViewById(R.id.imageView);
            name = view.findViewById(R.id.namestore);
            country = view.findViewById(R.id.country);
        }
    }
    @NonNull
    @Override
    public StoreAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        carview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.showstore,parent,false);
        return new StoreAdapter.MyViewHolder(carview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final modelstore md = car.get(position);
        GlideApp.with(carview.getContext()).load(md.getPath()).into(holder.avatar);
        holder.country.setText(md.getCounty());
        holder.name.setText(md.getNamestore());
    }

    @Override
    public int getItemCount() {
        return car.size();
    }
}
