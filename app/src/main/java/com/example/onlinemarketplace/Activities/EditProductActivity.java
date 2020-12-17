package com.example.onlinemarketplace.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.onlinemarketplace.AppRepository;
import com.example.onlinemarketplace.Entities.Product;
import com.example.onlinemarketplace.R;
import com.example.onlinemarketplace.UserInfo;

public class EditProductActivity extends AppCompatActivity {

    EditText nameText;
    EditText priceText;
    EditText imageName;
    EditText decriptionText;
    private AppRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        nameText = findViewById(R.id.name);
        priceText = findViewById(R.id.price);
        imageName = findViewById(R.id.image_name);
        decriptionText = findViewById(R.id.description);

        repository = new AppRepository(getApplication());

        nameText.setText(getIntent().getStringExtra("productName"));
        priceText.setText(String.valueOf(getIntent().getDoubleExtra("productPrice", 1000)));
        imageName.setText(getIntent().getStringExtra("productImagePath"));
        decriptionText.setText(getIntent().getStringExtra("productDescription"));
        UserInfo.role = getIntent().getStringExtra("UserRole");

    }

    public void updateProductClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        Product product = new Product(getIntent().getLongExtra("productId", 1), String.valueOf(nameText.getText()),
                Double.parseDouble(String.valueOf(priceText.getText())),
                String.valueOf(imageName.getText()),
                String.valueOf(decriptionText.getText()));
        repository.updateProduct(product);
        startActivity(intent);
    }

    public void deleteProductClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        Product product = new Product(getIntent().getLongExtra("productId", 1), String.valueOf(nameText.getText()),
                Double.parseDouble(String.valueOf(priceText.getText())),
                String.valueOf(imageName.getText()),
                String.valueOf(decriptionText.getText()));
        repository.deleteProduct(product);
        startActivity(intent);
    }
}