package com.example.user.freedge.RecyclerViewHandlers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.freedge.DataHandler;
import com.example.user.freedge.MainActivity;
import com.example.user.freedge.R;

public class RecipeListView extends RecyclerView.Adapter<RecipeListView.ViewHolder> {

    private String[][] mDataList;
    private Context context;

    @NonNull
    @Override
    public RecipeListView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View recyclerViewRow = inflater.inflate(R.layout.recyclerview_recipe_row, parent, false);
        return new ViewHolder(recyclerViewRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeListView.ViewHolder viewHolder, final int position) {
        viewHolder.bind(position);
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
