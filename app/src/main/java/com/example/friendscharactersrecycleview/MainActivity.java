package com.example.friendscharactersrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<DataModel> dataSet;
    private CustomAdapter adapter;
    private SearchView searchCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    adapter=new CustomAdapter(dataSet);
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
}