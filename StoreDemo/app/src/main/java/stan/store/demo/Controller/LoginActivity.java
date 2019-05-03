package stan.store.demo.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import stan.store.demo.GCMD.GCMD;
import stan.store.demo.Helper.SQLiteHelper;
import stan.store.demo.Model.Product;
import stan.store.demo.Model.User;
import stan.store.demo.R;

public class LoginActivity extends AppCompatActivity  {

    private GCMD mGCMD_LIB = new GCMD();

    //UI
    private EditText mEditText_Account;
    private EditText mEditText_Password;
    private Button mButton_Login;
    private Button mButton_Regitster;

    //DataBase
    private SQLiteHelper mDBHelper ;
    private User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitSetting();
    }

    private void InitSetting () {
        Log.d(mGCMD_LIB.TAG,"InitSetting");
        mUser.Set_User_Data_Clear();
        mDBHelper = new SQLiteHelper(LoginActivity.this);

        //InitUI
        mEditText_Account = (EditText) findViewById(R.id.editText_Account);
        mEditText_Password = (EditText) findViewById(R.id.editText_Password);
        mButton_Login = (Button) findViewById(R.id.button_Login);
        mButton_Regitster = (Button) findViewById(R.id.button_Register);

        Add_Product_Init();

        //Click Event
        mButton_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mEditText_Account.getText().toString();
                String password = mEditText_Password.getText().toString();

                //Step1 檢查資料完整性
                if (account.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this,"請輸入登入資訊", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Step2 驗證資料正確性
                String Check_SQL = "SELECT * FROM "+mGCMD_LIB.mTable_Type.User+ " WHERE account = '" + account + "' AND password = '" + password +"'";
                if (mDBHelper.QuerySQL(Check_SQL).size() > 0) {
                    mUser.Set_User_Data(mDBHelper.QuerySQL(Check_SQL).get(0));
                    Toast.makeText(LoginActivity.this,mUser.getName() + " 您好，登入成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, IndexActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,"帳號密碼有誤", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButton_Regitster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Add_Product_Init () {
        Log.d(mGCMD_LIB.TAG,"Add_Product_Init");

        if(mDBHelper.getAll(mGCMD_LIB.mTable_Type.Product).size()>0){
            Toast.makeText(LoginActivity.this,"資料庫已新增過產品", Toast.LENGTH_SHORT).show();
            new Product().Check_Product_Name(this);
        } else {
            for (int i = 1 ; i <= 10 ; i++) {
                HashMap<String,String> tmpMap = new HashMap<String,String>();
                tmpMap.put("name", "產品名稱" + i);
                tmpMap.put("price", String.valueOf(100 * i));
                mDBHelper.addData(mGCMD_LIB.mTable_Type.Product, tmpMap);
            }
            Toast.makeText(LoginActivity.this,"新增成功", Toast.LENGTH_SHORT).show();
        }
    }
}
