package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.riya.product.weburly.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import modelclass.Products;
import server_communication.ServerUrl;

/**
 * Created by product on 11/3/2016.
 */
public class CartAdapter extends BaseAdapter {

    private final List<Products> jsonArray;
    String[] str={"red","blue","white"};
    private final Context context;
    private final ImageLoader imageLoader;

    public CartAdapter(Context context,List<Products> jsonArray) {
        this.jsonArray = jsonArray;
        this.context = context;
        imageLoader = ImageLoader.getInstance();
    }


    @Override
    public int getCount() {
        return jsonArray.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppingcart_row_layout, null);
            holder.proImg = (ImageView) convertView.findViewById(R.id.imgpro);
            holder.txtproname=(TextView) convertView.findViewById(R.id.txtproname);
            holder.txtcolor=(TextView) convertView.findViewById(R.id.txtcolor);
            holder.txtprice=(TextView) convertView.findViewById(R.id.txtprice);
            holder.txtqty=(TextView) convertView.findViewById(R.id.txtqty);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {
            Products obj=jsonArray.get(position);
            holder.txtproname.setText(obj.getProName());
            holder.txtprice.setText("Rs."+obj.getProPrice()+"/-");
            holder.txtcolor.setText(obj.getProcolor());
            holder.txtqty.setText(obj.getProQty());

            String imgUrl = ServerUrl.IMAGE_URL+obj.getProimage();
            Picasso.with(context).load(R.drawable.apple).fit().into(holder.proImg);



        } catch (Exception ex) {

        }

        return convertView;
    }

    private class ViewHolder {
        ImageView proImg;
        TextView txtproname;
        TextView txtprice;
        TextView txtcolor;
        TextView txtqty;

    }
}
