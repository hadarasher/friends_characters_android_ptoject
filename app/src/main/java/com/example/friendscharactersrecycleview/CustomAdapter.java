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
        this.dataSet = dataSet;
    }

    public void setFilteredList(ArrayList<DataModel> filteredList){
        this.dataSet=filteredList;
        notifyDataSetChanged();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDescription;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.characterName);
            textViewDescription = itemView.findViewById(R.id.characterDecription);
            imageView = itemView.findViewById(R.id.charPicture);
        }
    }

    @NonNull
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        TextView textViewName = myViewHolder.textViewName;
        TextView textViewDescription = myViewHolder.textViewDescription;
        ImageView imageView = myViewHolder.imageView;

        textViewName.setText(dataSet.get(position).getName());
        textViewDescription.setText(dataSet.get(position).getDescription());
        imageView.setImageResource(dataSet.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
