package com.example.user.freedge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class ProductMenuListView extends RecyclerView.Adapter<ProductMenuListView.ViewHolder> {

    private List<List<String>> mDataList;
    List<Integer> colorResources;
    List<Integer> iconResources;
    Context context;

    LinearLayout menuElementRectangle;
    ImageView categoryIcon;
    TextView boldProductName;
    TextView productAddDate;
    TextView productWeight;

    @NonNull
    @Override
    public ProductMenuListView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View recyclerViewRow = inflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(recyclerViewRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductMenuListView.ViewHolder viewHolder, int position) {
        boldProductName.setText(mDataList.get(1).get(position));
        productAddDate.setText(mDataList.get(3).get(position));
        productWeight.setText(mDataList.get(2).get(position));
        categoryIcon.setImageResource(iconResources.get(position));
        menuElementRectangle.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, colorResources.get(position))));
    }

    public ProductMenuListView(Context context, List<List<String>> dataList) {
        mDataList = dataList;
        this.context = context;
        List<String> categoryID = mDataList.get(4);
        colorResources = DataHandler.getCategoryColorsById(categoryID);
        iconResources = DataHandler.getCategoryIconsById(categoryID);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

            menuElementRectangle = itemView.findViewById(R.id.menuElem);
            categoryIcon = itemView.findViewById(R.id.categoryImage);
            boldProductName = itemView.findViewById(R.id.boldProductName);
            productAddDate = itemView.findViewById(R.id.productAddDate);
            productWeight = itemView.findViewById(R.id.weightText);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.get(1).size();
    }
}