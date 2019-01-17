package com.example.testapp;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.activity.Ui2Activity;
import com.example.testapp.activity.Ui3Activity;
import com.example.testapp.activity.Ui4Activity;
import com.example.testapp.view.HuahuaView;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Banner top_banner;
    private RadioGroup rg1;
    private RadioButton r1;
    private RadioButton r2;
    private Button button1;
    private Button ui2;
    private PopupWindow mPopWindow;
    private TextView text;
    private Button btn3, btn4, btn5;
    private HuahuaView huahuaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册EventBus
        EventBus.getDefault().register(this);

        TextView textView = findViewById(R.id.text1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert();
            }
        });

        rg1 = findViewById(R.id.rg1);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        button1 = findViewById(R.id.button1);
        Button button1_ = findViewById(R.id.button1);
        Log.d("button1=" ,button1.hashCode()+"");
        Log.d("button1_=" ,button1_.hashCode()+"");

        rg1.check(R.id.r1);
        button1.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);

            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }
        Toast.makeText(this, "Build.VERSION.SDK_INT = " + Build.VERSION.SDK_INT +
                "; Build.VERSION_CODES.O = " + Build.VERSION_CODES.O, Toast.LENGTH_SHORT).show();

        initViews();


    }

    private void initViews() {
        ui2 = findViewById(R.id.ui2);
        text = findViewById(R.id.text);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        huahuaView = findViewById(R.id.huahuaView);

        ui2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
    }

    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_window, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv1 = (TextView)contentView.findViewById(R.id.pop_computer);
        TextView tv2 = (TextView)contentView.findViewById(R.id.pop_financial);
        TextView tv3 = (TextView)contentView.findViewById(R.id.pop_manage);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
//        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        mPopWindow.showAsDropDown(ui2);

    }

    private void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_red_message, null);
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
//                Animator animator = AnimatorInflater.loadAnimator(this, R.animator.set_animation); // 载入XML动画
//                animator.setTarget(view); // 设置动画对象
//                animator.start(); // 启动动画
                propertyAnimation(view);
                break;
            case R.id.ui2:
                Intent intent = new Intent(this, Ui2Activity.class);
                startActivity(intent);
                break;
            case R.id.btn3:
                Intent intent3 = new Intent(this, Ui3Activity.class);
                startActivity(intent3);
                break;
            case R.id.btn4:
                Intent intent4 = new Intent(this, Ui4Activity.class);
                startActivity(intent4);
                break;
            case R.id.btn5:
                Intent intent5 = new Intent(this, ImageLoaderActivity.class);
                startActivity(intent5);
                break;
        }
    }

    private void propertyAnimation(final View view) {
        // 步骤1：设置动画属性的初始值 & 结束值
        ValueAnimator anim = ValueAnimator.ofInt(200, 500);
            // ofInt（）作用有两个
            // 1. 创建动画实例
            // 2. 将传入的多个Int参数进行平滑过渡:此处传入0和1,表示将值从0平滑过渡到1
            // 如果传入了3个Int参数 a,b,c ,则是先从a平滑过渡到b,再从b平滑过渡到C，以此类推
            // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置，即默认设置了如何从初始值 过渡到 结束值
            // 关于自定义插值器我将在下节进行讲解
            // 下面看看ofInt()的源码分析 ->>关注1

        // 步骤2：设置动画的播放各种属性
            anim.setDuration(2000);
            // 设置动画运行的时长

            //anim.setStartDelay(500);
            // 设置动画延迟播放时间

            anim.setRepeatCount(0);
            // 设置动画重复播放次数 = 重放次数+1
            // 动画播放次数 = infinite时,动画无限重复

            anim.setRepeatMode(ValueAnimator.RESTART);
            // 设置重复播放动画模式
            // ValueAnimator.RESTART(默认):正序重放
            // ValueAnimator.REVERSE:倒序回放

        // 步骤3：将改变的值手动赋值给对象的属性值：通过动画的更新监听器
            // 设置 值的更新监听器
            // 即：值每次改变、变化一次,该方法就会被调用一次
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int currentValue = (Integer) animation.getAnimatedValue();
                    // 获得改变后的值
                    System.out.println(currentValue);

                    button1.getLayoutParams().width = currentValue;
                    // 每次值变化时，将值手动赋值给对象的属性
                    // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化

                    // 步骤4：刷新视图，即重新绘制，从而实现动画效果
                    button1.requestLayout();
                }
            });
            anim.start();
            // 启动动画
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Main", ">>>>>>>>>>>>.....onDestroy...<<<<<<<<<<<<<<<<<");
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {

        String msg = "main收到了消息：" + event.getMsg();
        text.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
