package com.example.testapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.testapp.GridSpacingItemDecoration;
import com.example.testapp.R;
import com.example.testapp.adapter.RecyclerViewAdapter2;
import com.example.testapp.bean.PhoneListBean;
import com.example.testapp.recyclerview.SwipeItemLayout;

import java.util.ArrayList;
import java.util.List;

public class Ui5Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter2 recyclerViewAdapter;
    private List<PhoneListBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui5);

        initViews();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new PhoneListBean());
        }
        LinearLayoutManager mgr = new LinearLayoutManager(this);//线性布局管理器
        mgr.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mgr);
        recyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        recyclerViewAdapter = new RecyclerViewAdapter2(this, list);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 10, false));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void initRecyclerView() {
        LinearLayoutManager mgr = new LinearLayoutManager(this);//线性布局管理器
        mgr.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mgr);
        recyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        recyclerViewAdapter = new RecyclerViewAdapter2(this, list);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 10, false));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
