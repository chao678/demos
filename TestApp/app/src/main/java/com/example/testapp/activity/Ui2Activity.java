package com.example.testapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.example.testapp.GridSpacingItemDecoration;
import com.example.testapp.R;
import com.example.testapp.WaterfallItemObj;
import com.example.testapp.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ui2Activity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private List<WaterfallItemObj> list;
    private Button but_update;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui2);

        setData();
        recycler_view = findViewById(R.id.recycler_view);
        //GridLayoutManager mgr = new GridLayoutManager(this, 4);//网格布局管理器
        StaggeredGridLayoutManager mgr = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);//瀑布流布局管理器
//        LinearLayoutManager mgr = new LinearLayoutManager(this);//线性布局管理器
//        mgr.setOrientation(LinearLayoutManager.HORIZONTAL);//设置 recyclerview 布局方式为横向布局
        recycler_view.setLayoutManager(mgr);
        recyclerViewAdapter = new RecyclerViewAdapter(this, list);
        int spanCount = 4;//跟布局里面的spanCount属性是一致的
        int spacing = 5;//每一个矩形的间距
        boolean includeEdge = true;//如果设置成false那边缘地带就没有间距s
//        //设置每个item间距
        recycler_view.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        recycler_view.setAdapter(recyclerViewAdapter);

        initViews();
    }

    private void initViews() {
        but_update = findViewById(R.id.but_update);

        but_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    private List setData() {
        if (list == null) {
            list = new ArrayList<>();
        }
        for (int i=0; i<60; i++) {
            list.add(new WaterfallItemObj(getRandomColorStr(), getRandomColorStr(), getRandomHeight()));
        }
        return list;
    }

    /*
     * 生成随机的颜色值
     * */
    protected String getRandomColorStr(){
        String colorStr="";
        StringBuilder tempStr=new StringBuilder();
        String[] c= {"a","b","c","d","e","f","0","1","3","4","5","6","7","8","9"};

        Random random=new Random();
        for (int i=1;i<=6;i++){
            tempStr.append(c[random.nextInt(c.length)]);
        }

        colorStr="#"+tempStr;
        Log.d("TAG",colorStr);

        return colorStr;
    }

    /*
     * 生成随机的高度,为了实现不规则高度
     * */
    protected int getRandomHeight(){
        int height=384;
        height= (int) (200+Math.random()*200);
        return height;
    }
}
