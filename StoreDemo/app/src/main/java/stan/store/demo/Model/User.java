package stan.store.demo.Model;

import android.util.Log;

import java.util.HashMap;

import stan.store.demo.GCMD.GCMD;
import stan.store.demo.Helper.SQLiteHelper;

public class User {
    // 定義 ColumnName
    final String ID = "id";
    final String Account = "account";
    final String PASSWORD = "password";
    final String NAME = "name";
    final String PHONE = "phone";
    final String EMAIL = "email";
    final String ADDRESS = "address";
    private GCMD mGCMD_LIB = new GCMD();

    //User的資料
    public static HashMap<String,String> mHashMap_Data = new HashMap<String, String>();

    public void Set_User_Data (HashMap<String,String> tmpMap) {
        mHashMap_Data = tmpMap;
    }

    public void  Set_User_Data_Clear () {
        mHashMap_Data.clear();
    }

    public String getID(){
        return mHashMap_Data.get(ID);
    }

    public String getAccount(){
        return mHashMap_Data.get(Account);
    }

    public String getPassword(){
        return mHashMap_Data.get(PASSWORD);
    }

    public String getName(){
        return mHashMap_Data.get(NAME);
    }
    public String getPhone(){
        return mHashMap_Data.get(PHONE);
    }
    public String getEmail(){
        return mHashMap_Data.get(EMAIL);
    }
    public String getAddress(){
        return mHashMap_Data.get(ADDRESS);
    }

    public void Check_Data (SQLiteHelper Source){
        // getAll Method
        Log.d(mGCMD_LIB.TAG,"Check_Data");
        if(Source.getAll(mGCMD_LIB.mTable_Type.User).size()>0){
            for (HashMap<String,String> mapData : Source.getAll(mGCMD_LIB.mTable_Type.User)) {
                for (String key : mapData.keySet()) {
                    Log.e(GCMD.TAG, "onCreate: " + key + "->" + mapData.get(key));
                }
            }
        }


        /*
        // QuerySQL Method
        String SQL = "SELECT * FROM user WHERE id = 2";
        for (HashMap<String,String> mapData : Source.QuerySQL(SQL)) {
            for (String key : mapData.keySet()) {
                Log.e(GCMD.TAG, "onCreate: " + key + "->" + mapData.get(key));
            }
        }*/
    }
}
