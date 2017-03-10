package com.example.slidingmenu_02.activity;

import android.app.Activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.slidingmenu_02.R;
import com.example.slidingmenu_02.fragment.LeftFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
//修改之后的代码
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class MainActivity extends FragmentActivity {
        private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_test_net);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, ""+"aaaaa", Toast.LENGTH_SHORT).show();
                getDataFromNet();
            }
        });
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        // 设置滑动菜单视图的宽度
        WindowManager wn = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wn.getDefaultDisplay().getWidth()/2;
        menu.setBehindOffset(width);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.leftmenu_fragment_layout);
        initFragment();
    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_menu,new LeftFragment()).commit();

    }

    public void getDataFromNet() {
        String url = new String("http://mock.eolinker.com/success/rq7m6GNqurs93zYkEANkY8Z4358Aihf1");
        RequestParams params = new RequestParams(url);


            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    Toast.makeText(MainActivity.this, "baiduwangzhi"+result, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(MainActivity.this,"请求失败"+ex.getMessage().toString(),Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {
                    Toast.makeText(MainActivity.this, "meiyou数据啊", Toast.LENGTH_SHORT).show();
                }
            });
    }
}
