package com.example.friendscharactersrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectListener{

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<DataModel> dataSet;
    private CustomAdapter adapter;
    private SearchView searchCharacter;
    private Dialog dialogCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogCharacter=new Dialog(this);
        dataSet= new ArrayList<>();

        searchCharacter=findViewById(R.id.searchCharacter);
        searchCharacter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        recyclerView=findViewById(R.id.res);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());


        for (int i = 0; i < MyData.drawableArray.length; i++) {
            dataSet.add(new DataModel(
                MyData.nameArray[i],
                MyData.descriptionArray[i],
                MyData.id_[i],
                MyData.drawableArray[i]
        ));
        }

        adapter=new CustomAdapter(dataSet,this);
        recyclerView.setAdapter(adapter);
    }


    private void filterList(String text) {
        ArrayList<DataModel> filteredList=new ArrayList<>();
        for(DataModel character: dataSet){
            if(character.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(character);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this,"No character found...",Toast.LENGTH_SHORT).show();
        }else {
            adapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onItemClick(DataModel dataModel) {
        openCardDialog(dataModel);
    }

    private void openCardDialog(DataModel dataModel) {
        dialogCharacter.setContentView(R.layout.character_dialog_layout);
        dialogCharacter.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView textViewName=dialogCharacter.findViewById(R.id.characterName);
        TextView textViewDescription=dialogCharacter.findViewById(R.id.characterDecription);
        ImageView imageViewCharacter=dialogCharacter.findViewById(R.id.charPicture);
        Button closeButton=dialogCharacter.findViewById(R.id.buttonClose);

        textViewName.setText(dataModel.getName());
        textViewDescription.setText(dataModel.getDescription());
        imageViewCharacter.setImageResource(dataModel.getImage());
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCharacter.dismiss();
            }
        });


        dialogCharacter.show();
    }
}