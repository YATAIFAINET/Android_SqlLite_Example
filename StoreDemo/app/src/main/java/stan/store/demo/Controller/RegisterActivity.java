package stan.store.demo.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import java.util.HashMap;

import stan.store.demo.GCMD.GCMD;
import stan.store.demo.Helper.SQLiteHelper;
import stan.store.demo.Model.User;
import stan.store.demo.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private GCMD mGCMD_LIB = new GCMD();

    //UI

    private EditText mRegister_EditText_Password,mRegister_EditText_Name, mRegister_EditText_Phone;
    private Button mRegister_Button_Register;

    //DB
    private SQLiteHelper mDBHelper;
    private User mUser = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitSetting();

    }

    public void InitSetting () {
        Log.d(mGCMD_LIB.TAG,"InitSetting");
        mDBHelper = new SQLiteHelper(RegisterActivity.this);
        mUser.Check_Data(mDBHelper);

        mRegister_EditText_Password = (EditText) findViewById(R.id.Text_Password);
        mRegister_EditText_Name = (EditText) findViewById(R.id.Text_Name);
        mRegister_EditText_Phone = (EditText) findViewById(R.id.Text_Phone);


        mRegister_Button_Register = (Button) findViewById(R.id.Register_Btn);
        mRegister_Button_Register.setOnClickListener(this);
    }

    public void Save_Data () {
        Log.d(mGCMD_LIB.TAG,"Save_Data");


        String password = mRegister_EditText_Password.getText().toString();
        String name = mRegister_EditText_Name.getText().toString();
        String phone = mRegister_EditText_Phone.getText().toString();


        String Error_Msg = "";


        if(password.trim().equals("")){
            Error_Msg = Error_Msg+"密碼 ";
        }
        if(name.trim().equals("")){
            Error_Msg = Error_Msg+"姓名 ";
        }
        if(phone.trim().equals("")){
            Error_Msg = Error_Msg+"電話 ";
        }


        if(!Error_Msg.equals("")){
            Error_Msg = Error_Msg+"未輸入 請確認";
            Toast.makeText(RegisterActivity.this, Error_Msg, Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String,String> tmpMap = new HashMap<String,String>();
            tmpMap.put("account",phone);
            tmpMap.put("password",password);
            tmpMap.put("name",name);
            tmpMap.put("phone",phone);
            tmpMap.put("email","");
            if (mDBHelper.addData("user", tmpMap)) {
                Toast.makeText(RegisterActivity.this, "註冊成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "註冊失敗", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_Register:
                Save_Data();
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
