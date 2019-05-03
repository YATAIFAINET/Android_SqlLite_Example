package stan.store.demo.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import stan.store.demo.Adapter.Adapter_Product;
import stan.store.demo.GCMD.GCMD;
import stan.store.demo.Helper.SQLiteHelper;
import stan.store.demo.Model.Product;
import stan.store.demo.R;

public class IndexActivity extends AppCompatActivity {
    //UI
    private RecyclerView mRecyclerView_Product;

    //Adapter
    private Adapter_Product mAdapter_Product;

    //DataBase
    private SQLiteHelper mDBHelper ;

    //Model
    private GCMD mGCMD_LIB = new GCMD();
    private Product mProduct = new Product();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mDBHelper = new SQLiteHelper(IndexActivity.this);

        //元件定義
        mRecyclerView_Product = (RecyclerView) findViewById(R.id.RecyclerView_Product);

        //先拿到產品資料
        mProduct.mArrayList_ProductData = mDBHelper.getAll(mGCMD_LIB.mTable_Type.Product);

        //設定Adapter + RecyclerView
        mAdapter_Product = new Adapter_Product(IndexActivity.this,mProduct);
        mRecyclerView_Product.setLayoutManager(new LinearLayoutManager(IndexActivity.this));
        mRecyclerView_Product.setAdapter(mAdapter_Product);

    }
}
