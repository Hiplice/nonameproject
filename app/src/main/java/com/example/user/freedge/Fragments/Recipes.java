package com.example.user.freedge.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.freedge.R;

public class Recipes extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe, null);
    }

    public void onButton(View view){
        final Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "Заработало", Toast.LENGTH_SHORT);
        toast.show();
    }
}