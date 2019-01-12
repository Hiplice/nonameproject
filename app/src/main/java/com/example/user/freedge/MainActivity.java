package com.example.user.freedge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ProductMenuListView.ItemClickListener {

    ProductMenuListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to populate the RecyclerView with
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Молоко");
        animalNames.add("Кефир");
        animalNames.add("Мясо");
        animalNames.add("Масло");
        animalNames.add("Базилик");
        animalNames.add("Молоко");
        animalNames.add("Кефир");
        animalNames.add("Мясо");
        animalNames.add("Масло");
        animalNames.add("Базилик");
        animalNames.add("Молоко");
        animalNames.add("Кефир");
        animalNames.add("Мясо");
        animalNames.add("Масло");
        animalNames.add("Базилик");
        animalNames.add("Молоко");
        animalNames.add("Кефир");
        animalNames.add("Мясо");
        animalNames.add("Масло");
        animalNames.add("Базилик");
        animalNames.add("Молоко");
        animalNames.add("Кефир");
        animalNames.add("Мясо");
        animalNames.add("Масло");
        animalNames.add("Базилик");
        animalNames.add("Молоко");
        animalNames.add("Кефир");
        animalNames.add("Мясо");
        animalNames.add("Масло");
        animalNames.add("Базилик");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductMenuListView(this, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}