package com.example.lostandfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    EditText etName, etDesc, etType;
    Button btnSubmit;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        dbHelper = new DatabaseHelper(this);

        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        etType = findViewById(R.id.etType);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String desc = etDesc.getText().toString().trim();
            String type = etType.getText().toString().trim();

            if (!name.isEmpty() && !desc.isEmpty() && !type.isEmpty()) {
                boolean inserted = dbHelper.insertItem(name, desc, type);
                if (inserted) {
                    Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Failed to add!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
