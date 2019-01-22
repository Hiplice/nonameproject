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
import com.example.user.freedge.R;

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
        final String[][] mData = DataHandler.getAllProducts(getContext());
        autoCompleteTextView.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, mData[1]));
        final EditText input = new EditText(getActivity());
        alert.setView(autoCompleteTextView);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText();
                // Do something with value!
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });


        return alert.show();
    }
}
