package com.example.qixinandroid;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private  BottomNavigationView bottomNavigationView;
    private Fragment work_fragment;
    private Fragment message_fragment;
    private Fragment my_fragment;
    private Fragment[] fragments;
    private int lastfragment;//用于记录上个选择的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
    }

    private void initFragment() {
        work_fragment = new Work_Fragment();
        message_fragment = new Message_Fragment();
        my_fragment = new My_Fragment();
        fragments = new Fragment[]{message_fragment, work_fragment, my_fragment};
        lastfragment = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment, message_fragment).show(message_fragment).commit();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.action_message: {
                    if (lastfragment != 0) {
                        switchFragment(lastfragment, 0);
                        lastfragment = 0;
                    }
                    return true;
                }
                case R.id.action_work: {
                    if (lastfragment != 1) {
                        switchFragment(lastfragment, 1);
                        lastfragment = 1;

                    }

                    return true;
                }
                case R.id.action_my: {
                    if (lastfragment != 2) {
                        switchFragment(lastfragment, 2);
                        lastfragment = 2;
                    }
                    return true;
                }
            }
            return false;
        }
    };


    //切换Fragment
    private void switchFragment(int lastfragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if (fragments[index].isAdded() == false) {
            transaction.add(R.id.frame_fragment, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();

    }



 /*   public boolean onKeyUp(int keyCode, KeyEvent event) {
        //在主界面，连击连两下退出程序
        if(keyCode==KeyEvent.KEYCODE_BACK){
            long secondTime=System.currentTimeMillis();
            if(secondTime-firstTime>800){
                Toast.makeText(Home_Activity.this,"再按一次返回键退出",Toast.LENGTH_SHORT).show();
                firstTime=secondTime;
                return true;
            }else{
                WelcomeActivity.activity.finish();
                System.exit(0);
            }
        }
        return super.onKeyUp(keyCode, event);
    }*/
}
