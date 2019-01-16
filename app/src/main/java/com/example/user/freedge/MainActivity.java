package com.example.user.freedge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.user.freedge.Fragments.ProductsFragment;
import com.example.user.freedge.Fragments.RecipesFragment;
import com.example.user.freedge.Fragments.SettingsFragment;


public class MainActivity extends AppCompatActivity {

    android.app.FragmentTransaction transaction;
    RecipesFragment recipes;
    ProductsFragment products;
    SettingsFragment settings;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);

        products = new ProductsFragment();
        recipes = new RecipesFragment();
        settings = new SettingsFragment();

        // Меняем надпись на тулбаре
        toolbar.setTitle(R.string.appbar_products);

        // Устанавливаем активити в фрагменте
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, products);
        transaction.commit();
    }

    public void onProducts(View view) {
        // Меняем надпись на тулбаре
        toolbar.setTitle(R.string.appbar_recipes);

        // Меняем активити в фрагменте
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, products);
        transaction.commit();
    }

    public void onRecipes(View view) {
        // Меняем надпись на тулбаре
        toolbar.setTitle(R.string.appbar_recipes);

        // Меняем активити в фрагменте
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, recipes);
        transaction.commit();
    }

    public void onSettings(View view) {
        // Меняем надпись на тулбаре
        toolbar.setTitle(R.string.appbar_recipes);

        // Меняем активити в фрагменте
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, settings);
        transaction.commit();
    }

    public void onButton(View view){
        final Toast toast = Toast.makeText(getApplicationContext(),
                "Заработало", Toast.LENGTH_SHORT);
        toast.show();
    }


}