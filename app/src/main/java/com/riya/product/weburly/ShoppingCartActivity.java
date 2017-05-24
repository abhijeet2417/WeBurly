package com.riya.product.weburly;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import adapter.CartAdapter;
import local_database.DBHelper;

public class ShoppingCartActivity extends AppCompatActivity {
    private ListView ListView1;
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListView1=(ListView) findViewById(R.id.ListView1);
        db=new DBHelper(ShoppingCartActivity.this);

        try {

            CartAdapter adapter=new CartAdapter(ShoppingCartActivity.this,db.getProductList());
            ListView1.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
