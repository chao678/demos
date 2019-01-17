package com.example.testapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testapp.activity.Pic2Activity;
import com.example.testapp.activity.Pic3Activity;
import com.example.testapp.activity.Ui11Activity;
import com.example.testapp.activity.Ui5Activity;
import com.example.testapp.activity.Ui6Activity;
import com.example.testapp.activity.Ui7Activity;
import com.example.testapp.activity.Ui8Activity;
import com.example.testapp.activity.Ui9Activity;
import com.example.testapp.bean.KeyValueBean;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<KeyValueBean<String, Class<?>>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meanu);
        setData();
        initViews();
    }

    private void setData() {
        list = new ArrayList<>();
        list.add(new KeyValueBean<String, Class<?>>("Main", MainActivity.class));
        list.add(new KeyValueBean<String, Class<?>>("网络图片", ImageLoaderActivity.class));
        list.add(new KeyValueBean<String, Class<?>>("百度地图", BaiduMapActivity.class));
        list.add(new KeyValueBean<String, Class<?>>("测滑删除", Ui5Activity.class));
        list.add(new KeyValueBean<String, Class<?>>("软键盘", Ui6Activity.class));
        list.add(new KeyValueBean<String, Class<?>>("下拉菜单", Ui7Activity.class));
        list.add(new KeyValueBean<String, Class<?>>("照片", Pic2Activity.class));
        list.add(new KeyValueBean<String, Class<?>>("照片3", Pic3Activity.class));
        list.add(new KeyValueBean<String, Class<?>>("UI8", Ui8Activity.class));
        list.add(new KeyValueBean<String, Class<?>>("UI9", Ui9Activity.class));
        list.add(new KeyValueBean<String, Class<?>>("UI11", Ui11Activity.class));
    }

    private void initViews() {
        recycler_view = findViewById(R.id.recycler_view);

//        GridLayoutManager mgr = new GridLayoutManager(this, 4);//网格布局管理器
        StaggeredGridLayoutManager mgr = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);//瀑布流布局管理器
        recycler_view.setLayoutManager(mgr);
        recyclerViewAdapter = new RecyclerViewAdapter(this, list);
//        recycler_view.addItemDecoration(new GridSpacingItemDecoration(4, 5,true));
        recycler_view.setAdapter(recyclerViewAdapter);
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        private Context context;
        private List<KeyValueBean<String, Class<?>>> list;
        private LayoutInflater layoutInflater;

        public RecyclerViewAdapter(Context context, List<KeyValueBean<String, Class<?>>> list) {
            this.context = context;
            this.list = list;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.item_recycler, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.textView.setText(list.get(position).getKey());
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MenuActivity.this, list.get(position).getValue()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        //自定义的ViewHolder,减少findViewById调用次数
        class ViewHolder extends RecyclerView.ViewHolder {

            TextView textView;

            //在布局中找到所含有的UI组件
            public ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.text1);
            }
        }
    }
}
