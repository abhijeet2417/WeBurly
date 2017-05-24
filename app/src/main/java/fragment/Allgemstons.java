package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.riya.product.weburly.R;

import org.json.JSONArray;

import adapter.AllProductAdapter;

/**
 * Created by product on 9/21/2016.
 */
public class Allgemstons extends Fragment {

    private GridView progridview;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.allgemstons_layout, container, false);
        progridview=(GridView) rootView.findViewById(R.id.gridView);
        JSONArray jsonArray=new JSONArray();
        AllProductAdapter adapter=new AllProductAdapter(getActivity(),jsonArray);
        progridview.setAdapter(adapter);



        return rootView;
    }
}
