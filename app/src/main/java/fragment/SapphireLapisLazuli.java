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
public class SapphireLapisLazuli extends Fragment {

    GridView progridview;

    String[] str={"http://weburly.com/image/product109_1.jpg",
            "http://weburly.com/image/product122_1.jpg",
            "http://weburly.com/image/product127_1.jpg",
            "http://weburly.com/image/product113_1.jpg",
            "http://weburly.com/image/product103_1.jpg",
            "http://weburly.com/image/product121_1.jpg",
            "http://weburly.com/image/product105_1.jpg"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sapphirelapis_layout, container, false);
        progridview=(GridView) rootView.findViewById(R.id.gridView);

        JSONArray jsonArray=new JSONArray();
        AllProductAdapter adapter=new AllProductAdapter(getActivity(),jsonArray);
        progridview.setAdapter(adapter);

        return rootView;
    }
}
