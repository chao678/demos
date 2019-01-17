package com.example.testapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.R;
import com.example.testapp.adapter.PicAdapter;
import com.example.testapp.bean.PicBean;
import com.example.testapp.util.CameraUtils2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Pic2Activity extends AppCompatActivity {

    private CameraUtils2 cameraUtils;
    private File caFile;
    private Integer cameraFlag;
    private List<PicBean> list;
    private RecyclerView recycler_view;
    private PicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic2);

        initViews();

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
//        outState.putSerializable("util", );
    }

    private void initViews() {
        list = new ArrayList<>();
        list.add(new PicBean(null, "http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg"));
        list.add(new PicBean());
        recycler_view = findViewById(R.id.recycler_view);
        GridLayoutManager mgr = new GridLayoutManager(this, 4);//网格布局管理器
        recycler_view.setLayoutManager(mgr);
        adapter = new PicAdapter(this, list);
        recycler_view.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "回来了", Toast.LENGTH_SHORT).show();
        if (adapter == null) {
            Toast.makeText(this, "adapter=null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adapter.cameraUtils == null) {
            Toast.makeText(this, "cameraUtils=null", Toast.LENGTH_SHORT).show();
            return;
        }
        adapter.cameraUtils.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "requestCode=" + requestCode + "resultCode=" + resultCode + "data=" + data, Toast.LENGTH_SHORT).show();
    }

}
