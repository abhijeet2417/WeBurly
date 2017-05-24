package com.riya.product.weburly;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapter.CustomExpandableListAdapter;
import adapter.ExpandableListDataPump;
import comman.Methods;
import comman.PreferenceHelper;
import fragment.Home;
import local_database.DBHelper;
import modelclass.Products;
import modelclass.Responce;
import server_communication.ServerUrl;
import server_communication.Server_Connection;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private ExpandableListView expandableListView;
    private List<String> expandableListTitle;
    private HashMap<String, List<Products>> expandableListDetail;
    private DBHelper db;
    private TextView txtcount;
    private PreferenceHelper pref;
    public static boolean countFlag=false;
    public String categoryString="[{\"categoryname\":\"Fruits & Vegetables\",\"categoryid\":\"2\",\"subcatgory\":[{\"subcategoryid\":\"43\",\"subcategoryname\":\"Fruits\"},{\"subcategoryid\":\"44\",\"subcategoryname\":\"Vegetables\"}]},{\"categoryname\":\"Beverages\",\"categoryid\":\"2\",\"subcatgory\":[{\"subcategoryid\":\"43\",\"subcategoryname\":\"Tea & coffee\"}]},{\"categoryname\":\"Grocery & Staples\",\"categoryid\":\"1\",\"subcatgory\":[{\"subcategoryid\":\"7\",\"subcategoryname\":\"Dals & palses\"},{\"subcategoryid\":\"5\",\"subcategoryname\":\"Dry Fruits\"},{\"subcategoryid\":\"11\",\"subcategoryname\":\"Flours & Sooji\"},{\"subcategoryid\":\"12\",\"subcategoryname\":\"Masals & spices\"},{\"subcategoryid\":\"141\",\"subcategoryname\":\"Rice\"},{\"subcategoryid\":\"13\",\"subcategoryname\":\"Salt & Sugar\"}]},{\"categoryname\":\"Bread Dairy & Eggs\",\"categoryid\":\"43\",\"subcatgory\":[{\"subcategoryid\":\"86\",\"subcategoryname\":\"Bread & Baker\"},{\"subcategoryid\":\"85\",\"subcategoryname\":\"Dairy Product\"},{\"subcategoryid\":\"87\",\"subcategoryname\":\"Eggs\"}]},{\"categoryname\":\"Fruits & Vegetables\",\"categoryid\":\"44\",\"subcatgory\":[{\"subcategoryid\":\"83\",\"subcategoryname\":\"Fruits\"},{\"subcategoryid\":\"121\",\"subcategoryname\":\"Vegetables\"}]}]\n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      //  expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        db = new DBHelper(MainActivity.this);

      //  new AsyncGetDrowerCaterory().execute();

        JSONArray array = null;
        try {
            array = new JSONArray(categoryString.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        expandableListDetail = ExpandableListDataPump.getData(array);
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        ExpandableListAdapter expandableListAdapter = new CustomExpandableListAdapter(MainActivity.this, expandableListTitle, expandableListDetail);
       // expandableListView.setAdapter(expandableListAdapter);

        pref = new PreferenceHelper(MainActivity.this);
        Fragment fragment = new Home(); // this fragment contains the list with all the "test" items
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        /*expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Products item = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                Fragment fragment;
                pref.addString("catid", item.getCategoryid());
                pref.addString("subcatid", item.getSubcategoryid());
                db.insertBackstack(item.getCategoryid(),item.getSubcategoryid());

                fragment = new CommanProductView();
                fragmentManager.beginTransaction().addToBackStack(item.getSubcategoryname());
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

                *//*if (item.equals("Ruby")) {

                    fragment=new NaturalRuby();
                    fragmentManager.beginTransaction().addToBackStack("GuestFragment");
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

                } else if (item.equals("Coral")) {

                    fragment = new Coral(); // this fragment contains the list with all the "test" items
                    fragmentManager.beginTransaction().addToBackStack("GuestFragment");
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

                }else if (item.equals("Emerald")) {

                    fragment = new Emerald(); // this fragment contains the list with all the "test" items
                    fragmentManager.beginTransaction().addToBackStack("GuestFragment");
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

                }else if (item.equals("Sapphire & Lapis Lazuli")) {

                    fragment = new SapphireLapisLazuli(); // this fragment contains the list with all the "test" items
                    fragmentManager.beginTransaction().addToBackStack("GuestFragment");
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

                }else if (item.equals("Pearl")) {

                    fragment = new Pearl(); // this fragment contains the list with all the "test" items
                    fragmentManager.beginTransaction().addToBackStack("GuestFragment");
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

                }else if(item.equals("My Account Setting")){

                    startActivity(new Intent(MainActivity.this,SignInActivity.class));
                }*//*

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


    }

    private class AsyncGetDrowerCaterory extends AsyncTask<Void, Void, Responce> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Responce doInBackground(Void... params) {
            Server_Connection sc = new Server_Connection();
            return sc.getMethod(ServerUrl.DrowerCategory.SERVICE_URL);
        }

        @Override
        protected void onPostExecute(Responce result) {

            if (result.isStatus()) {
                try {
                    JSONArray array = new JSONArray(result.getResponce());
                    expandableListDetail = ExpandableListDataPump.getData(array);
                    expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                    ExpandableListAdapter expandableListAdapter = new CustomExpandableListAdapter(MainActivity.this, expandableListTitle, expandableListDetail);
                    expandableListView.setAdapter(expandableListAdapter);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                Methods.showMessage(MainActivity.this, result.getMessage());
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        int i = fragmentManager.getBackStackEntryCount();
        Log.e("length", i + "");

        if (fragmentManager.getBackStackEntryCount() == 0) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                // super.onBackPressed();
                finish();
            }
        } else {

            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);

            } else {
                db = new DBHelper(MainActivity.this);
                db.deleteBackstack();
                fragmentManager.popBackStack();
            }


            //  removeCurrentFragment();


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
        txtcount = (TextView) notificationCount1.findViewById(R.id.counter);
        txtcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,ShoppingCartActivity.class);
                startActivity(it);

            }
        });

        db = new DBHelper(MainActivity.this);
        int c = db.getCartCount();
        txtcount.setText(c + "");
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(countFlag) {
            db = new DBHelper(MainActivity.this);
            int c = db.getCartCount();
            txtcount.setText(c + "");
            countFlag=false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   /* @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;

       *//* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

            fragment=new NaturalRuby();
            fragmentManager.beginTransaction().addToBackStack("GuestFragment");
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

        } else if (id == R.id.nav_send) {

            fragment = new Coral(); // this fragment contains the list with all the "test" items
            fragmentManager.beginTransaction().addToBackStack("GuestFragment");
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

        }else if (id == R.id.emerald) {

            fragment = new Emerald(); // this fragment contains the list with all the "test" items
            fragmentManager.beginTransaction().addToBackStack("GuestFragment");
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

        }else if (id == R.id.Sapphire) {

            fragment = new SapphireLapisLazuli(); // this fragment contains the list with all the "test" items
            fragmentManager.beginTransaction().addToBackStack("GuestFragment");
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

        }else if (id == R.id.pearl) {

            fragment = new Pearl(); // this fragment contains the list with all the "test" items
            fragmentManager.beginTransaction().addToBackStack("GuestFragment");
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

        }else if(id ==R.id.myaccount){

            startActivity(new Intent(MainActivity.this,SignInActivity.class));
        }*//*

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/


}
