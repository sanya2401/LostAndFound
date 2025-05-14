package com.example.lostandfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private DatabaseHelper dbHelper;
    private ArrayList<Item> itemList;
    private Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerViewItems);
        btnAddItem = findViewById(R.id.btnAddItem);

        itemList = dbHelper.getAllItems();
        adapter = new ItemAdapter(this, itemList, dbHelper);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAddItem.setOnClickListener(v -> {
            startActivity(new Intent(this, AddItemActivity.class));
        });
    }
}
