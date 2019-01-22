package com.example.user.freedge.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.freedge.R;

public class AddProductFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View addProductView = inflater.inflate(R.layout.add_product_fragment, container, false);


        return addProductView;
    }
}
