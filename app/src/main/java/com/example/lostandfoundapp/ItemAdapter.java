package com.example.lostandfoundapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private ArrayList<Item> items;
    private DatabaseHelper dbHelper;

    public ItemAdapter(Context context, ArrayList<Item> items, DatabaseHelper dbHelper) {
        this.context = context;
        this.items = items;
        this.dbHelper = dbHelper;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.name.setText(item.name);
        holder.desc.setText(item.description);
        holder.type.setText(item.type);

        holder.deleteBtn.setOnClickListener(v -> {
            dbHelper.deleteItem(item.id);
            items.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, items.size());
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name, desc, type;
        Button deleteBtn;

        public ItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtItemName);
            desc = itemView.findViewById(R.id.txtItemDesc);
            type = itemView.findViewById(R.id.txtItemType);
            deleteBtn = itemView.findViewById(R.id.btnDelete);
        }
    }
}
