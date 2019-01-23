package com.example.user.freedge;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDialog extends AppCompatActivity {

    AutoCompleteTextView mAutoCompleteTextView;
    TextView countType;
    String name;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_dialog);
        countType = findViewById(R.id.count_type);

        //Автозаполнение
        final String[][] mData = MainActivity.allProducts;
        mAutoCompleteTextView = findViewById(R.id.product);
        mAutoCompleteTextView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, mData[1]));
        final String[] chosenElementInformation = new String[4];

        mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String inputData = mAutoCompleteTextView.getText().toString();

                for (int i = 0; i < mData[0].length; ++i) {
                    if (mData[1][i].equals(inputData)) {
                        for (int k = 0; k < 4; ++k) {
                            chosenElementInformation[k] = mData[k][i];
                        } break;
                    }
                }

                countType.setText(chosenElementInformation[3]);
            }
        });
    }

    public void onOk(View view){
        name = mAutoCompleteTextView.getText().toString();
        Toast toast = Toast.makeText(getApplicationContext(),
                name, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onCancel(View view) {
        finish();
    }
}
