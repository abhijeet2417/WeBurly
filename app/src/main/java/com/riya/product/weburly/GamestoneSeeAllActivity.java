package com.riya.product.weburly;

import android.content.DialogInterface;
import android.content.Intent;
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
import local_database.DBHelper;

public class GamestoneSeeAllActivity extends AppCompatActivity {

    private GridView progridview;
    private DBHelper db;
    private TextView txtcount;

    String[] str={"http://weburly.com/image/product110_1.jpg",
            "http://weburly.com/image/product126_1.jpg",
            "http://weburly.com/image/product116_1.jpg",
            "http://weburly.com/image/product112_1.jpg",
            "http://weburly.com/image/product102_1.jpg",
            "http://weburly.com/image/product128_1.jpg",
            "http://weburly.com/image/product121_1.jpg",
            "http://weburly.com/image/product105_1.jpg",
            "http://weburly.com/image/product93_1.jpg",
            "http://weburly.com/image/product106_1.jpg",
            "http://weburly.com/image/product107_1.jpg",
            "http://weburly.com/image/product108_1.jpg",
            "http://weburly.com/image/product115_1.jpg",
            "http://weburly.com/image/product111_1.jpg",
            "http://weburly.com/image/product129_1.jpg",
            "http://weburly.com/image/product109_1.jpg",
            "http://weburly.com/image/product122_1.jpg"};

        private TextView txtsort;
    private TextView txtfilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progridview=(GridView)findViewById(R.id.gridView);
        txtsort=(TextView) findViewById(R.id.txtsort);
        txtfilter=(TextView) findViewById(R.id.txtfilter);

        db=new DBHelper(GamestoneSeeAllActivity.this);

        JSONArray jsonArray=new JSONArray();
        AllProductAdapter adapter=new AllProductAdapter(GamestoneSeeAllActivity.this,jsonArray);
        progridview.setAdapter(adapter);

        progridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(GamestoneSeeAllActivity.this,ProductDetailsActivity.class));
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
    }

    private void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(GamestoneSeeAllActivity.this);
        View promptView = layoutInflater.inflate(R.layout.sort_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GamestoneSeeAllActivity.this);
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
        LayoutInflater layoutInflater = LayoutInflater.from(GamestoneSeeAllActivity.this);
        View promptView = layoutInflater.inflate(R.layout.filter_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GamestoneSeeAllActivity.this);
        alertDialogBuilder.setView(promptView);
        SeekBar seekbar=(SeekBar) promptView.findViewById(R.id.seekBar);
        final TextView txtmaxprice=(TextView) promptView.findViewById(R.id.txtmaxprice);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtmaxprice.setText(progress+"");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //getMenuInflater().inflate(R.menu.home, menu);

        MenuItem item1 = menu.findItem(R.id.action_settings);
        MenuItemCompat.setActionView(item1, R.layout.actionbar_badge_layout);
        RelativeLayout notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        txtcount= (TextView) notificationCount1.findViewById(R.id.counter);
        txtcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        db=new DBHelper(GamestoneSeeAllActivity.this);
        int c=db.getCartCount();
        txtcount.setText(c+"");
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    protected void onResume() {
        super.onResume();

        db=new DBHelper(GamestoneSeeAllActivity.this);

      //  txtcount.setText(c+"");
    }
}
