package com.example.user.freedge;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.freedge.Fragments.AddProductFragment;
import com.example.user.freedge.Fragments.ProductsFragment;
import com.example.user.freedge.Fragments.RecipesFragment;
import com.example.user.freedge.Fragments.RecipesListFragment;
import com.example.user.freedge.Fragments.RecipesTextFragment;
import com.example.user.freedge.Fragments.SettingsFragment;


public class MainActivity extends AppCompatActivity {

    android.app.FragmentTransaction transaction;
    RecipesFragment recipes;
    ProductsFragment products;
    RecipesListFragment listFragment;
    SettingsFragment settings;
    TextView toolBarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализируем элементы интерфейса
        toolBarText = findViewById(R.id.toolBarText);
        BottomNavigationView bottomNavigationMenu = findViewById(R.id.navigationMenu);

        // Инициализируем фрагметы активити
        products = new ProductsFragment();
        recipes = new RecipesFragment();
        settings = new SettingsFragment();

        // Инициплизируем обработчик нажатия на меню
        bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                transaction = getFragmentManager().beginTransaction();
                if (menuItem.getItemId() == R.id.action_products) {
                    toolBarText.setText(R.string.appbar_products);
                    transaction.replace(R.id.contentContainer, products);
                } else if (menuItem.getItemId() == R.id.action_recipes) {
                    transaction.replace(R.id.contentContainer, recipes);
                    toolBarText.setText(R.string.appbar_recipes);
                } else if (menuItem.getItemId() == R.id.action_settings) {
                    transaction.replace(R.id.contentContainer, settings);
                    toolBarText.setText(R.string.appbar_settings);
                }
                transaction.commit();
                return false;
            }
        });
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contentContainer, products);
        toolBarText.setText(R.string.appbar_products);
        transaction.commit();
    }

    public void onClickRecipeMenu(View view) {
        listFragment = new RecipesListFragment();
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contentContainer, listFragment);
        transaction.commit();

        switch (view.getId()) {
            case R.id.available_recipes:
                toolBarText.setText("Доступные рецепты");
                break;
            case R.id.all_recipes:
                toolBarText.setText("Все рецепты");
                break;
            case R.id.favourites:
                toolBarText.setText("Избранное");
                break;
            case R.id.selections:
                toolBarText.setText("Подборки");
                break;
        }
    }
}