package adapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelclass.Products;

/**
 * Created by product on 9/24/2016.
 */
public class ExpandableListDataPump {

   public static HashMap<String, List<Products>> getData(JSONArray array) {

    HashMap<String, List<Products>> expandableListDetail = new HashMap<String, List<Products>>();

    for (int i = 0; i < array.length(); i++) {
     try {
     String title=array.getJSONObject(i).getString("categoryname");
      String categoryid=array.getJSONObject(i).getString("categoryid");
     JSONArray subcatArray=array.getJSONObject(i).getJSONArray("subcatgory");

      List<Products> cricket = new ArrayList<Products>();
      for(int j=0;j<subcatArray.length();j++){

       Products pro=new Products();
       pro.setCategoryid(categoryid);
       pro.setSubcategoryid(subcatArray.getJSONObject(j).getString("subcategoryid"));
       pro.setSubcategoryname(subcatArray.getJSONObject(j).getString("subcategoryname"));


       cricket.add(pro);
      }
      expandableListDetail.put(title, cricket);

     } catch (JSONException e) {
      e.printStackTrace();
     }

    /* List<String> cricket = new ArrayList<String>();
     cricket.add("Rangoli");
     cricket.add("Jewellery Box");
     cricket.add("Dish");
     cricket.add("Starter Plate");

     List<String> football = new ArrayList<String>();
     football.add("Ruby");
     football.add("Coral");
     football.add("Pearl");
     football.add("Emerald");
     football.add("Sapphire & Lapis Lazuli");

     List<String> basketball = new ArrayList<String>();
     basketball.add("Diffuser/Lamps");
     basketball.add("Furnishings");
     basketball.add("Decor");
     basketball.add("Kitchen");

     List<String> account = new ArrayList<String>();
     account.add("My Cart");
     account.add("My Wishlist");
     account.add("My Orders");
     account.add("My Account Setting");


     expandableListDetail.put("Pottery Products", cricket);
     expandableListDetail.put("Gemstones", football);
     expandableListDetail.put("Our Trendy Collection", basketball);
     expandableListDetail.put("My Account", account);*/
    }

    return expandableListDetail;
   }
}
