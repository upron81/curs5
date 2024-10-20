package com.example.lab;

import android.content.Context;
import android.os.AsyncTask;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveDataTask extends AsyncTask<ProductRepository, Void, Void> {
    private static final String FILENAME = "products.dat";
    private final Context context;

    public SaveDataTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(ProductRepository... productRepositories) {
        ProductRepository productRepository = productRepositories[0];
        try (FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(productRepository);
        } catch (IOException e) {
        }
        return null;
    }
}
