package com.bes.tao.freedge;

import android.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bes.tao.freedge.Fragments.ProductsFragment;
import com.bes.tao.freedge.RecyclerViewHandlers.ProductMenuListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProductDialog extends AppCompatActivity {

    AutoCompleteTextView mAutoCompleteTextView;
    TextView countType;
    EditText productWeight;
    final String[] chosenElementInformation = new String[4];
    private boolean itemChosen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_dialog);
        countType = findViewById(R.id.count_type);
        productWeight = findViewById(R.id.count);

        //Автозаполнение
        final String[][] mData = MainActivity.allProducts;
        mAutoCompleteTextView = findViewById(R.id.product);
        mAutoCompleteTextView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, mData[1]));

        mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String inputData = mAutoCompleteTextView.getText().toString();
                itemChosen = true;

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
        if (itemChosen && productWeight.getText().toString() != "") {
            DataHandler.addProduct(Integer.valueOf(chosenElementInformation[0]),
                    chosenElementInformation[1], Integer.valueOf(productWeight.getText().toString()),
                    chosenElementInformation[3], Integer.valueOf(chosenElementInformation[2]),
                    getDate(), getBaseContext());
            finish();
        }
    }

    public void onCancel(View view) {
        finish();
    }

    public String getDate() {
        return new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
    }
}
