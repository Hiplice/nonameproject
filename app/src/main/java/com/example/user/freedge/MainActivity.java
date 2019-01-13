package com.example.user.freedge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
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
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductMenuListView(this, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        //Первая кнопка панели управления
        ImageButton button1 = findViewById(R.id.productMenuButton);
        final Toast toast1 = Toast.makeText(getApplicationContext(),
                "РАБОТАЕТ 1!", Toast.LENGTH_SHORT);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast1.show();
            }
        });

        //Вторая кнопка панели управления
        ImageButton button2 = findViewById(R.id.recipeMenuButton);
        final Toast toast2 = Toast.makeText(getApplicationContext(),
                "РАБОТАЕТ 2!", Toast.LENGTH_SHORT);
        final Intent intent2 = new Intent(MainActivity.this, RecipeActivity.class);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast2.show();
                startActivity(intent2);
            }
        });

        //Третья кнопка панели управления
        ImageButton button3 = findViewById(R.id.settingMenuButton);
        final Toast toast3 = Toast.makeText(getApplicationContext(),
                "РАБОТАЕТ 3!", Toast.LENGTH_SHORT);
        final Intent intent3 = new Intent(MainActivity.this, SettingsActivity.class);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast3.show();
                startActivity(intent3);
            }
        });
    }



    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }


}