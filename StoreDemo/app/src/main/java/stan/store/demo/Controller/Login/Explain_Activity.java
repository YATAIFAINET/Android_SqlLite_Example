package stan.store.demo.Controller.Login;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import stan.store.demo.GCMD.GCMD;
import stan.store.demo.R;

public class Explain_Activity extends AppCompatActivity implements View.OnClickListener {

    private GCMD mGCMD_LIB = new GCMD();
    private ImageView mExplain_Back_Arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(mGCMD_LIB.TAG,this.getClass().getSimpleName()+"：onCreate");
        super.onCreate(savedInstanceState);

        mGCMD_LIB.SetActionBar(this);
        setContentView(R.layout.activity_explain);
        mGCMD_LIB.SetActionBar_Status(this);

        InitSetting();

    }

    //------------------------------------------------------------

    private  void InitSetting () {
        Log.d(mGCMD_LIB.TAG,this.getClass().getSimpleName()+"：InitSetting");
        mExplain_Back_Arrow = (ImageView)findViewById(R.id.Explain_Back_Arrow);
        mExplain_Back_Arrow.setOnClickListener(this);

    }
    //------------------------------------------------------------


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Explain_Back_Arrow:
                Set_Back_To_Login();
                break;
            default:
                break;
        }
    }

    public void Set_Back_To_Login () {
        Log.d(mGCMD_LIB.TAG,"Set_Back_To_Login");
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回鍵
        Log.d(mGCMD_LIB.TAG,"onKeyDown");
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Set_Back_To_Login();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

