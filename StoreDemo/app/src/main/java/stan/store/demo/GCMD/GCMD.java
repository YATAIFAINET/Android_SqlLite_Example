package stan.store.demo.GCMD;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import stan.store.demo.Controller.Main.CartActivity;
import stan.store.demo.Controller.Main.IndexActivity;
import stan.store.demo.Controller.Main.MemberActivity;
import stan.store.demo.R;

public class GCMD {

    public final static String TAG = "Demo_Sql";
    public final int SPLASH_TIME_OUT = 1500;

    public Table_Name mTable_Type = new Table_Name();

    public class Table_Name {
        public final String Product = "product";
        public final String User = "user";
    }


    public enum Main_Type {
        product,cart,member
    }

    ///--Check action to activity
    public void Get_Icon_Act (Activity Source_Act,Main_Type Type){
        Intent intent = new Intent();
        switch (Type){
            case product:
                intent.setClass(Source_Act, IndexActivity.class);
                break;
            case cart:
                intent.setClass(Source_Act, CartActivity.class);
                break;
            case member:
                intent.setClass(Source_Act, MemberActivity.class);
                break;
            default:
                break;
        }
        Source_Act.startActivity(intent);
        Source_Act.finish();
    }


    ////------Statusbar

    public void SetActionBar(Activity Acttmp){
        Acttmp.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = Acttmp.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Acttmp.getResources().getColor(R.color.Status_Bar));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
                View statusBarView = new View(window.getContext());
                int statusBarHeight = getStatusBarHeight(window.getContext());
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
                params.gravity = Gravity.TOP;
                statusBarView.setLayoutParams(params);
                statusBarView.setBackgroundColor(Acttmp.getResources().getColor(R.color.Status_Bar));
                decorViewGroup.addView(statusBarView);

            }
        }
    }

    //-----------------------------------------------------------------------------------
    public void SetActionBar_Status(Activity Acttmp){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            Window window = Acttmp.getWindow();
            ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
                mChildView.setFitsSystemWindows(true);
            }
        }
    }
    //-----------------------------------------------------------------------------------
    private static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
