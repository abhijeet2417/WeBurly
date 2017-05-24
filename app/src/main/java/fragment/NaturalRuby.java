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
public class NaturalRuby extends Fragment {

    private GridView progridview;

    String[] str={"http://weburly.com/image/product126_1.jpg",
     "http://weburly.com/image/product129_1.jpg"
    ,"http://weburly.com/image/product116_1.jpg"
    ,"http://weburly.com/image/product128_1.jpg"
    ,"http://weburly.com/image/product125_1.jpg"
    ,"http://weburly.com/image/product114_1.jpg"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.naturalruby_layout, container, false);
        progridview=(GridView) rootView.findViewById(R.id.gridView);
        JSONArray jsonArray=new JSONArray();
        AllProductAdapter adapter=new AllProductAdapter(getActivity(),jsonArray);
        progridview.setAdapter(adapter);

        return rootView;
    }
}
