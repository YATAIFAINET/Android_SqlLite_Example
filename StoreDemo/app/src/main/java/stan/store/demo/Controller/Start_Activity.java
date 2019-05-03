package stan.store.demo.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import stan.store.demo.GCMD.GCMD;
import stan.store.demo.R;

public class Start_Activity extends AppCompatActivity {

    private GCMD mGCMD_LIB = new GCMD();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(mGCMD_LIB.TAG,this.getClass().getSimpleName()+"：onCreate");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_start);
        InitSetting();

    }

    //------------------------------------------------------------

    private  void InitSetting () {
        Log.d(mGCMD_LIB.TAG,this.getClass().getSimpleName()+"：InitSetting");
        Transition_Activity();
    }
    //------------------------------------------------------------
    private void Transition_Activity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Start_Activity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        }, mGCMD_LIB.SPLASH_TIME_OUT);
    }

}

