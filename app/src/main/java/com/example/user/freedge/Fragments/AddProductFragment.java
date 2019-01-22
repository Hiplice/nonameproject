package com.example.user.freedge.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.user.freedge.DataHandler;
import com.example.user.freedge.MainActivity;
import com.example.user.freedge.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddProductFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //здесь можешь стиль указывать
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        alert.setTitle("Title");
        alert.setMessage("Message");

        // Set an EditText view to get user input
        AutoCompleteTextView autoCompleteTextView = new AutoCompleteTextView(getActivity());
        final String[][] mData = MainActivity.allProducts;
        autoCompleteTextView.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, mData[1]));
        final EditText input = new EditText(getActivity());
        alert.setView(autoCompleteTextView);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText();
                int id = -1;
                int catID = 1;
                for (int i = 0; i < mData[0].length; ++i) {
                    if (mData[1][i] == String.valueOf(value)) {
                        id = Integer.valueOf(mData[0][i]);
                        catID = Integer.valueOf(mData[2][i]);
                        break;
                    }
                } DataHandler.addProduct(id, String.valueOf(value), "200 г.", catID, getDate(), getContext());
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });


        return alert.show();
    }

    private String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", new Locale("ru"));
        return dateFormat.format(new Date());
    }
}
