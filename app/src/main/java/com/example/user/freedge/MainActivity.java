package com.example.user.freedge;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.freedge.Fragments.ProductsFragment;
import com.example.user.freedge.Fragments.RecipesFragment;
import com.example.user.freedge.Fragments.RecipesListFragment;
import com.example.user.freedge.Fragments.RecipesTextFragment;
import com.example.user.freedge.Fragments.SettingsFragment;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    android.app.FragmentTransaction transaction;
    RecipesFragment recipes;
    ProductsFragment products;
    Fragment currentFragment;
    RecipesListFragment listFragment;
    SettingsFragment settings;
    TextView toolBarText;
    Stack<Fragment> stack;

    public static String[] availableProductID;
    public static String[][] allProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Stack фрагментов - инициализация
        stack = new Stack<Fragment>();

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
                if (menuItem.getItemId() == R.id.action_products) {
                    check(products);
                    //Меняем надпись на тулбаре
                    toolBarText.setText(R.string.appbar_products);
                } else if (menuItem.getItemId() == R.id.action_recipes) {
                    check(recipes);
                    // Меняем надпись на тулбаре
                    toolBarText.setText(R.string.appbar_recipes);
                } else if (menuItem.getItemId() == R.id.action_settings) {
                    check(settings);
                    // Меняем надпись на тулбаре
                    toolBarText.setText(R.string.appbar_settings);
                }
                return false;
            }
        });

        // Меняем надпись на тулбаре
        toolBarText.setText(R.string.appbar_products);

        // Устанавливаем активити в фрагменте
        check(products);
    }



    // TODO: Активити закрывается, если нажать Back при пустом стеке;
    @Override
    public void onBackPressed() {
        if (!stack.empty()) {
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contentContainer, stack.peek());
            transaction.commit();
            //TODO: При возврате на ListFragment не меняется текст Toolbar (пока это не тестится)
            if (stack.peek() == products) toolBarText.setText(R.string.appbar_products);
            if (stack.peek() == recipes) toolBarText.setText(R.string.appbar_recipes);
            if (stack.peek() == settings) toolBarText.setText(R.string.appbar_settings);
            currentFragment = stack.peek();
            stack.pop();
        }

    }


    // Делает проверку на повторное нажатие одной и той же кнопки
    public void check(Fragment f){
        if (stack.empty()){
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contentContainer, f);
            transaction.commit();
            stack.push(currentFragment);
            currentFragment = f;
        }
        else {
            if (f != currentFragment) {
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contentContainer, f);
                transaction.commit();
                stack.push(currentFragment);
                currentFragment = f;
            }
        }
    }

    public void onClickRecipeMenu(View view) {
        listFragment = new RecipesListFragment();
        check(listFragment);

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

    public void onButton(View view){
        Intent intent = new Intent(MainActivity.this,  ProductDialog.class);
        startActivity(intent);
    }

    private void loadResources() {
        availableProductID = DataHandler.loadAvailableProducts(getBaseContext());
        allProducts = DataHandler.loadAllProducts(getBaseContext());
        /*
        class LoadRecipes extends AsyncTask<Void, Void, String[][]> {
            @Override
            protected String[][] doInBackground(Void... voids) {
                return DataHandler.loadAllProducts(getBaseContext());
            }
        }
        class LoadProducts extends AsyncTask<Void, Void, String[]> {
            @Override
            protected String[] doInBackground(Void... voids) {
                return DataHandler.loadAvailableProducts(getBaseContext());
            }
        }
        LoadRecipes lr = new LoadRecipes();
        LoadProducts lp = new LoadProducts();
        lr.execute();
        lp.execute();
        try {
            //availableProductID = lp.get();
            allProducts = lr.get();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Что-то пошло не по плану :c", Toast.LENGTH_LONG);
        }*/
    }
}