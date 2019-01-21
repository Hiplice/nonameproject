package com.example.user.freedge;

import android.app.Fragment;

import java.util.ArrayList;

public class FragmentStack {
    ArrayList<Fragment> fragmentArrayList;
    android.app.FragmentTransaction transaction;

    public Fragment pop(){
        Fragment fragment = fragmentArrayList.get(fragmentArrayList.size()-1);
        fragmentArrayList.remove(fragmentArrayList.size()-1);
        return fragment;
    }

    public void push_back(Fragment fragment){
        fragmentArrayList.add(fragment);
    }

    public Fragment get() {
        return fragmentArrayList.get(fragmentArrayList.size()-1);
    }

    public Boolean isEmpty() {
        return fragmentArrayList.size() == 0;
    }

}
