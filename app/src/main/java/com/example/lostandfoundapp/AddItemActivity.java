package com.example.lostandfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    EditText etName, etDesc, etPhone, etDate, etLocation;
    RadioGroup radioGroupType;
    Button btnSubmit;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        dbHelper = new DatabaseHelper(this);

        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        etPhone = findViewById(R.id.etPhone);
        etDate = findViewById(R.id.etDate);
        etLocation = findViewById(R.id.etLocation);
        radioGroupType = findViewById(R.id.radioGroupType);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String desc = etDesc.getText().toString().trim();
            String date = etDate.getText().toString().trim();
            String location = etLocation.getText().toString().trim();

            int selectedId = radioGroupType.getCheckedRadioButtonId();

            if (selectedId == -1 || name.isEmpty() || phone.isEmpty() || desc.isEmpty() || date.isEmpty() || location.isEmpty()) {
                Toast.makeText(this, "Fill all fields and select post type!", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadio = findViewById(selectedId);
            String type = selectedRadio.getText().toString();

            // Concatenate extra info if needed, or save separately
            boolean inserted = dbHelper.insertItem(name, desc, type); // You can expand DB fields if needed

            if (inserted) {
                Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Failed to add!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
