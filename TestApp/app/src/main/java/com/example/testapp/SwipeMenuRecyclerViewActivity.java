package com.example.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

public class SwipeMenuRecyclerViewActivity extends AppCompatActivity {
    private SwipeMenuRecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_menu_recycler_view);

        initViews();
    }

    private void initViews() {
        recycler_view = findViewById(R.id.recycler_view);

    }
}
