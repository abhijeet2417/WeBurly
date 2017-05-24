package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.riya.product.weburly.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import server_communication.ServerUrl;

/**
 * Created by product on 9/21/2016.
 */
public class AllProductAdapter extends BaseAdapter {

    private final JSONArray jsonArray;
    private final Context context;

    public AllProductAdapter(Context context, JSONArray jsonArray) {
        this.jsonArray = jsonArray;
        this.context = context;

    }


    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_row_layout, null);
            holder.proImg = (ImageView) convertView.findViewById(R.id.imgpro);
            holder.txtproname = (TextView) convertView.findViewById(R.id.txtproname);
            holder.txtamount = (TextView) convertView.findViewById(R.id.txtprice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {
            String imgUrl = ServerUrl.IMAGE_URL+jsonArray.getJSONObject(position).getString("mainimg");
            Picasso.with(context).load(imgUrl).fit().into(holder.proImg);
         //   Glide.with(context).load(imgUrl).into(holder.proImg);
         //   imageLoader.displayImage(imgUrl, holder.proImg);
            holder.txtproname.setText(jsonArray.getJSONObject(position).getString("name"));
            holder.txtamount.setText("Rs. "+jsonArray.getJSONObject(position).getString("price")+ "/-");
            Log.e("img Url",imgUrl);
        } catch (Exception ex) {

        }

        return convertView;
    }

    private class ViewHolder {
        ImageView proImg;
        TextView txtproname;
        TextView txtamount;


    }
}
