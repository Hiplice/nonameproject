package com.example.user.freedge;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    public static Fragment currentFragment;
    RecipesListFragment listFragment;
    SettingsFragment settings;
    TextView toolBarText;
    public static Stack<Fragment> stack;
    Button back;
    public static String window;

    public static String[][] availableProducts;
    public static String[][] allProducts;

    private static AppCompatActivity main_activity_object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main_activity_object = this;
        setContentView(R.layout.activity_main);

        loadResources();

        //Stack фрагментов - инициализация
        stack = new Stack<Fragment>();

        // Инициализируем элементы интерфейса
        toolBarText = findViewById(R.id.toolBarText);
        BottomNavigationView bottomNavigationMenu = findViewById(R.id.navigationMenu);
        back = findViewById(R.id.back);

        // Инициализируем фрагметы активити
        products = new ProductsFragment();
        recipes = new RecipesFragment();
        settings = new SettingsFragment();
        window = "";

        // Инициплизируем обработчик нажатия на меню
        bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_products) {
                    check(products);
                    //Меняем надпись на тулбаре
                    toolBarText.setText(R.string.appbar_products);
                    back.setVisibility(View.INVISIBLE);
                } else if (menuItem.getItemId() == R.id.action_recipes) {
                    check(recipes);
                    // Меняем надпись на тулбаре
                    toolBarText.setText(R.string.appbar_recipes);
                    back.setVisibility(View.INVISIBLE);
                } else if (menuItem.getItemId() == R.id.action_settings) {
                    check(settings);
                    // Меняем надпись на тулбаре
                    toolBarText.setText(R.string.appbar_settings);
                    back.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });

        // Меняем надпись на тулбаре
        toolBarText.setText(R.string.appbar_products);

        // Устанавливаем активити в фрагменте
        check(products);
        back.setVisibility(View.INVISIBLE);
    }



    // TODO: Активити закрывается, если нажать Back при пустом стеке;
    @Override
    public void onBackPressed() {
        if (!stack.empty()) {
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contentContainer, stack.peek());
            transaction.commit();
            if (stack.peek() == products) {
                toolBarText.setText(R.string.appbar_products);
                back.setVisibility(View.INVISIBLE);
            }
            if (stack.peek() == recipes) {
                toolBarText.setText(R.string.appbar_recipes);
                back.setVisibility(View.INVISIBLE);
            }
            if (stack.peek() == settings) {
                toolBarText.setText(R.string.appbar_settings);
                back.setVisibility(View.INVISIBLE);
            }
            if (stack.peek() == listFragment) {
                if (window == "All recipes") {
                    toolBarText.setText(R.string.list);
                }
                if (window == "Available recipes")
                    toolBarText.setText(R.string.available);
                back.setVisibility(View.VISIBLE);
            }
            currentFragment = stack.peek();
            stack.pop();
        }

    }

    public void onBack(View view) {
        if (!stack.empty()) {
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contentContainer, stack.peek());
            transaction.commit();
            if (stack.peek() == products) {
                toolBarText.setText(R.string.appbar_products);
                back.setVisibility(View.INVISIBLE);
            }
            if (stack.peek() == recipes) {
                toolBarText.setText(R.string.appbar_recipes);
                back.setVisibility(View.INVISIBLE);
            }
            if (stack.peek() == settings) {
                toolBarText.setText(R.string.appbar_settings);
                back.setVisibility(View.INVISIBLE);
            }
            //TODO здесь должен быть if
            if (stack.peek() == listFragment) {
                if (window == "All recipes") {
                    toolBarText.setText(R.string.list);
                }
                if (window == "Available recipes")
                    toolBarText.setText(R.string.available);
                back.setVisibility(View.VISIBLE);
            }
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
        back.setVisibility(View.VISIBLE);

        switch (view.getId()) {
            case R.id.available_recipes:
                toolBarText.setText("Доступные рецепты");
                window = "Available recipes";
                break;
            case R.id.all_recipes:
                toolBarText.setText("Все рецепты");
                window = "All recipes";
                break;
            case R.id.favourites:
                toolBarText.setText("Избранное");
                window = "Favourites";
                break;
            case R.id.selections:
                toolBarText.setText("Подборки");
                window = "Recommendation";
                break;
        }
    }

    public static AppCompatActivity getMainActivityObject() {
        return main_activity_object;
    }


    public void onButton(View view){
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).show();
        /*Intent intent = new Intent(MainActivity.this,  ProductDialog.class);
        startActivity(intent);*/
    }


    private void loadResources() {
        availableProducts = DataHandler.loadAvailableProducts(getBaseContext());
        allProducts = DataHandler.loadAllProducts(getBaseContext());
    }
}