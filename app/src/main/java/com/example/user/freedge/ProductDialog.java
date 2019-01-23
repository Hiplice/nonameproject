package com.example.user.freedge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

public class ProductDialog extends AppCompatActivity {

    AutoCompleteTextView mAutoCompleteTextView;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_dialog);

        //Автозаполнение
        final String[][] mData = MainActivity.allProducts;
        mAutoCompleteTextView = findViewById(R.id.product);
        mAutoCompleteTextView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, mData[1]));
        name = mAutoCompleteTextView.getText().toString();
    }

    public void onOk(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                name, Toast.LENGTH_SHORT);
        toast.show();
        //DataHandler.addProduct(name);
    }

    public void onCancel(View view) {
        finish();
    }
}
