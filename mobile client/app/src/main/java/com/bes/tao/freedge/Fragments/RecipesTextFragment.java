package com.bes.tao.freedge.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bes.tao.freedge.DataHandler;
import com.bes.tao.freedge.R;

public class RecipesTextFragment extends Fragment {

    TextView recipeText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View recipeView = inflater.inflate(R.layout.recipes_text_fragment, container, false);

        recipeText = recipeView.findViewById(R.id.recipeText);
        setRecipeText();

        return recipeView;
    }

    private void setRecipeText() {
        String[] textDataArr = DataHandler.recipesText;
        String textData = "";

        for (int i = 0; i < textDataArr.length; ++i) {
            textData += textDataArr[i];
            if (i < textDataArr.length - 1) {
                textData += "\n\n";
            }
        }
        recipeText.setText(textData);
    }
}