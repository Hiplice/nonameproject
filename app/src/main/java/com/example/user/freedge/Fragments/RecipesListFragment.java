package com.example.user.freedge.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.freedge.DataHandler;
import com.example.user.freedge.R;
import com.example.user.freedge.RecyclerViewHandlers.ProductMenuListView;
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

        mRecyclerView = recipeView.findViewById(R.id.recipeRecycler);
        context = recipeView.getContext();
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecipeListView(context, DataHandler.getFirstNRecipes(20, context));
        mRecyclerView.setAdapter(mAdapter);

        return recipeView;
    }
}