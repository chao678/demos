package com.example.mydemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mydemo.R;
import com.example.mydemo.view.MyListView;

import java.util.ArrayList;
import java.util.List;

public class Test2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        ListView listView = findViewById(R.id.listView);
        Button add = findViewById(R.id.add);
        final List<String> list = new ArrayList<>();
        list.add("aaa0");
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.item_test2, list);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("aaa" + list.size());
                adapter.notifyDataSetChanged();
            }
        });

        listView.setAdapter(adapter);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setStackFromBottom(true);

    }
}
