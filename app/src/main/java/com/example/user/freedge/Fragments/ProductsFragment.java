package com.example.user.freedge.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.freedge.DataHandler;
import com.example.user.freedge.ProductMenuListView;
import com.example.user.freedge.R;

public class ProductsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View productsView = inflater.inflate(R.layout.products_fragment, container, false);

        mRecyclerView = productsView.findViewById(R.id.productRecyclerView);
        context = productsView.getContext();
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ProductMenuListView(context, DataHandler.getAvailableProducts(context));
        mRecyclerView.setAdapter(mAdapter);

        return productsView;
    }
}

