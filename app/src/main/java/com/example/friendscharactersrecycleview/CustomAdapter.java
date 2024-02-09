package com.example.friendscharactersrecycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter {
    private ArrayList<DataModel> dataSet;
    public CustomAdapter(ArrayList<DataModel> dataSet) {
        this.dataSet=dataSet;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewDescription;
        ImageView imageView;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            textViewName=itemView.findViewById(R.id.characterName);
            textViewDescription=itemView.findViewById(R.id.characterDecription);
            imageView=itemView.findViewById(R.id.charPicture);
        }
    }

    public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void onBindViewHolder(CustomAdapter.MyViewHolder holder,int position){
        TextView textViewName=holder.textViewName;
        TextView textViewDescription=holder.textViewDescription;
        ImageView imageView=holder.imageView;

        textViewName.setText(dataSet.get(position).getName());
        textViewDescription.setText(dataSet.get(position).getName());
        imageView.setImageResource(dataSet.get(position).getImage());
    }
}
