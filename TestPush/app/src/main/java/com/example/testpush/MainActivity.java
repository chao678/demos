package com.example.testpush;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //创建普通的通知
    public void btn1(View v) {
        //这里的通知要使用Builder对象来创建
        Notification.Builder builder = new Notification.Builder(this);
        //显示在通知栏上的图标是必须的
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //在通知栏上显示文字信息，可以不写
        builder.setTicker("你有一条新信息！");

        //这是状态栏上的信息
        builder.setContentTitle("文本标题");//标题信息
        builder.setContentText("文本内容");//文本信息
        builder.setWhen(System.currentTimeMillis());//显示发送的时间
        builder.setContentInfo("Info");//文本的其他信息
        //设置通知模式，参数如下
        //Notification.DEFAULT_LIGHTS闪光模式
        // Notification.DEFAULT_SOUND声音模式
        // Notification.DEFAULT_VIBRATE震动模式
        //Notification.DEFAULT_ALL以上三中模式都选
        builder.setDefaults(Notification.DEFAULT_ALL);

        //创建Notification对象,生成对象 16API以上，支持低版本需要使用v4包中的notificationCompat
        Notification notify = builder.build();
        //设置消息不能清除
        notify.flags = Notification.FLAG_NO_CLEAR;
        //利用系统的Manage发送消息
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //发送消息
        //第一个参数是通知的一个唯一标识值，
        manager.notify((int) (Math.random() * 100), notify);

    }

    //创建自定义的通知
    public void btn2(View v) {
        //这里的通知要使用Builder对象来创建
        Notification.Builder builder = new Notification.Builder(this);
        //显示在通知栏上的图标是必须的
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //在通知栏上显示文字信息，可以不写
        builder.setTicker("你有一条自定义的信息！");
        //ledARGB 表示灯光颜色、 ledOnMS 亮持续时间、ledOffMS 暗的时间
        builder.setLights(0xFF0000, 3000, 3000);
        //这是状态栏上的信息
       /* builder.setContentTitle("文本标题");//标题信息
        builder.setContentText("文本内容");//文本信息
        builder.setWhen(System.currentTimeMillis());//显示发送的时间
        builder.setContentInfo("Info");//文本的其他信息*/
        //设置通知模式
        //Notification.DEFAULT_ALL以上三中模式都选
        builder.setDefaults(Notification.DEFAULT_ALL);
        //给通知设置自定义布局
        //这里的View布局是RemoteViews类的布局,第一个参数是包名，第二个参数是布局文件名
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.activity_notification);
        //给自定义布局中的文本设置红色颜色
        view.setTextColor(R.id.notification_tv, Color.RED);
        //给builder对象添加布局
        builder.setContent(view);
        //给view里面的文本控件添加监听事件
        //点击文本后要跳转去的页面
        Intent intent = new Intent(this, MyTextView.class);
        //下面的方法有四个参数：
        // 第一个参数代表的是上下文
        //第二个参数是一个请求码
        //第三个参数是是代表意图对象
        //第四个参数是是代表跳转页面的形式
        PendingIntent intent_tv = PendingIntent.getActivity(this, 11, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.notification_tv, intent_tv);

        //给布局里的图像控件添加点击监听事件，跟上面文本监听事件一样的
        //点击文本后要跳转去的页面
        Intent intent2 = new Intent(this, MyImageView.class);
        //下面的方法有四个参数：
        // 第一个参数代表的是上下文
        //第二个参数是一个请求码
        //第三个参数是是代表意图对象
        //第四个参数是是代表跳转页面的形式
        PendingIntent intent_iv = PendingIntent.getActivity(this, 12, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.notification_iv, intent_iv);

        //创建Notification对象,生成对象 16API以上，支持低版本需要使用v4包中的notificationCompat
        Notification notify = builder.build();
        //设置消息可以使用通知栏上的清除按钮清除消息
        notify.flags = Notification.FLAG_AUTO_CANCEL;
        //利用系统的Manage发送消息
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //发送消息
        //第一个参数是通知的一个唯一标识值，
        manager.notify(11, notify);

    }

    //清除自定义的通知
    public void btn3(View v) {
        //清除消息也用到manager对象
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //清除某一条通知，必须知道它的通知的标识值
        manager.cancel(11);
    }

    //清除所有的通知
    public void btn4(View v) {
        //清除消息也用到manager对象
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //清除所有的通知
        manager.cancelAll();
    }
}
