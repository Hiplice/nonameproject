package com.bes.tao.freedge.RecyclerViewHandlers;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bes.tao.freedge.DataHandler;
import com.bes.tao.freedge.Fragments.ProductsFragment;
import com.bes.tao.freedge.MainActivity;
import com.bes.tao.freedge.R;

public class ProductMenuListView extends RecyclerView.Adapter<ProductMenuListView.ViewHolder> {

    public String[][] mDataList;
    private Context context;

    private FragmentTransaction transaction;

    @NonNull
    @Override
    public ProductMenuListView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View recyclerViewRow = inflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(recyclerViewRow);
    }

    public void refreshDataset(String[][] data) {
        mDataList = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductMenuListView.ViewHolder viewHolder, final int position) {
        viewHolder.bind(position);

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar deleteItem  = Snackbar.make(viewHolder.itemView, "Удалить продукт?", Snackbar.LENGTH_LONG)
                        .setAction("Да", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DataHandler.removeProduct(Integer.valueOf(mDataList[position][0]), context);
                                ProductsFragment productsFragment = ProductsFragment.getProductsFragment();
                                productsFragment.onPause();
                                productsFragment.onResume();
                                //productsFragment.initRecyclerView();
                            }
                        });
                deleteItem.show();
                return false;
            }
        });
    }

    public ProductMenuListView(Context context, String[][] dataList) {
        mDataList = dataList; // [productID, productName, productWeight, amount, categoryID, addDate]
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryIcon;
        private TextView boldProductName;
        private TextView productAddDate;
        private TextView productWeight;
        private LinearLayout menuElementRectangle;

        private ViewHolder(View itemView) {
            super(itemView);
            menuElementRectangle = itemView.findViewById(R.id.menuElem);
            categoryIcon = itemView.findViewById(R.id.categoryImage);
            boldProductName = itemView.findViewById(R.id.boldProductName);
            productAddDate = itemView.findViewById(R.id.productAddDate);
            productWeight = itemView.findViewById(R.id.weightText);
        }

        public void bind (int position) {
            boldProductName.setText(mDataList[position][1]);
            productAddDate.setText(mDataList[position][5]);
            productWeight.setText(mDataList[position][2] + " " + mDataList[position][3]);
            categoryIcon.setImageResource(DataHandler.getCategoryIconsById(mDataList[position][4]));
            menuElementRectangle.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, DataHandler.getCategoryColorsById(mDataList[position][4]))));
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.length;
    }
}