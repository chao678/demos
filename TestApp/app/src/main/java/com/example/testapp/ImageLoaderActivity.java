package com.example.testapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class ImageLoaderActivity extends AppCompatActivity {

    private com.nostra13.universalimageloader.core.ImageLoader imageLoader;
    private DisplayImageOptions options;
    private ImageView img;
    private Button loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);

        initViews();
    }

    private void initViews() {
        img = findViewById(R.id.img);
        loader = findViewById(R.id.loader);

        loader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoader.displayImage("http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg", img, options);
            }
        });

        initImageLoader();
    }

    /**
     * 初始化ImageLoader 网络加载列表图片
     */
    private void initImageLoader() {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).threadPoolSize(6) // default
                .threadPriority(Thread.NORM_PRIORITY - 3) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory().writeDebugLogs().build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(false).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565)
                // .displayer(new RoundedBitmapDisplayer(50))
                .build();

        imageLoader.displayImage("http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg", img, options);

    }
}
