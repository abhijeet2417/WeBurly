package com.riya.product.weburly;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import adapter.MyPagerAdapter;
import comman.Methods;
import local_database.DBHelper;
import modelclass.Responce;
import server_communication.ServerUrl;
import server_communication.Server_Connection;

public class ProductDetailsActivity extends AppCompatActivity {
    private ImageView proimage;
    private DBHelper db;
    private TextView txtprice;
    private TextView txtname;
    private Spinner spcolor;
    private static TextView txtcount;
    private JSONObject obj;

    MyPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        String proid = getIntent().getStringExtra("proid");
        int pos=0;//getIntent().getIntExtra("position",0);
        try {

            JSONArray jarray = new JSONArray();
            JSONObject json1=new JSONObject();
            jarray.put(json1);
            adapterViewPager = new MyPagerAdapter(getSupportFragmentManager(),jarray);
            vpPager.setAdapter(adapterViewPager);
            vpPager.setCurrentItem(pos);

        }catch (Exception ex){

        }

     /* txtname = (TextView) findViewById(R.id.txtname);
        proimage = (ImageView) findViewById(R.id.proimage);
        Button btnaddtocart = (Button) findViewById(R.id.btnaddtocart);
        txtprice = (TextView) findViewById(R.id.txtprice);
        spcolor = (Spinner) findViewById(R.id.spcolor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        obj = new JSONObject();
        db = new DBHelper(ProductDetailsActivity.this);
        String proid = getIntent().getStringExtra("proid");
        new AsyncCaller().execute(proid);

        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Products pro = new Products();
                try {
                    pro.setProName(obj.getString("name"));
                    pro.setProPrice(obj.getString("price"));
                    pro.setProQty("1");
                    pro.setProTotalAmt(obj.getString("price"));
                    pro.setProcolor(spcolor.getSelectedItem().toString());
                    pro.setProductId(obj.getString("product_code"));
                    pro.setProimage(obj.getString("mainimg"));

                    int l = db.insert(pro);
                    if (l > 0) {
                        Toast.makeText(ProductDetailsActivity.this, "Items add to cart", Toast.LENGTH_LONG).show();
                    }

                    int c = db.getCartCount();
                    String count=c+"";
                    txtcount.setText(count);
                    MainActivity.countFlag = true;

                } catch (Exception ex) {

                }
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //getMenuInflater().inflate(R.menu.home, menu);

        MenuItem item1 = menu.findItem(R.id.action_settings);
        MenuItemCompat.setActionView(item1, R.layout.actionbar_badge_layout);
        RelativeLayout notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        txtcount = (TextView) notificationCount1.findViewById(R.id.counter);
        txtcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ProductDetailsActivity.this,ShoppingCartActivity.class);
                startActivity(it);
            }
        });

        db = new DBHelper(ProductDetailsActivity.this);
        int c = db.getCartCount();
        String count=c+"";
        txtcount.setText(count);
        return true;
    }

    private class AsyncCaller extends AsyncTask<String, Void, Responce> {

        @Override
        protected void onPreExecute() {
            Methods.showProgressDialog(ProductDetailsActivity.this);

        }

        @Override
        protected Responce doInBackground(String... params) {
            Server_Connection sc = new Server_Connection();
            return sc.getMethod(ServerUrl.BASE_URL + params[0]);
        }

        @Override
        protected void onPostExecute(Responce result) {
            Methods.closeProgressDialog();
            if (result.isStatus()) {
                String colors;
                try {
                    obj = new JSONObject(result.getResponce());
                    String name=obj.getString("name");
                    String price="Rs." + obj.getString("price") + "/-";
                    txtname.setText(name);
                    txtprice.setText(price);

                    String imgUrl = ServerUrl.IMAGE_URL + obj.getString("mainimg");
                    Picasso.with(ProductDetailsActivity.this).load(imgUrl).fit().into(proimage);
                    //  Glide.with(ProductDetailsActivity.this).load(imgUrl).into(proimage);

                    colors = obj.getString("color");
                    String[] str = colors.split(",");
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ProductDetailsActivity.this, android.R.layout.simple_spinner_item, str); //selected item will look like a spinner set from XML
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spcolor.setAdapter(spinnerArrayAdapter);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                Methods.showMessage(ProductDetailsActivity.this, result.getMessage());
            }
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
