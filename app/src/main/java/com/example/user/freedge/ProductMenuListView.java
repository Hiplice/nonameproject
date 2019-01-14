package com.example.user.freedge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductMenuListView extends RecyclerView.Adapter<ProductMenuListView.ViewHolder> {

    private ArrayList<ArrayList<String>> mData;
    private ArrayList<Integer> mColors;
    private ArrayList<Integer> mIcons;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    ProductMenuListView(Context context, ArrayList<ArrayList<String>> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        mColors = DataHandler.getCategoryColorsById(mData.get(4));
        mIcons = DataHandler.getCategoryIconsById(mData.get(4));
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.boldProductName.setText(mData.get(1).get(position));
        holder.weightText.setText(mData.get(2).get(position));
        holder.productAddDate.setText( mData.get(3).get(position));
        holder.menuElement.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, mColors.get(position))));
        holder.categoryImage.setImageResource(mIcons.get(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.get(0).size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout menuElement;
        TextView boldProductName;
        TextView productAddDate;
        TextView weightText;
        ImageView categoryImage;

        ViewHolder(View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            boldProductName = itemView.findViewById(R.id.boldProductName);
            productAddDate = itemView.findViewById(R.id.productAddDate);
            weightText = itemView.findViewById(R.id.weightText);
            menuElement = itemView.findViewById(R.id.menuElem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(1).get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}