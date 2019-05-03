package stan.store.demo.Controller.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

import stan.store.demo.GCMD.GCMD;
import stan.store.demo.Helper.SQLiteHelper;
import stan.store.demo.Model.User;
import stan.store.demo.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private GCMD mGCMD_LIB = new GCMD();

    //UI
    private ImageView mRegister_Back_Arrow;
    private EditText mRegister_EditText_Account,mRegister_EditText_Password,mRegister_EditText_Name, mRegister_EditText_Phone,mRegister_EditText_Email;
    private Button mRegister_Button_Register;

    //DB
    private SQLiteHelper mDBHelper;
    private User mUser = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGCMD_LIB.SetActionBar(this);
        setContentView(R.layout.activity_main);
        mGCMD_LIB.SetActionBar_Status(this);
        InitSetting();

    }

    public void InitSetting () {
        Log.d(mGCMD_LIB.TAG,"InitSetting");
        mDBHelper = new SQLiteHelper(RegisterActivity.this);
        mUser.Check_Data(mDBHelper);

        //Step1 賦予元件id
        mRegister_Back_Arrow = (ImageView)findViewById(R.id.Register_Back_Arrow);

        mRegister_EditText_Account = (EditText) findViewById(R.id.Register_EditText_Account);
        mRegister_EditText_Password = (EditText) findViewById(R.id.Register_EditText_Password);
        mRegister_EditText_Name = (EditText) findViewById(R.id.Register_EditText_Name);
        mRegister_EditText_Phone = (EditText) findViewById(R.id.Register_EditText_Phone);
        mRegister_EditText_Email = (EditText) findViewById(R.id.Register_EditText_Email);

        mRegister_Button_Register = (Button) findViewById(R.id.Register_Button_Save);
        mRegister_Button_Register.setOnClickListener(this);
        mRegister_Back_Arrow.setOnClickListener(this);
    }

    public void Save_Data () {
        Log.d(mGCMD_LIB.TAG,"Save_Data");

        String account = mRegister_EditText_Account.getText().toString();
        String password = mRegister_EditText_Password.getText().toString();
        String name = mRegister_EditText_Name.getText().toString();
        String phone = mRegister_EditText_Phone.getText().toString();
        String email = mRegister_EditText_Email.getText().toString();

        String Error_Msg = "";

        if(account.trim().equals("")){
            Error_Msg = Error_Msg+"帳號 ";
        }
        if(password.trim().equals("")){
            Error_Msg = Error_Msg+"密碼 ";
        }
        if(name.trim().equals("")){
            Error_Msg = Error_Msg+"姓名 ";
        }
        if(phone.trim().equals("")){
            Error_Msg = Error_Msg+"電話 ";
        }
        if(email.trim().equals("")){
            Error_Msg = Error_Msg+"email ";
        }

        if(!Error_Msg.equals("")){
            Error_Msg = Error_Msg+"未輸入 請確認";
            Toast.makeText(RegisterActivity.this, Error_Msg, Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String,String> tmpMap = new HashMap<String,String>();
            tmpMap.put("account",account);
            tmpMap.put("password",password);
            tmpMap.put("name",name);
            tmpMap.put("phone",phone);
            tmpMap.put("email",email);
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
            case R.id.Register_Back_Arrow:
                Set_Back_To_Login();
                break;
            case R.id.Register_Button_Save:
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
