package com.example.testapp.activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.example.testapp.R;
import com.example.testapp.util.KeyboardUtil;
import com.example.testapp.util.KeyboardUtil2;

public class Ui6Activity extends AppCompatActivity {
    EditText editText, editText2;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui6);

        editText = findViewById(R.id.edit_text);
        editText2 = findViewById(R.id.edit_text2);
        button1 = findViewById(R.id.button1);

        editText.setInputType(InputType.TYPE_NULL);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyboardUtil.shared(Ui6Activity.this,editText).showKeyboard();
                Log.d("Ui6", "onClick");
            }
        });

        editText2.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        editText2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                new KeyboardUtil2(Ui6Activity.this, Ui6Activity.this, editText2).showKeyboard();
                return false;
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert();
            }
        });
    }

    private void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dialog_msg, null);
//        View view = View.inflate(this, R.layout.dialog_msg, null);
        dialog.setView(view, 0, 0, 0,0);
        dialog.setCanceledOnTouchOutside(false);
        Button yes = view.findViewById(R.id.yes);
        Button no = view.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
