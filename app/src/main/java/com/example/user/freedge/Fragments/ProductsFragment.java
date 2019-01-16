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

import java.util.ArrayList;

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

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ProductMenuListView(context, DataHandler.getAvailableProducts());
        mRecyclerView.setAdapter(mAdapter);

        return productsView;
    }

    public void onButton(View view){
        final Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "Заработало", Toast.LENGTH_SHORT);
        toast.show();
    }
}

