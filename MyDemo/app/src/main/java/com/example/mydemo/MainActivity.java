package com.example.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mydemo.base.BaseActivity;
import com.example.mydemo.ui.activity.DongHuaActivity;
import com.example.mydemo.view.XCDropDownListView;
import com.example.mydemo.view.combobox.DropdownButton;
import com.example.mydemo.view.combobox.bean.DropBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private XCDropDownListView dropDownListView;
    private DropdownButton combobox;
    private List<DropBean> listCombobox;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dropDownListView = findViewById(R.id.drop_down_list_view);
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0;i< 6;i++){
            list.add("下拉列表项"+(i+1));
        }
        dropDownListView.setItemsData(list);

        combobox = findViewById(R.id.combobox);
        listCombobox = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listCombobox.add(new DropBean("下拉选项" + i));
        }
        combobox.setData(listCombobox);
        combobox.setOnDropItemSelectListener(new DropdownButton.OnDropItemSelectListener() {
            @Override
            public void onDropItemSelect(int postion) {
                Toast.makeText(MainActivity.this, "选择了:" + listCombobox.get(postion).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        initViews();
    }

    private void initViews() {
        spinner = findViewById(R.id.spinner);
        MyAdapter myAdapter = new MyAdapter();
        spinner.setAdapter(myAdapter);

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AliyunVideoRecorder.class));
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DongHuaActivity.class));
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)); //直接进入手机中的wifi网络设置界面
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public MyAdapter() {
            this.inflater = LayoutInflater.from(MainActivity.this);
        }

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_spinner, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        class ViewHolder {
            public View rootView;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
