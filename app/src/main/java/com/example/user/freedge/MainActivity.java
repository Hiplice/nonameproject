package com.example.user.freedge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ProductMenuListView.ItemClickListener {

    FrameLayout container;
    ProductMenuListView adapter;
    FragmentManager myFragmentManager;
    MyFragment1 myFragment1;
    MyFragment2 myFragment2;
    final static String TAG_1 = "FRAGMENT_1";
    final static String TAG_2 = "FRAGMENT_2";
    final static String KEY_MSG_1 = "FRAGMENT1_MSG";
    final static String KEY_MSG_2 = "FRAGMENT2_MSG";

    // класс для первого фрагмента
    public static class MyFragment1 extends Fragment {

        TextView textMsg;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.first_screen, null);
            textMsg = (TextView) view.findViewById(R.id.f);

            Bundle bundle = getArguments();
            if (bundle != null) {
                String msg = bundle.getString(KEY_MSG_1);
                if (msg != null) {
                    textMsg.setText(msg);
                }
            }
            return view;
        }

        public void setMsg(String msg) {
            textMsg.setText(msg);
        }
    }

    // класс для второго фрагмента
    public static class MyFragment2 extends Fragment {

        TextView textMsg;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.recipe, null);
            textMsg = (TextView) view.findViewById(R.id.q);

            Bundle bundle = getArguments();
            if (bundle != null) {
                String msg = bundle.getString(KEY_MSG_2);
                if (msg != null) {
                    textMsg.setText(msg);
                }
            }
            return view;
        }

        public void setMsg(String msg) {
            textMsg.setText(msg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (FrameLayout) findViewById(R.id.container);


        ImageButton button1 = findViewById(R.id.productMenuButton);
        ImageButton button2 = findViewById(R.id.recipeMenuButton);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                MyFragment1 fragment = (MyFragment1) myFragmentManager
                        .findFragmentByTag(TAG_1);

                if (fragment == null) {

                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_MSG_1, "Заменили на первый фрагмент");
                    myFragment1.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = myFragmentManager
                            .beginTransaction();
                    fragmentTransaction.replace(R.id.container, myFragment1,
                            TAG_1);
                    fragmentTransaction.commit();

                } else {
                    fragment.setMsg("Первый фрагмент уже загружен");
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                MyFragment2 fragment = (MyFragment2) myFragmentManager
                        .findFragmentByTag(TAG_2);

                if (fragment == null) {

                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_MSG_2, "Заменили на второй фрагмент");
                    myFragment2.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = myFragmentManager
                            .beginTransaction();
                    fragmentTransaction.replace(R.id.container, myFragment2,
                            TAG_2);
                    fragmentTransaction.commit();

                } else {
                    fragment.setMsg("Второй фрагмент уже загружен");
                }
            }
        });

        //Третья кнопка панели управления
        ImageButton button3 = findViewById(R.id.settingMenuButton);
        final Toast toast3 = Toast.makeText(getApplicationContext(),
                "РАБОТАЕТ 3!", Toast.LENGTH_SHORT);
        final Intent intent3 = new Intent(MainActivity.this, SettingsActivity.class);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast3.show();
                startActivity(intent3);
            }
        });
    }



    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }


}