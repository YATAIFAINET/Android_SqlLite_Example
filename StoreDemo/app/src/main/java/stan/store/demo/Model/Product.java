package stan.store.demo.Model;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import stan.store.demo.GCMD.GCMD;
import stan.store.demo.Helper.SQLiteHelper;

public class Product {
    // 定義 ColumnName
    final String ID = "id";
    final String NAME = "name";
    final String PRICE = "price";
    final String Explain = "expl";

    //Product的資料
    public ArrayList<HashMap<String,String>> mArrayList_ProductData = new ArrayList<HashMap<String, String>>();

    public void Check_Product_Name (Activity tmp) {
        mArrayList_ProductData = new SQLiteHelper(tmp).getAll(new GCMD().mTable_Type.Product);

        for (int i = 0 ; i<mArrayList_ProductData.size();i++){
            Log.d(GCMD.TAG,mArrayList_ProductData.get(i).get(ID));
            Log.d(GCMD.TAG,mArrayList_ProductData.get(i).get(NAME));
            Log.d(GCMD.TAG,mArrayList_ProductData.get(i).get(PRICE));
        }
    }

    public String getID(int position){
        return mArrayList_ProductData.get(position).get(ID);
    }

    public String getName(int position){
        return mArrayList_ProductData.get(position).get(NAME);
    }

    public String getPrice(int position){
        return mArrayList_ProductData.get(position).get(PRICE);
    }
    public String getExplain(int position){
        return mArrayList_ProductData.get(position).get(Explain);
    }

}
