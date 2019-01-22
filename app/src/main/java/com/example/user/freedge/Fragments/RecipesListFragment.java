package com.example.user.freedge.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.user.freedge.DataHandler;
import com.example.user.freedge.R;
import com.example.user.freedge.RecyclerViewHandlers.RecipeListView;

public class
RecipesListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View recipeView = inflater.inflate(R.layout.recipes_list_fragment, container, false);

        final EditText recipeSearch = recipeView.findViewById(R.id.recipeSearch);

        mRecyclerView = recipeView.findViewById(R.id.recipeRecycler);
        context = recipeView.getContext();
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecipeListView(context, DataHandler.getFirstNRecipes(30, context));
        mRecyclerView.setAdapter(mAdapter);

        recipeSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (recipeSearch.getText().length() > 2) {
                    mAdapter = new RecipeListView(context, DataHandler.searchFirstNRecipes(30,String.valueOf(recipeSearch.getText()), context));
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    mAdapter = new RecipeListView(context, DataHandler.getFirstNRecipes(30, context));
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
        });

        return recipeView;
    }
}