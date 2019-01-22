package com.example.user.freedge.RecyclerViewHandlers;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.freedge.DataHandler;
import com.example.user.freedge.Fragments.RecipesTextFragment;
import com.example.user.freedge.MainActivity;
import com.example.user.freedge.R;

public class RecipeListView extends RecyclerView.Adapter<RecipeListView.ViewHolder> {

    private String[][] mDataList;
    private Context context;
    AppCompatActivity mainActivity;
    RecipesTextFragment recipesTextFragment;
    FragmentTransaction transaction;
    TextView recipeText;

    @NonNull
    @Override
    public RecipeListView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View recyclerViewRow = inflater.inflate(R.layout.recyclerview_recipe_row, parent, false);
        mainActivity = MainActivity.getMainActivityObject();
        recipesTextFragment = new RecipesTextFragment();
        return new ViewHolder(recyclerViewRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeListView.ViewHolder viewHolder, final int position) {
        viewHolder.bind(position);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView toolBarText = mainActivity.findViewById(R.id.toolBarText);
                toolBarText.setText(mDataList[1][position]);

                //DataHandler.recipesText = DataHandler.getRecipeById(123, context);

                FragmentManager manager = ((AppCompatActivity)context).getFragmentManager();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.contentContainer, recipesTextFragment);
                transaction.commit();
                MainActivity.stack.push(recipesTextFragment);
            }
        });
    }

    public RecipeListView(Context context, String[][] dataList) {
        mDataList = dataList; //
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView boldRecipeName;


        private ViewHolder(View itemView) {
            super(itemView);
            boldRecipeName = itemView.findViewById(R.id.boldRecipeName);
        }

        public void bind(int position) {
            boldRecipeName.setText(mDataList[1][position]);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList[0].length;
    }

}
