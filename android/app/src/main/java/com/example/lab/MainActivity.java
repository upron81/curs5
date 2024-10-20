package com.example.lab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "products.dat";
    private static final long SAVE_INTERVAL = 1000;
    private ProductRepository productRepository;
    private ListView listView;
    private EditText filterName, filterPrice, filterShelfLife;
    private Handler handler;
    private Runnable saveRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadProductsFromFile();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        startPeriodicSave();

        listView = findViewById(R.id.product_list);
        filterName = findViewById(R.id.filter_name);
        filterPrice = findViewById(R.id.filter_price);
        filterShelfLife = findViewById(R.id.filter_shelf_life);
        showProducts(productRepository.products);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = (Product) listView.getAdapter().getItem(position);
                Intent intent = new Intent(MainActivity.this, EditProductActivity.class);
                intent.putExtra("selectedProduct", selectedProduct);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void startPeriodicSave() {
        saveRunnable = new Runnable() {
            @Override
            public void run() {
                new SaveDataTask(MainActivity.this).execute(productRepository);
                handler.postDelayed(this, SAVE_INTERVAL);
            }
        };
        handler.post(saveRunnable);
    }

    private void stopPeriodicSave() {
        handler.removeCallbacks(saveRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveProductsToFile();
        stopPeriodicSave();
    }

    private void loadProductsFromFile() {
        try (FileInputStream fis = openFileInput(FILENAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            productRepository = (ProductRepository) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            productRepository = new ProductRepository();
        }
    }

    private void saveProductsToFile() {
        try (FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(productRepository);
        } catch (IOException e) {
        }
    }

    private void showProducts(List<Product> products) {
        ArrayAdapter<Product> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        listView.setAdapter(adapter);
    }

    public void filterByName(View view) {
        String name = filterName.getText().toString();
        showProducts(productRepository.getProductsByName(name));
    }

    public void filterByNameAndPrice(View view) {
        String name = filterName.getText().toString();
        double price = Double.parseDouble(filterPrice.getText().toString());
        showProducts(productRepository.getProductsByNameAndPrice(name, price));
    }

    public void filterByShelfLife(View view) {
        int shelfLife = Integer.parseInt(filterShelfLife.getText().toString());
        showProducts(productRepository.getProductsByShelfLife(shelfLife));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Product updatedProduct = (Product) data.getSerializableExtra("updatedProduct");
            if (updatedProduct != null) {
                productRepository.updateProduct(updatedProduct);
                showProducts(productRepository.products);
            }
        }
    }
}
