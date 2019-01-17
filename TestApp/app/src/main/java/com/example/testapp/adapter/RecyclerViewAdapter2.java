package com.example.testapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testapp.R;
import com.example.testapp.activity.Ui5Activity;
import com.example.testapp.bean.PhoneListBean;

import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder> {

    private Context context;
    private List<PhoneListBean> list;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdapter2(Context context, List<PhoneListBean> list) {
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<PhoneListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_phone_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView1.setText("电话" + toChinese((position + 1) + ""));
        holder.editText.setText(list.get(position).getName());
        holder.et_phone.setText(list.get(position).getPhone());

        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                list.get(position).setName(s.toString());
            }
        });

        holder.et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                list.get(position).setPhone(s.toString());
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                ((Ui5Activity)context).initRecyclerView();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void add() {
        PhoneListBean p = new PhoneListBean();
        p.setName("fd");
        list.add(new PhoneListBean());
        notifyItemInserted(list.size() - 1);
    }

    public void remove(int position) {
        list.remove(position);
//        notifyItemRemoved(position);
//        if (position != list.size()) {
//            notifyItemRangeChanged(position, list.size() - position);
//        }
        ((Ui5Activity)context).initRecyclerView();
    }

    //自定义的ViewHolder,减少findViewById调用次数
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        EditText editText;
        EditText et_phone;
        Button remove;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text_view1);
            editText = itemView.findViewById(R.id.edit_text);
            et_phone = itemView.findViewById(R.id.et_phone);
            remove = itemView.findViewById(R.id.remove);
        }
    }

    private static String toChinese(String input) {
        String[] num = {"零","一","二","三","四","五","六","七","八","九","十"};

        String[] unit = {"","十","百","千","万","十","百","千","亿"};

        String[] result;
        String out = "";
        result = new String[input.length()];
        int length = result.length;
        for(int i = 0; i< length; i++) {
            result[i] = String.valueOf(input.charAt(i));
        }
        for(int i = 0; i< length; i++) {
            int back;
            if(!result[i].equals("0")) {
                back = length - i - 1;
                out += num[Integer.parseInt(result[i])];
                out += unit[back];
            } else {
                //最后一位不考虑
                if(i == (length - 1)) {
                    if(length > 4 && result[length - 1].equals("0") && result[length - 2].equals("0") && result[length - 3].equals("0") && result[length - 4].equals("0")){
                        out += unit[4];
                    }
                } else {
                    //九位数，千万，百万，十万，万位都为0，则不加“万”
                    if(length == 9 && result[1].equals("0") && result[2].equals("0") && result[3].equals("0") && result[4].equals("0")) {

                    } else {
                        //大于万位，连着的两个数不为0，万位等于0则加上“万”
                        if(length > 4 && !result[i+1].equals("0") && result[length -5].equals("0")){
                            out += unit[4];
                        }
                    }
                    //万位之后的零显示
                    if(i == length -4 && !result[i+1].equals("0")) {
                        out += num[0];
                    }
                }
            }
        }

        if (out.length() >= 2 && out.length() <= 3) {
            if ("一十".equals(out.substring(0,2))) {
                out = out.substring(1);
            }
        }
        return out;
    }

}
