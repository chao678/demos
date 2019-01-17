package com.example.testapp.activity;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testapp.R;
import com.example.testapp.view.ui9.Xcircleindicator;

import java.util.ArrayList;
import java.util.List;

public class Ui9Activity extends BaseActivity {
    private Xcircleindicator mXcircleindicator;
    private ViewPager viewPager;
    private List<View> viewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui9);
        initViews();
        initData();
    }

    public void initViews() {
        viewPager = findViewById(R.id.viewPager);
        mXcircleindicator = findViewById(R.id.xcircleindicator);
    }

    public void initData() {
        viewList.add(LayoutInflater.from(this).inflate(R.layout.viewpaer_item_one, null));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.viewpaer_item_two, null));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.viewpaer_item_three, null));
        viewPager.setAdapter(new ViewPagerAdapter());
        //设置总共的页数
        mXcircleindicator.initData(viewList.size(), 0);
        //设置当前的页面
        mXcircleindicator.setCurrentPage(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mXcircleindicator.setCurrentPage(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class ViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            ((ViewGroup)container).removeView(viewList.get(position));//删除页卡
        }
        //这个方法用来实例化页卡
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);
            ((ViewGroup) container).addView(viewList.get(position), 0);//添加页卡
            return viewList.get(position);
        }
    }
}
