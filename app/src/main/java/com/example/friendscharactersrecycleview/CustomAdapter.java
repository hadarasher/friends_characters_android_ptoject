package com.example.friendscharactersrecycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter {
    private ArrayList<DataModel> dataSet;
    private SelectListener clickListener;

    public CustomAdapter(ArrayList<DataModel> dataSet,SelectListener listener) {
        this.dataSet = dataSet;
        this.clickListener=listener;
    }

    public void setFilteredList(ArrayList<DataModel> filteredList){
        this.dataSet=filteredList;
        notifyDataSetChanged();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDescription;
        ImageView imageView;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.characterName);
            textViewDescription = itemView.findViewById(R.id.characterDecription);
            imageView = itemView.findViewById(R.id.charPicture);
            cardView=itemView.findViewById(R.id.cardCharacter);
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

        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(dataSet.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
