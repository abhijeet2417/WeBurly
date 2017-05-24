package fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.meetme.android.horizontallistview.HorizontalListView;
import com.riya.product.weburly.MainActivity;
import com.riya.product.weburly.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import adapter.HorizontalListAdapter;
import comman.Methods;
import local_database.DBHelper;
import modelclass.Products;
import modelclass.Responce;
import server_communication.ServerUrl;
import server_communication.Server_Connection;

/**
 * Created by product on 12/14/2016.
 */
public class ProductDetailsFragment extends Fragment {

    private ImageView proimage;
    private DBHelper db;
    private TextView txtprice;
    private TextView txtname;
    private Spinner spcolor;
    private static TextView txtcount;
    private JSONObject obj;
    String proid ="";
    private LinearLayout linlaHeaderProgress;
    private HorizontalListView list_decor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_product_details, container, false);

        txtname = (TextView)rootView.findViewById(R.id.txtname);
        proimage = (ImageView)rootView.findViewById(R.id.proimage);
        Button btnaddtocart = (Button)rootView.findViewById(R.id.btnaddtocart);
        txtprice = (TextView)rootView.findViewById(R.id.txtprice);

        linlaHeaderProgress=(LinearLayout) rootView.findViewById(R.id.linlaHeaderProgress);
        list_decor=(HorizontalListView)rootView.findViewById(R.id.list_decor);

        int [] f={R.drawable.apple,R.drawable.banana,R.drawable.chickoo,R.drawable.grapes,R.drawable.custardapple,R.drawable.watermelon};

        HorizontalListAdapter wbcollectionAdapter=new HorizontalListAdapter(getActivity(),f);
        list_decor.setAdapter(wbcollectionAdapter);


        try {
          //  obj = new JSONObject(result.getResponce());
           // String name=obj.getString("name");
            String price="Rs." + 10 + "/kg";
            txtname.setText("Apple");
            txtprice.setText(price);

            //String imgUrl = ServerUrl.IMAGE_URL + obj.getString("mainimg");
            Picasso.with(getActivity()).load(R.drawable.apple).fit().into(proimage);
            //  Glide.with(ProductDetailsActivity.this).load(imgUrl).into(proimage);

            //colors = obj.getString("color");
            //String[] str = colors.split(",");
            //ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, str); //selected item will look like a spinner set from XML
            //spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //spcolor.setAdapter(spinnerArrayAdapter);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        obj = new JSONObject();
        db = new DBHelper(getActivity());


        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Products pro = new Products();
                try {
                    pro.setProName("apple");
                    pro.setProPrice("100");
                    pro.setProQty("1");
                    pro.setProTotalAmt("100");
                    pro.setProcolor("");
                    pro.setProductId("1");
                    pro.setProimage("");

                    int l = db.insert(pro);
                    if (l > 0) {
                        Toast.makeText(getActivity(), "Items add to cart", Toast.LENGTH_LONG).show();
                    }

                    int c = db.getCartCount();
                    String count=c+"";
                    txtcount.setText(count);
                    MainActivity.countFlag = true;

                } catch (Exception ex) {

                }
            }
        });

        //new AsyncCaller().execute(proid);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        //getMenuInflater().inflate(R.menu.home, menu);

        MenuItem item1 = menu.findItem(R.id.action_settings);
        MenuItemCompat.setActionView(item1, R.layout.actionbar_badge_layout);
        RelativeLayout notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        txtcount = (TextView) notificationCount1.findViewById(R.id.counter);


        db = new DBHelper(getActivity());
        int c = db.getCartCount();
        txtcount.setText(c + "");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        proid =getArguments().getString("proid");
    }




    private class AsyncCaller extends AsyncTask<String, Void, Responce> {

        @Override
        protected void onPreExecute() {
           // Methods.showProgressDialog(getActivity());
            linlaHeaderProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Responce doInBackground(String... params) {
            Server_Connection sc = new Server_Connection();
            return sc.getMethod(ServerUrl.BASE_URL + params[0]);
        }

        @Override
        protected void onPostExecute(Responce result) {
            linlaHeaderProgress.setVisibility(View.GONE);
            if (result.isStatus()) {
                String colors;
                try {
                    obj = new JSONObject(result.getResponce());
                    String name=obj.getString("name");
                    String price="Rs." + obj.getString("price") + "/-";
                    txtname.setText(name);
                    txtprice.setText(price);

                    String imgUrl = ServerUrl.IMAGE_URL + obj.getString("mainimg");
                    Picasso.with(getActivity()).load(imgUrl).fit().into(proimage);
                    //  Glide.with(ProductDetailsActivity.this).load(imgUrl).into(proimage);

                    colors = obj.getString("color");
                    String[] str = colors.split(",");
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, str); //selected item will look like a spinner set from XML
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spcolor.setAdapter(spinnerArrayAdapter);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                Methods.showMessage(getActivity(), result.getMessage());
            }
        }
    }
}
