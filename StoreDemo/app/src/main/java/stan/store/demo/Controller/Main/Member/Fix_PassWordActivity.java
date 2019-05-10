package stan.store.demo.Controller.Main.Member;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import stan.store.demo.GCMD.GCMD;
import stan.store.demo.R;

public class Fix_PassWordActivity extends AppCompatActivity implements View.OnClickListener{

    //Model
    private GCMD mGCMD_LIB = new GCMD();
    private ImageView mMember_Fix_Pas_Back_Arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGCMD_LIB.SetActionBar(this);
        setContentView(R.layout.activity_member_fix_pas);
        mGCMD_LIB.SetActionBar_Status(this);
        InitSetting();

    }

    //---------------------------------------------------------------------------
    public void InitSetting () {
        Log.d(mGCMD_LIB.TAG,this.getClass().getSimpleName()+"InitSetting");
        mMember_Fix_Pas_Back_Arrow = (ImageView)findViewById(R.id.Member_Fix_Pas_Back_Arrow);

        mMember_Fix_Pas_Back_Arrow.setOnClickListener(this);
    }

    //---------------------------------------------------------------------------

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.Member_Fix_Pas_Back_Arrow:
                Log.d(mGCMD_LIB.TAG,"Member_Fix_Pas_Back_Arrow");
                ConfirmReturn();
                break;
            default:
                break;
        }
    }

    //---------------------------------------------------------------------------
    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回鍵
        Log.d(mGCMD_LIB.TAG,"onKeyDown");
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ConfirmReturn();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void ConfirmReturn (){
        finish();
    }
}
