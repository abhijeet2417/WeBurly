package adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import fragment.ProductDetailsFragment;


/**
 * Created by product on 12/14/2016.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    JSONArray jarray;

    public MyPagerAdapter(FragmentManager fragmentManager, JSONArray jarray) {
        super(fragmentManager);
        this.jarray = jarray;
    }

    @Override
    public Fragment getItem(int position) {

        ProductDetailsFragment obj = new ProductDetailsFragment();

        try {
            String proid = "0";//jarray.getJSONObject(position).getString("product_code");
            Bundle args = new Bundle();
            args.putString("proid", proid);
            obj.setArguments(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public int getCount() {
        return jarray.length();
    }




    /*JSONArray jarray;
    private static int NUM_ITEMS = 3;


    public MyPagerAdapter(FragmentManager fragmentManager, JSONArray jarray) {
        super(fragmentManager);
        this.jarray = jarray;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return jarray.length();
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        ProductDetailsFragment obj = new ProductDetailsFragment();

        try {
            String proid = jarray.getJSONObject(position).getString("product_code");
            Bundle args = new Bundle();
            args.putString("proid", proid);
            obj.setArguments(args);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;


       *//* switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return FirstFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return FirstFragment.newInstance(1, "Page # 2");
            case 2: // Fragment # 1 - This will show SecondFragment
                return SecondFragment.newInstance(2, "Page # 3");
            default:
                return null;
        }*//*
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }*/

}

