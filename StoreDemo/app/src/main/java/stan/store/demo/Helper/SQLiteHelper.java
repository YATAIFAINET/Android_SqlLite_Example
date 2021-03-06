package stan.store.demo.Helper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import stan.store.demo.GCMD.GCMD;

public class SQLiteHelper {
    final String db_name="store_demo";    // 資料庫名稱
    private Activity mActivity;
    private SQLiteDatabase db;    //資料庫
    private GCMD mGCMD_LIB = new GCMD();

    public SQLiteHelper(Activity ac) {
        mActivity = ac;
        db = mActivity.openOrCreateDatabase(db_name,  Context.MODE_PRIVATE, null);

        //Create User's Table
        String SQL_UserTable =
                "CREATE TABLE IF NOT EXISTS " +
                 mGCMD_LIB.mTable_Type.User +            // 資料表名稱
                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +    //主鍵(唯一識別)
                "account VARCHAR(50), " +
                "password VARCHAR(50), "+
                "name VARCHAR(50), " +    //姓名欄位
                "phone VARCHAR(50), " +    //電話欄位
                "email VARCHAR(50)) ";    //密碼欄位
        db.execSQL(SQL_UserTable);    // 建立資料表


        //Create Product's Table
        String SQL_Product_Table =
                "CREATE TABLE IF NOT EXISTS " +
                        mGCMD_LIB.mTable_Type.Product +            // 資料表名稱
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +    //主鍵(唯一識別)
                        "name VARCHAR(50), " +    //產品名稱
                        "price VARCHAR(50))";    //產品價格
        db.execSQL(SQL_Product_Table);    // 建立資料表

    }

    public ArrayList<HashMap<String,String>> getAll(String TableName) {
        //宣告陣列Map
        ArrayList<HashMap<String,String>> mArrayData = new ArrayList<HashMap<String,String>>();

        //取得CursorData
        Cursor CursorData = db.rawQuery("SELECT * FROM " + TableName, null);

        //取得Cursor的所有Key
        String[] columnNames = CursorData.getColumnNames();

        //解析出來放入陣列
        if (CursorData != null && CursorData.getCount()>0){
            CursorData.moveToFirst();
            do{
                HashMap<String,String> tmpMap = new HashMap<String,String>();
                for(int i = 0; i < columnNames.length; i++){
                    tmpMap.put(columnNames[i], CursorData.getString(i));
                }
                mArrayData.add(tmpMap);
            }while(CursorData.moveToNext());
        }

        CursorData.close();

        return mArrayData;
    }

    public ArrayList<HashMap<String,String>> QuerySQL (String SQL) {
        //宣告陣列Map
        ArrayList<HashMap<String,String>> mArrayData = new ArrayList<HashMap<String,String>>();

        //取得CursorData
        Cursor CursorData = db.rawQuery(SQL, null);

        //取得Cursor的所有Key
        String[] columnNames = CursorData.getColumnNames();

        //解析出來放入陣列
        if (CursorData != null && CursorData.getCount()>0){
            CursorData.moveToFirst();
            do{
                HashMap<String,String> tmpMap = new HashMap<String,String>();
                for(int i = 0; i < columnNames.length; i++){
                    tmpMap.put(columnNames[i], CursorData.getString(i));
                }
                mArrayData.add(tmpMap);
            }while(CursorData.moveToNext());
        }

        CursorData.close();

        return mArrayData;
    }

    public boolean addData (String TableName, HashMap<String,String> DataMap) {
        ContentValues ContentVal = new ContentValues(DataMap.size());
        for (String key : DataMap.keySet()) {
            ContentVal.put(key, DataMap.get(key));
        }
        long tmp  = db.insert(TableName, null, ContentVal);

        if(tmp != -1){
            return true;
        } else {
            return false;
        }
    }

    public boolean update (String TableName, HashMap<String,String> DataMap, String WhereID) {
        ContentValues ContentVal = new ContentValues();
        for (String key : DataMap.keySet()) {
            ContentVal.put(key, DataMap.get(key));
        }

        db.update(TableName, ContentVal, "_ID = " + WhereID, null);
        return true;
    }

    public boolean delete (String TableName, String WhereID) {
        db.delete(TableName, "_ID = " + WhereID, null);
        return true;
    }


}
