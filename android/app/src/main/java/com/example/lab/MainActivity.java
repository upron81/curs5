package com.example.lab;

import android.os.Bundle;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductRepository productRepository;
    private ListView listView;
    private EditText filterName, filterPrice, filterShelfLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productRepository = new ProductRepository();
        listView = findViewById(R.id.product_list);
        filterName = findViewById(R.id.filter_name);
        filterPrice = findViewById(R.id.filter_price);
        filterShelfLife = findViewById(R.id.filter_shelf_life);

        // Отображаем все продукты при запуске
        showProducts(productRepository.products);

        // Обрабатываем нажатие на элемент списка
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = (Product) listView.getAdapter().getItem(position);
                Log.e("MainActivity", "Product hash: " + selectedProduct.hashCode());
                Intent intent = new Intent(MainActivity.this, EditProductActivity.class);

                // Передаем сам объект продукта
                intent.putExtra("selectedProduct", selectedProduct);
                startActivityForResult(intent, 1); // Запускаем активность с ожиданием результата
            }
        });
    }

    // Метод отображения продуктов в ListView
    private void showProducts(List<Product> products) {
        ArrayAdapter<Product> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        listView.setAdapter(adapter);
    }

    // Обработка фильтрации по наименованию
    public void filterByName(View view) {
        String name = filterName.getText().toString();
        showProducts(productRepository.getProductsByName(name));
    }

    // Обработка фильтрации по наименованию и цене
    public void filterByNameAndPrice(View view) {
        String name = filterName.getText().toString();
        double price = Double.parseDouble(filterPrice.getText().toString());
        showProducts(productRepository.getProductsByNameAndPrice(name, price));
    }

    // Обработка фильтрации по сроку хранения
    public void filterByShelfLife(View view) {
        int shelfLife = Integer.parseInt(filterShelfLife.getText().toString());
        showProducts(productRepository.getProductsByShelfLife(shelfLife));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Получаем обновленный продукт
            Product updatedProduct = (Product) data.getSerializableExtra("updatedProduct");
            if (updatedProduct != null) {
                // Обновляем продукт в репозитории
                productRepository.updateProduct(updatedProduct);
                showProducts(productRepository.products);
            }
        }
    }
}
