package fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.riya.product.weburly.ProductDetailsActivity;
import com.riya.product.weburly.R;

import org.json.JSONArray;

import java.util.List;

import adapter.AllProductAdapter;
import comman.Methods;
import local_database.DBHelper;
import modelclass.Products;
import modelclass.Responce;
import server_communication.ServerUrl;
import server_communication.Server_Connection;

/**
 * Created by product on 10/25/2016.
 */
public class CommanProductView extends Fragment {

    private GridView progridview;
    private JSONArray jsonArray=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.naturalruby_layout, container, false);
        progridview = (GridView) rootView.findViewById(R.id.gridView);
        TextView txtsort = (TextView) rootView.findViewById(R.id.txtsort);
        TextView txtfilter = (TextView) rootView.findViewById(R.id.txtfilter);

        DBHelper db = new DBHelper(getActivity());
        List<Products> list= db.getLatestBackstack();
        Products pro=list.get(0);
        String catid = pro.getCategoryid();
        String scatid = pro.getSubcategoryid();
        jsonArray=new JSONArray();

        new AsyncGetProductList().execute(catid, scatid);
       // new AsyncGetProductList().execute("43", "0");

        progridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String proid = jsonArray.getJSONObject(position).getString("product_code");
                    Intent it = new Intent(getActivity(), ProductDetailsActivity.class);
                    it.putExtra("position", position);
                    it.putExtra("proid", jsonArray.toString());
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


        return rootView;
    }

    private void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.sort_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
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
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.filter_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
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

    private class AsyncGetProductList extends AsyncTask<String, Void, Responce> {
        @Override
        protected void onPreExecute() {
            Methods.showProgressDialog(getActivity());
        }

        @Override
        protected Responce doInBackground(String... params) {
            Server_Connection sc = new Server_Connection();
            return sc.getMethod(ServerUrl.GetSubCategoryProduct.SERVICE_URL + params[0] + "/" + params[1]);
        }

        @Override
        protected void onPostExecute(Responce result) {
            Methods.closeProgressDialog();
            if (result.isStatus()) {
                try {
                    Log.e("responce",result.getResponce());
                    jsonArray=new JSONArray(result.getResponce());
                    AllProductAdapter adapter=new AllProductAdapter(getActivity(),jsonArray);
                    progridview.setAdapter(adapter);

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
