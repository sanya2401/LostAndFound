package com.example.lostandfoundapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private DatabaseHelper dbHelper;
    private ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);  // Make sure this layout exists

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerViewItems);

        itemList = dbHelper.getAllItems();
        adapter = new ItemAdapter(this, itemList, dbHelper);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
