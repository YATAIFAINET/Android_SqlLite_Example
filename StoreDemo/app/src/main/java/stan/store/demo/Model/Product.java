package stan.store.demo.Model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import stan.store.demo.Controller.IndexActivity;
import stan.store.demo.GCMD.GCMD;
import stan.store.demo.Helper.SQLiteHelper;

public class Product {
    // 定義 ColumnName
    final String ID = "id";
    final String NAME = "name";
    final String PRICE = "price";

    //Product的資料
    public ArrayList<HashMap<String,String>> mArrayList_ProductData = new ArrayList<HashMap<String, String>>();
    private HashMap<String,String> mHashMap_Data;

    public Product () {
        mArrayList_ProductData.clear();
    }

    public void Set_Product (HashMap<String,String> tmpMap){
        mHashMap_Data = tmpMap;
    }

    public void Check_Product_Name (Activity tmp) {
        mArrayList_ProductData = new SQLiteHelper(tmp).getAll(new GCMD().mTable_Type.Product);

        for (int i = 0 ; i<mArrayList_ProductData.size();i++){
            Log.d(GCMD.TAG,mArrayList_ProductData.get(i).get(ID));
            Log.d(GCMD.TAG,mArrayList_ProductData.get(i).get(NAME));
            Log.d(GCMD.TAG,mArrayList_ProductData.get(i).get(PRICE));
        }
    }

    public String getID(){
        return mHashMap_Data.get(ID);
    }

    public String getName(){
        return mHashMap_Data.get(NAME);
    }

    public String getPrice(){
        return mHashMap_Data.get(PRICE);
    }

}
