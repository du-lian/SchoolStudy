package activity;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lee.test.R;

import adapter.MyAdapter;
import fragment.LocalFragment;
import fragment.OnlineFragment;

import fragment.TabFragmentPagerAdapter;
import model.Music;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;



//实现OnClickListener的接口
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //定义activity_main.xml的控件对象

    private ViewPager viewPager;//用来放置界面切换

    //两个按钮
    private TextView logicTv;
    private TextView onlineTv;

    //两个图片视图
    private ImageView menuImagv;
    private ImageView seachImagv;

    private TabFragmentPagerAdapter adapter;
    private ListView myLocalList;

   private List<Music> musicLocalList;
    private List<Fragment> list;
    private FrameLayout play_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();
        InitsetOnclick();
        addFragment();

        /*自动查询本地文件是否有音乐文件*/
        musicLocalList = new ArrayList<>();
        musicLocalList = Utils.getmusic(MainActivity.this);
        MyAdapter myAdapter = new MyAdapter(this,musicLocalList);
        //myLocalList.setAdapter(myAdapter);

}




    /**
     * 把Fragment添加到List集合里面
     */
    private void addFragment() {
        /*分页管理*/
        list = new ArrayList();
        list.add(new LocalFragment());
        list.add(new OnlineFragment());
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);//初始化第一个页面









       /* myLocalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String p = musicLocalList.get(position).path;
                play(p);
            }
        });*/

    }

    /**
     * 设置菜单栏的点击事件
     */
    private void InitsetOnclick() {
        logicTv.setOnClickListener(this);
        onlineTv.setOnClickListener(this);
        seachImagv.setOnClickListener(this);
        menuImagv.setOnClickListener(this);
        play_bar.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new MyPagerChangeListener());

    }

    /**
     * 初始化控件
     */
    private void InitView() {
        logicTv = findViewById(R.id.main_logic_tv);
        onlineTv = findViewById(R.id.main_online_tv);
        menuImagv = findViewById(R.id.main_menu_imgv);
        seachImagv = findViewById(R.id.main_search_imgv);
        viewPager = findViewById(R.id.main_vp);
        myLocalList = findViewById(R.id.main_listview);
        play_bar = findViewById(R.id.fl_play_bar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_logic_tv:
                viewPager.setCurrentItem(0);
                break;
            case  R.id.main_online_tv:
                viewPager.setCurrentItem(1);
                break;
            case R.id.main_search_imgv:
                setContentView(R.layout.searchview);
            case R.id.fl_play_bar:
                //setContentView(R.layout.include_play_page_controller);
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class MyPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            switch (i) {
                case 0:
                    logicTv.setTextColor(Color.WHITE);
                    onlineTv.setTextColor(Color.GRAY);
                    break;
                case 1:
                    logicTv.setTextColor(Color.GRAY);
                    onlineTv.setTextColor(Color.WHITE);
                    break;

            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }
}

