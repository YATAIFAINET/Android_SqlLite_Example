package stan.store.demo.Controller.Main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import stan.store.demo.Controller.Login.Explain_Activity;
import stan.store.demo.Controller.Login.LoginActivity;
import stan.store.demo.Controller.Main.Member.Fix_DataActivity;
import stan.store.demo.Controller.Main.Member.Fix_PassWordActivity;
import stan.store.demo.GCMD.GCMD;
import stan.store.demo.Model.User;
import stan.store.demo.R;

public class MemberActivity extends AppCompatActivity implements View.OnClickListener{
    //UI
    private LinearLayout mMain_Product_Index_Lin;
    private LinearLayout mMain_Cart_Index_Lin;
    private LinearLayout mMain_Member_Index_Lin;
    private LinearLayout mMember_Fix_Pas_Lin;
    private LinearLayout mMember_Fix_Data_Lin;
    private LinearLayout mMember_Explain_Lin;
    private LinearLayout mMember_Version_Lin;
    private LinearLayout mMember_Make_Name_Lin;
    private Button mMember_Logut_Lin;

    private TextView mMember_Name;
    private TextView mMember_Account;

    //Model
    private GCMD mGCMD_LIB = new GCMD();
    private User mUser = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGCMD_LIB.SetActionBar(this);
        setContentView(R.layout.activity_member);
        mGCMD_LIB.SetActionBar_Status(this);
        InitSetting();

    }

    //---------------------------------------------------------------------------
    public void InitSetting () {
        Log.d(mGCMD_LIB.TAG,this.getClass().getSimpleName()+"InitSetting");

        mMain_Product_Index_Lin = (LinearLayout)findViewById(R.id.Main_Product_Index_Lin);
        mMain_Cart_Index_Lin = (LinearLayout) findViewById(R.id.Main_Cart_Index_Lin);
        mMain_Member_Index_Lin = (LinearLayout) findViewById(R.id.Main_Member_Index_Lin);
        mMember_Name = (TextView)findViewById(R.id.Member_Name);
        mMember_Account = (TextView)findViewById(R.id.Member_Account);
        mMember_Fix_Pas_Lin = (LinearLayout)findViewById(R.id.Member_Fix_Pas_Lin);
        mMember_Fix_Data_Lin = (LinearLayout)findViewById(R.id.Member_Fix_Data_Lin);
        mMember_Explain_Lin = (LinearLayout)findViewById(R.id.Member_Explain_Lin);
        mMember_Version_Lin = (LinearLayout)findViewById(R.id.Member_Version_Lin);
        mMember_Make_Name_Lin = (LinearLayout)findViewById(R.id.Member_Make_Name_Lin);
        mMember_Logut_Lin = (Button)findViewById(R.id.Member_Logut_Lin);

        mMain_Product_Index_Lin.setOnClickListener(this);
        mMain_Cart_Index_Lin.setOnClickListener(this);
        mMain_Member_Index_Lin.setOnClickListener(this);
        mMember_Fix_Pas_Lin.setOnClickListener(this);
        mMember_Fix_Data_Lin.setOnClickListener(this);
        mMember_Explain_Lin.setOnClickListener(this);
        mMember_Version_Lin.setOnClickListener(this);
        mMember_Make_Name_Lin.setOnClickListener(this);
        mMember_Logut_Lin.setOnClickListener(this);

        Set_Layout_Data ();
    }

    private void Set_Layout_Data () {
        Log.d(mGCMD_LIB.TAG,"Set_Layout_Data");
        mMember_Name.setText(mUser.getName());
        mMember_Account.setText(mUser.getAccount());
    }

    //---------------------------------------------------------------------------

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.Main_Product_Index_Lin:
                Log.d(mGCMD_LIB.TAG,"Main_Product_Index_Lin");
                mGCMD_LIB.Get_Icon_Act(this,GCMD.Main_Type.product);
                break;
            case R.id.Main_Cart_Index_Lin:
                Log.d(mGCMD_LIB.TAG,"Main_Cart_Index_Lin");
                mGCMD_LIB.Get_Icon_Act(this,GCMD.Main_Type.cart);
                break;
            case R.id.Main_Member_Index_Lin:
                Log.d(mGCMD_LIB.TAG,"Main_Member_Index_Lin");
                //mGCMD_LIB.Get_Icon_Act(this,GCMD.Main_Type.member);
                break;
            case R.id.Member_Fix_Pas_Lin:
                Log.d(mGCMD_LIB.TAG,"Main_Member_Index_Lin");
                intent.setClass(MemberActivity.this, Fix_PassWordActivity.class);
                startActivity(intent);
                break;
            case R.id.Member_Fix_Data_Lin:
                Log.d(mGCMD_LIB.TAG,"Member_Fix_Data_Lin");
                intent.setClass(MemberActivity.this, Fix_DataActivity.class);
                startActivity(intent);
                break;
            case R.id.Member_Explain_Lin:
                Log.d(mGCMD_LIB.TAG,"Member_Explain_Lin");
                intent.setClass(MemberActivity.this, Explain_Activity.class);
                startActivity(intent);
                break;
            case R.id.Member_Version_Lin:
                Log.d(mGCMD_LIB.TAG,"Member_Version_Lin");
                Toast.makeText(getApplicationContext(),"版本為："+this.getString(R.string.Version),Toast.LENGTH_SHORT).show();
                break;
            case R.id.Member_Make_Name_Lin:
                Log.d(mGCMD_LIB.TAG,"Member_Make_Name_Lin");
                Toast.makeText(getApplicationContext(),"製作者為："+this.getString(R.string.Make_Name),Toast.LENGTH_SHORT).show();
                break;
            case R.id.Member_Logut_Lin:
                mUser.Set_User_Data_Clear();
                intent.setClass(MemberActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
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

