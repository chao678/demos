package com.example.testapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testapp.R;
import com.example.testapp.WaterfallItemObj;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<WaterfallItemObj> list;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Context context, List<WaterfallItemObj> list) {
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder == null) {
            return;
        }
        //获取到布局参数，然后设置随机的高度
        ViewGroup.LayoutParams params = holder.textView.getLayoutParams();
        params.height = list.get(position).getHeight();
        holder.textView.setLayoutParams(params);
        holder.textView.setText(list.get(position).getColorText());
//        Drawable drawableColor = new ColorDrawable(Color.parseColor(list.get(position).getBgColor()));
//        holder.textView.setBackground(drawableColor);
        holder.textView.setBackgroundColor(Color.parseColor(list.get(position).getBgColor()));
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
