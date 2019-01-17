package com.example.testapp.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.testapp.R;
import com.example.testapp.activity.Pic2Activity;
import com.example.testapp.bean.PicBean;
import com.example.testapp.util.CameraUtils2;
import com.example.testapp.util.ImageTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.ViewHolder>  {
    private Context context;
    private List<PicBean> list;
    private LayoutInflater layoutInflater;
    public CameraUtils2 cameraUtils;
    private File caFile;
    private int mPosition;
    private String flag;//判断是添加还是更改

    public PicAdapter(Context context, List<PicBean> list) {
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public PicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_pic, parent, false);
        return new PicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PicAdapter.ViewHolder holder, final int position) {
        if (holder == null) {
            return;
        }
        if (position == list.size()-1) {
            holder.imageView1.setImageResource(R.mipmap.icon_addpic_unfocused);
            holder.imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPosition = position;
                    flag = "add";
                    dialog();
                }
            });
        } else {

            if (list.get(position).getFile() != null) {
                holder.imageView1.setImageBitmap(getLoacalBitmap(holder, list.get(position).getFile().getAbsolutePath()));
            } else {
                Glide.with(context).load(list.get(position).getUrl())
                        .placeholder(R.drawable.ic_launcher_background) //设置占位图
                        .error(R.drawable.ic_launcher_background) //设置错误图片
                        .into(holder.imageView1);
            }
            holder.imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPosition = position;
                    flag = "";
                    dialog();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //自定义的ViewHolder,减少findViewById调用次数
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;

        //在布局中找到所含有的UI组件
        public ViewHolder(View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.imageView1);
        }
    }

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    public Bitmap getLoacalBitmap(PicAdapter.ViewHolder holder, String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            int width = holder.imageView1.getMeasuredWidth();
            int height = holder.imageView1.getMeasuredHeight();


            Log.i("hey,man", width + "^^^^" + height);
            return ImageTools.zoomBitmap(BitmapFactory.decodeStream(fis), width, height);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 提示选择相机还是相册
     */
    private void dialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        final android.app.AlertDialog dialog01 = builder.create();
        View view2 = View.inflate(context, R.layout.dialog_selection, null);

        dialog01.setView(view2, 0, 0, 0, 0);
        Button xc= (Button) view2.findViewById(R.id.button1);
        Button pz= (Button) view2.findViewById(R.id.button2);
        Button qx= (Button) view2.findViewById(R.id.button3);
        xc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相册
                cameraUtils = new CameraUtils2((Activity) context, handler);
                cameraUtils.MyPicture();
                dialog01.dismiss();
            }
        });
        pz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA)){
                    // 请求授权
                    ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.CAMERA},1);
                }else {
                    ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.CAMERA},1);
                }
                //拍照
                cameraUtils = new CameraUtils2((Activity) context, handler);
                cameraUtils.MyCamera("image" + mPosition + ".png");
                dialog01.dismiss();

            }
        });
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog01.dismiss();
            }
        });
        dialog01.show();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 800:
                    Bundle bundle = msg.getData();
                    caFile = (File) bundle.getSerializable("CaFile");
                    if ("add".equals(flag)) {
                        list.add(mPosition, new PicBean(caFile, null));
                    } else {
                        list.get(mPosition).setFile(caFile);
                    }
                    notifyDataSetChanged();
                    Log.d("111", "dfsf");
                    break;
            }
        }
    };
}
