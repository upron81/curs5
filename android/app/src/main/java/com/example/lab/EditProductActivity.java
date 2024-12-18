package com.example.lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditProductActivity extends AppCompatActivity {

    private Product selectedProduct;
    private EditText nameField, upcField, manufacturerField, priceField, shelfLifeField, quantityField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        selectedProduct = (Product) getIntent().getSerializableExtra("selectedProduct");
        nameField = findViewById(R.id.edit_name);
        upcField = findViewById(R.id.edit_upc);
        manufacturerField = findViewById(R.id.edit_manufacturer);
        priceField = findViewById(R.id.edit_price);
        shelfLifeField = findViewById(R.id.edit_shelf_life);
        quantityField = findViewById(R.id.edit_quantity);

        if (selectedProduct != null) {
            nameField.setText(selectedProduct.name);
            upcField.setText(selectedProduct.upc);
            manufacturerField.setText(selectedProduct.manufacturer);
            priceField.setText(String.valueOf(selectedProduct.price));
            shelfLifeField.setText(String.valueOf(selectedProduct.shelfLife));
            quantityField.setText(String.valueOf(selectedProduct.quantity));
        }
    }

    public void saveProduct(View view) {
        if (selectedProduct != null) {
            selectedProduct.name = nameField.getText().toString();
            selectedProduct.upc = upcField.getText().toString();
            selectedProduct.manufacturer = manufacturerField.getText().toString();
            selectedProduct.price = Double.parseDouble(priceField.getText().toString());
            selectedProduct.shelfLife = Integer.parseInt(shelfLifeField.getText().toString());
            selectedProduct.quantity = Integer.parseInt(quantityField.getText().toString());
            Intent resultIntent = new Intent();
            resultIntent.putExtra("updatedProduct", selectedProduct);
            setResult(RESULT_OK, resultIntent);
        }
        finish();
    }
}
