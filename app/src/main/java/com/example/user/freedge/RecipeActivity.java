package com.example.user.freedge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        // Плавающая кнопка снизу
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        buttons();
    }


    // Функиционал кнопок управления
    private void buttons(){

        //Первая кнопка панели управления
        ImageButton button1 = findViewById(R.id.productMenuButton);
        final Toast toast1 = Toast.makeText(getApplicationContext(),
                "РАБОТАЕТ 1!", Toast.LENGTH_SHORT);
        final Intent intent1 = new Intent(RecipeActivity.this, MainActivity.class);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast1.show();
                startActivity(intent1);
            }
        });

        //Вторая кнопка панели управления
        ImageButton button2 = findViewById(R.id.recipeMenuButton);
        final Toast toast2 = Toast.makeText(getApplicationContext(),
                "РАБОТАЕТ 2!", Toast.LENGTH_SHORT);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast2.show();
            }
        });

        //Третья кнопка панели управления
        ImageButton button3 = findViewById(R.id.settingMenuButton);
        final Toast toast3 = Toast.makeText(getApplicationContext(),
                "РАБОТАЕТ 3!", Toast.LENGTH_SHORT);
        final Intent intent3 = new Intent(RecipeActivity.this, SettingsActivity.class);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast3.show();
                startActivity(intent3);
            }
        });
    }
}
