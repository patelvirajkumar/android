package com.example.chitrangiassignment3;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chitrangiassignment3.ProductFragment.OnListItemListener;

import java.util.List;
import java.util.Locale;


public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ViewHolder> {

    private final List<ProductItem> itemList;
    private OnListItemListener itemListener;
    private int selectedIndex = -1;

    ProductViewAdapter(List<ProductItem> list, OnListItemListener listener) {
        this.itemList = list;
        this.itemListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.productItem = itemList.get(position);
        holder.tvTitle.setText(holder.productItem.title);
        holder.ivBook.setImageResource(holder.productItem.image);
        holder.tvAmount.setText(String.format(Locale.CANADA, "$%d", holder.productItem.amount));

        if (selectedIndex == position){
            holder.itemView.setBackgroundColor(Color.parseColor("#e6e6e6"));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIndex = position;
                itemListener.onItem(holder.productItem);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        ImageView ivBook;
        TextView tvTitle, tvAmount;
        ProductItem productItem;

        ViewHolder(View view) {
            super(view);
            itemView = view;
            ivBook = (ImageView) view.findViewById(R.id.ivBook);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvAmount = (TextView) view.findViewById(R.id.tvAmount);
        }
    }
}
