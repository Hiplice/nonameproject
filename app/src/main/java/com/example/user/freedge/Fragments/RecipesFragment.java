package com.example.user.freedge.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.freedge.R;

public class RecipesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View recipeView = inflater.inflate(R.layout.recipes_fragment, container, false);

        return recipeView;
    }
}