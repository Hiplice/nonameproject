package com.example.user.freedge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.user.freedge.Fragments.Products;
import com.example.user.freedge.Fragments.Recipes;


public class MainActivity extends AppCompatActivity {

    android.app.FragmentTransaction transaction;
    Recipes recipes;
    Products products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        products = new Products();
        recipes = new Recipes();

        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, products);
        transaction.commit();


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

    public void onProducts(View view) {
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, products);
        transaction.commit();
    }

    public void onRecipes(View view) {
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, recipes);
        transaction.commit();
    }
}