package stan.store.demo.Controller.Main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import stan.store.demo.Adapter.Adapter_Product;
import stan.store.demo.GCMD.GCMD;
import stan.store.demo.Helper.SQLiteHelper;
import stan.store.demo.Model.Product;
import stan.store.demo.R;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener{
    //UI
    private RecyclerView mRecyclerView_Product;
    private LinearLayout mMain_Product_Index_Lin;
    private LinearLayout mMain_Cart_Index_Lin;
    private LinearLayout mMain_Member_Index_Lin;
    //Adapter
    private Adapter_Product mAdapter_Product;

    //DataBase
    private SQLiteHelper mDBHelper ;

    //Model
    private GCMD mGCMD_LIB = new GCMD();
    private Product mProduct = new Product();

    //layout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGCMD_LIB.SetActionBar(this);
        setContentView(R.layout.activity_index);
        mGCMD_LIB.SetActionBar_Status(this);
        InitSetting();

    }

    //---------------------------------------------------------------------------
    public void InitSetting () {
        Log.d(mGCMD_LIB.TAG,this.getClass().getSimpleName()+"InitSetting");

        mDBHelper = new SQLiteHelper(IndexActivity.this);
        //先拿到產品資料
        mProduct.mArrayList_ProductData = mDBHelper.getAll(mGCMD_LIB.mTable_Type.Product);

        //元件定義
        mRecyclerView_Product = (RecyclerView) findViewById(R.id.RecyclerView_Product);

        mMain_Product_Index_Lin = (LinearLayout)findViewById(R.id.Main_Product_Index_Lin);
        mMain_Cart_Index_Lin = (LinearLayout) findViewById(R.id.Main_Cart_Index_Lin);
        mMain_Member_Index_Lin = (LinearLayout) findViewById(R.id.Main_Member_Index_Lin);

        mMain_Product_Index_Lin.setOnClickListener(this);
        mMain_Cart_Index_Lin.setOnClickListener(this);
        mMain_Member_Index_Lin.setOnClickListener(this);

        Set_Adapter_Init ();

    }

    //---------------------------------------------------------------------------
    private void Set_Adapter_Init () {
        Log.d(mGCMD_LIB.TAG,"Set_Adapter_Init");
        //設定Adapter + RecyclerView
        mAdapter_Product = new Adapter_Product(IndexActivity.this,mProduct);
        mRecyclerView_Product.setLayoutManager(new LinearLayoutManager(IndexActivity.this));
        mRecyclerView_Product.setAdapter(mAdapter_Product);
    }

    //---------------------------------------------------------------------------

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Main_Product_Index_Lin:
                Log.d(mGCMD_LIB.TAG,"Main_Product_Index_Lin");
                //mGCMD_LIB.Get_Icon_Act(this,GCMD.Main_Type.product);
                break;
            case R.id.Main_Cart_Index_Lin:
                Log.d(mGCMD_LIB.TAG,"Main_Cart_Index_Lin");
                mGCMD_LIB.Get_Icon_Act(this,GCMD.Main_Type.cart);
                break;
            case R.id.Main_Member_Index_Lin:
                Log.d(mGCMD_LIB.TAG,"Main_Member_Index_Lin");
                mGCMD_LIB.Get_Icon_Act(this,GCMD.Main_Type.member);
                break;
        }
    }

    //---------------------------------------------------------------------------
    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回鍵
        Log.d(mGCMD_LIB.TAG,"onKeyDown");
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ConfirmExit(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void ConfirmExit (final Activity Source_Act){
        final AlertDialog.Builder ad=new AlertDialog.Builder(Source_Act);
        ad.setTitle("離開");
        ad.setMessage("確定要離開系統嗎?");
        ad.setCancelable(false);
        ad.setPositiveButton("是", new DialogInterface.OnClickListener() {//退出按鈕
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(DialogInterface dialog, int i) {
                // TODO Auto-generated method stub
                Source_Act.finishAffinity();
                System.exit(0);
            }
        });
        ad.setNegativeButton("否", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        ad.show();//示對話框
    }
}
