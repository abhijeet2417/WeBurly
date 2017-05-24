package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.riya.product.weburly.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Ganesh on 18-09-2016.
 */
public class HorizontalListAdapter extends BaseAdapter {

    private final int[] jsonArray;
    String[] str;
    private final Context context;

    public HorizontalListAdapter(Context context,int[]jsonArray){
        this.jsonArray=jsonArray;
        this.context=context;
    }


    @Override
    public int getCount() {
        return jsonArray.length;
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

        View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_row_layout, null);
        ImageView img=(ImageView) retval.findViewById(R.id.imgpro);
        TextView txtproname=(TextView) retval.findViewById(R.id.txtproname);
        TextView txtprice=(TextView) retval.findViewById(R.id.txtprice);

        try{

            //txtproname.setText(jsonArray.getJSONObject(position).getString("name"));
            //txtprice.setText("Rs. "+jsonArray.getJSONObject(position).getString("price")+"/-");

            txtproname.setText("Apple");
            txtprice.setText("Rs. "+200+"/-");

         //   String imgUrl="http://www.weburly.com/image/"+jsonArray.getJSONObject(position).getString("mainimg");
            Picasso.with(context).load(jsonArray[position]).fit().into(img);

        }catch (Exception ex){

        }

        //http://www.weburly.com/image/product347_1.jpg


      //  Glide.with(context).load(str[position]).into(img);


        return retval;
    }
}
