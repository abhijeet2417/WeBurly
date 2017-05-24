package fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.meetme.android.horizontallistview.HorizontalListView;
import com.riya.product.weburly.ProductDetailsActivity;
import com.riya.product.weburly.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import adapter.HorizontalListAdapter;
import comman.Methods;
import local_database.DBHelper;
import modelclass.Responce;
import server_communication.ServerUrl;
import server_communication.Server_Connection;

/**
 * Created by product on 9/19/2016.
 */
public class Home extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;
    private HorizontalListView listView;
    private HorizontalListView list_tones;
    private HorizontalListView list_decor;
    private JSONArray jsonwbCollection=null;
    private JSONArray jsonjewellery;
    private JSONArray jsongiftitem;
    private DBHelper db;
    private String responceStr;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.content_main, container, false);
        mDemoSlider = (SliderLayout)rootView.findViewById(R.id.slider);
        listView=(HorizontalListView)rootView.findViewById(R.id.list_horizontal);
        list_tones=(HorizontalListView)rootView.findViewById(R.id.list_tones);
        list_decor=(HorizontalListView)rootView.findViewById(R.id.list_decor);
        TextView txtjewellery = (TextView) rootView.findViewById(R.id.txtjewellery);
        ImageView imgsearch = (ImageView) rootView.findViewById(R.id.imgsearch);
        TextView txtpotteryproduct = (TextView) rootView.findViewById(R.id.txtpotteryproduct);
        TextView txtgiftitem = (TextView) rootView.findViewById(R.id.txtgiftitem);

        db = new DBHelper(getActivity());
        jsonwbCollection=new JSONArray();
        jsonjewellery=new JSONArray();
        jsongiftitem=new JSONArray();

      /* txtjewellery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               *//* Intent it=new Intent(getActivity(), SeeAllProductActivity.class);
                it.putExtra("catid","43");
                startActivity(it);*//*
                db.insertBackstack("43","0");
                Fragment fragment = new CommanProductView();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().addToBackStack("jewellery");
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            }
        });
        txtpotteryproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               *//* Intent it=new Intent(getActivity(), SeeAllProductActivity.class);
                it.putExtra("catid","2");
                startActivity(it);*//*
                db.insertBackstack("2","0");
                Fragment fragment = new CommanProductView();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().addToBackStack("pottery");
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();


            }
        });
        txtgiftitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*Intent it=new Intent(getActivity(), SeeAllProductActivity.class);
                it.putExtra("catid","44");
                startActivity(it);*//*

                db.insertBackstack("44","0");
                Fragment fragment = new CommanProductView();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().addToBackStack("gift");
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

            }
        });

*/

       /* imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), SearchActivity.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            }
        });*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String proid = "0";//jsonwbCollection.getJSONObject(position).getString("product_code");
                    Intent it = new Intent(getActivity(), ProductDetailsActivity.class);
                    it.putExtra("proid", proid);
                    startActivity(it);

                } catch (Exception ex) {

                }

            }
        });

        list_tones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String proid = "0";//jsonjewellery.getJSONObject(position).getString("product_code");
                    Intent it = new Intent(getActivity(), ProductDetailsActivity.class);
                    it.putExtra("proid", proid);
                    startActivity(it);

                } catch (Exception ex) {

                }

            }
        });

        list_decor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String proid = "0";//jsongiftitem.getJSONObject(position).getString("product_code");
                    Intent it = new Intent(getActivity(), ProductDetailsActivity.class);
                    it.putExtra("proid", proid);
                    startActivity(it);

                } catch (Exception ex) {

                }

            }
        });





        return rootView;


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("someVarB", responceStr);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState!=null) {

            bindData(savedInstanceState.getString("someVarB"));

        }else{

            new AsyncCallerHome().execute();
        }
    }


    public void bindData(String data){
        try {

            JSONObject obj=new JSONObject(data);
            responceStr=data;
            jsonwbCollection=obj.getJSONArray("WeburlyCollection");
            jsonjewellery=obj.getJSONArray("Jewellery");
            jsongiftitem=obj.getJSONArray("GiftItems");

           int [] f={R.drawable.apple,R.drawable.banana,R.drawable.chickoo,R.drawable.grapes,R.drawable.custardapple,R.drawable.watermelon};
            int [] v={R.drawable.imgmilk,R.drawable.imgmilk_two,R.drawable.dahi,R.drawable.brinjal,R.drawable.cauliflower,R.drawable.greenchili};
            int [] g={R.drawable.tur_daal,R.drawable.urad_daal,R.drawable.rajma,R.drawable.moong,R.drawable.matki};

           HorizontalListAdapter wbcollectionAdapter=new HorizontalListAdapter(getActivity(),f);
            listView.setAdapter(wbcollectionAdapter);

            HorizontalListAdapter add=new HorizontalListAdapter(getActivity(),v);
            list_tones.setAdapter(add);

            HorizontalListAdapter addd=new HorizontalListAdapter(getActivity(),g);
            list_decor.setAdapter(addd);

            JSONArray jarray=obj.getJSONArray("Homeimages");
           // Uri uri= Uri.parse("R.drawable.spices");
           /* String Imagurl1=uri.toString();
          //  String Imagurl2="http://weburly.com/images/"+jarray.getJSONObject(0).getString("img2");
           // String Imagurl3="http://weburly.com/images/"+jarray.getJSONObject(0).getString("img3");
           // String Imagurl4="http://weburly.com/images/"+jarray.getJSONObject(0).getString("img4");

            HashMap<String,String> url_maps = new HashMap<String, String>();
            url_maps.put("Weburly Collection", Imagurl1);
          //  url_maps.put("Jewellery", Imagurl2);
          //  url_maps.put("Gift Items", Imagurl3);
          //  url_maps.put("Gemestones", Imagurl4);*/

            HashMap<String,Integer> url_maps = new HashMap<String, Integer>();
            url_maps.put("Spices",R.drawable.spices);
            url_maps.put("Vegetables",R.drawable.vegetables);
            url_maps.put("Fruits",R.drawable.frouts);
            url_maps.put("Pulses", R.drawable.pulses);


            for(String name : url_maps.keySet()){
                TextSliderView textSliderView = new TextSliderView(getActivity());
                // initialize a SliderLayout
                textSliderView
                        .description(name)
                        .image(url_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(Home.this);

                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra",name);

                mDemoSlider.addSlider(textSliderView);
            }
            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mDemoSlider.setCustomAnimation(new DescriptionAnimation());
            mDemoSlider.setDuration(4000);
            mDemoSlider.addOnPageChangeListener(Home.this);



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {

        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onStart() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.startAutoCycle();
        super.onStart();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    private class AsyncCallerHome extends AsyncTask<Void, Void, Responce> {

        @Override
        protected void onPreExecute() {
            Methods.showProgressDialog(getActivity());
        }

        @Override
        protected Responce doInBackground(Void... params) {
            Server_Connection sc = new Server_Connection();
            return sc.getMethod(ServerUrl.BASE_URL +"Home");
        }

        @Override
        protected void onPostExecute(Responce result) {
            Methods.closeProgressDialog();
            if (result.isStatus()) {

                try {

                    bindData(result.getResponce());

                   /* JSONObject obj=new JSONObject(result.getResponce());

                    jsonwbCollection=obj.getJSONArray("WeburlyCollection");
                    jsonjewellery=obj.getJSONArray("Jewellery");
                    jsongiftitem=obj.getJSONArray("GiftItems");

                    HorizontalListAdapter wbcollectionAdapter=new HorizontalListAdapter(getActivity(),obj.getJSONArray("WeburlyCollection"));
                    listView.setAdapter(wbcollectionAdapter);

                    HorizontalListAdapter add=new HorizontalListAdapter(getActivity(),obj.getJSONArray("Jewellery"));
                    list_tones.setAdapter(add);

                    HorizontalListAdapter addd=new HorizontalListAdapter(getActivity(),obj.getJSONArray("GiftItems"));
                    list_decor.setAdapter(addd);

                    JSONArray jarray=obj.getJSONArray("Homeimages");
                    String Imagurl1="http://weburly.com/images/"+jarray.getJSONObject(0).getString("img1");
                    String Imagurl2="http://weburly.com/images/"+jarray.getJSONObject(0).getString("img2");
                    String Imagurl3="http://weburly.com/images/"+jarray.getJSONObject(0).getString("img3");
                    String Imagurl4="http://weburly.com/images/"+jarray.getJSONObject(0).getString("img4");

                    HashMap<String,String> url_maps = new HashMap<String, String>();
                    url_maps.put("Weburly Collection", Imagurl1);
                    url_maps.put("Jewellery", Imagurl2);
                    url_maps.put("Gift Items", Imagurl3);
                    url_maps.put("Gemestones", Imagurl4);


                    for(String name : url_maps.keySet()){
                        TextSliderView textSliderView = new TextSliderView(getActivity());
                        // initialize a SliderLayout
                        textSliderView
                                .description(name)
                                .image(url_maps.get(name))
                                .setScaleType(BaseSliderView.ScaleType.Fit)
                                .setOnSliderClickListener(Home.this);

                        //add your extra information
                        textSliderView.bundle(new Bundle());
                        textSliderView.getBundle()
                                .putString("extra",name);

                        mDemoSlider.addSlider(textSliderView);
                    }
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                    mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                    mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                    mDemoSlider.setDuration(4000);
                    mDemoSlider.addOnPageChangeListener(Home.this);
*/


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
