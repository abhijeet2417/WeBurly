package com.riya.product.weburly;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    private EditText txtsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtsearch=(EditText) findViewById(R.id.txtsearch);

        txtsearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_ENTER) {

                    String str=txtsearch.getText().toString();
                    Toast.makeText(SearchActivity.this, str, Toast.LENGTH_SHORT).show();


                }

                return false;
            }
        });


    }

    /*@Override
    public boolean onSearchRequested() {
        // your stuff here
        String str=txtsearch.getText().toString();
        Toast.makeText(SearchActivity.this, str, Toast.LENGTH_SHORT).show();

        return false;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
