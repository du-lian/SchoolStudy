package com.example.mytext;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_item_one;
    private TextView tv_item_two;
    private ViewPager myViewPager;
    private List<Fragment> list;
    private TabFragmentPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        // 设置菜单栏的点击事件
        tv_item_one.setOnClickListener(this);
        tv_item_two.setOnClickListener(this);
        myViewPager.addOnPageChangeListener(new MyPagerChangeListener() );
        //把Fragment添加到List集合里面
        list = new ArrayList<>();
        list.add(new OneFragement());
        list.add(new TwoFragment());
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(),list);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);
        tv_item_one.setBackgroundColor(Color.RED);

    }

    private void initView() {
        tv_item_one = findViewById(R.id.tv_local_music);
        tv_item_two = findViewById(R.id.tv_online_music);
        myViewPager = findViewById(R.id.myViewPager);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_local_music:
                    myViewPager.setCurrentItem(0);
                    tv_item_one.setBackgroundColor(Color.RED);
                    tv_item_two.setBackgroundColor(Color.WHITE);
                    break;
                case R.id.tv_online_music:
                    myViewPager.setCurrentItem(1);
                    tv_item_one.setBackgroundColor(Color.WHITE);
                    tv_item_two.setBackgroundColor(Color.RED);
                    break;

            }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    tv_item_one.setBackgroundColor(Color.RED);
                    tv_item_two.setBackgroundColor(Color.WHITE);

                    break;
                case 1:
                    tv_item_one.setBackgroundColor(Color.WHITE);
                    tv_item_two.setBackgroundColor(Color.RED);

                    break;
                case 2:
                    tv_item_one.setBackgroundColor(Color.WHITE);
                    tv_item_two.setBackgroundColor(Color.WHITE);

                    break;
            }
        }
    }

}
