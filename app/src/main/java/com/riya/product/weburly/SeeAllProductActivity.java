package com.riya.product.weburly;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONArray;

import adapter.AllProductAdapter;
import comman.Methods;
import local_database.DBHelper;
import modelclass.Responce;
import server_communication.ServerUrl;
import server_communication.Server_Connection;

public class SeeAllProductActivity extends AppCompatActivity {

    private GridView progridview;
    private DBHelper db;
    private JSONArray jarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pottery_see_all);
        progridview = (GridView) findViewById(R.id.gridView);
        TextView txtsort = (TextView) findViewById(R.id.txtsort);
        TextView txtfilter = (TextView) findViewById(R.id.txtfilter);
        jarray = new JSONArray();

        db = new DBHelper(SeeAllProductActivity.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String catid = getIntent().getStringExtra("catid");

        progridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String proid = jarray.getJSONObject(position).getString("product_code");
                    Intent it = new Intent(SeeAllProductActivity.this, ProductDetailsActivity.class);
                    it.putExtra("proid", proid);
                    startActivity(it);

                } catch (Exception ex) {

                }

            }
        });

        txtsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
        txtfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showfilterDialog();
            }
        });

        new AsyncCaller().execute(catid);
    }

    private void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(SeeAllProductActivity.this);
        View promptView = layoutInflater.inflate(R.layout.sort_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SeeAllProductActivity.this);
        alertDialogBuilder.setView(promptView);

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("APPLY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void showfilterDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(SeeAllProductActivity.this);
        View promptView = layoutInflater.inflate(R.layout.filter_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SeeAllProductActivity.this);
        alertDialogBuilder.setView(promptView);
        SeekBar seekbar = (SeekBar) promptView.findViewById(R.id.seekBar);
        final TextView txtmaxprice = (TextView) promptView.findViewById(R.id.txtmaxprice);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtmaxprice.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("APPLY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


    private class AsyncCaller extends AsyncTask<String, Void, Responce> {

        @Override
        protected void onPreExecute() {
            Methods.showProgressDialog(SeeAllProductActivity.this);

        }

        @Override
        protected Responce doInBackground(String... params) {
            Server_Connection sc = new Server_Connection();
            return sc.getMethod(ServerUrl.BASE_URL + params[0] + "/0");
        }

        @Override
        protected void onPostExecute(Responce result) {
            Methods.closeProgressDialog();
            if (result.isStatus()) {
                try {
                    jarray = new JSONArray(result.getResponce());
                    AllProductAdapter adapter = new AllProductAdapter(SeeAllProductActivity.this, jarray);
                    progridview.setAdapter(adapter);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                Methods.showMessage(SeeAllProductActivity.this, result.getMessage());
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //getMenuInflater().inflate(R.menu.home, menu);

        MenuItem item1 = menu.findItem(R.id.action_settings);
        MenuItemCompat.setActionView(item1, R.layout.actionbar_badge_layout);
        RelativeLayout notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        TextView txtcount = (TextView) notificationCount1.findViewById(R.id.counter);
        txtcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(SeeAllProductActivity.this,ShoppingCartActivity.class);
                startActivity(it);

            }
        });

        db = new DBHelper(SeeAllProductActivity.this);
        String c = db.getCartCount()+"";
        txtcount.setText(c);
        return true;
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
