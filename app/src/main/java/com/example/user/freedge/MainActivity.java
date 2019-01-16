package com.example.user.freedge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Map;

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

    public void onButton(View view){
        final Toast toast = Toast.makeText(getApplicationContext(),
                "Заработало", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onSettings(View view) {
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
}