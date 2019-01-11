package com.example.mydemo.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mydemo.R;

public class TestActivity extends AppCompatActivity {
    Runnable r;
    Handler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        r = new Runnable() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("num", "100");
                message.setData(bundle);
                myHandler.sendMessage(message);

                myHandler.postDelayed(this, 5000);
            }
        };
        myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        Bundle bundle = msg.getData();
                        String num = bundle.getString("num");
                        Log.i("TestActivity", "num=" + num);
                        Toast.makeText(TestActivity.this, "num=" + num, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        myHandler.postDelayed(r, 5000);
//        Message message = Message.obtain();
//        message.what = 0;
//        Bundle bundle = new Bundle();
//        bundle.putString("num", "100");
//        message.setData(bundle);
//        myHandler.sendMessage(message);

        //定时执行
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                //
//            }
//        }, 3000);//延迟3秒

        ImageView image = findViewById(R.id.image);
        ObjectAnimator.ofFloat(image, "rotationY", 0.0f, 360.0f).setDuration(2000).start();

//        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1, 0, 0);
//        animator.setInterpolator(new AccelerateInterpolator());
//        animator.setDuration(2000);
//        animator.start();
//        ObjectAnimator animator02 = ObjectAnimator.ofFloat(view, "translationY", 0F, -300F);
//        animator02.setInterpolator(new AccelerateInterpolator());
//        animator02.setDuration(2000);
//        animator02.start();
        ImageView image1 = findViewById(R.id.image1);
        //补间动画
//        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.anim1);
//        hyperspaceJumpAnimation.setRepeatCount(10);
//        hyperspaceJumpAnimation.setDuration(1000);
//        image1.startAnimation(hyperspaceJumpAnimation);

        //属性动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(image1, "alpha", 1, 0, 0.5f, 0);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(1000);
        animator.setRepeatCount(-1);
        animator.start();

        ImageView image2 = findViewById(R.id.image2);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(image2, "translationX", 0f, 100f);
        animator2.setInterpolator(new AccelerateInterpolator());
        animator2.setDuration(1000);
        animator2.setRepeatCount(-1);
        animator2.start();

        ImageView image3 = findViewById(R.id.image3);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(image3, "rotationX", 0f, 360f);
        animator3.setDuration(1000);
        animator3.setRepeatCount(-1);
        animator3.start();

        ImageView image4 = findViewById(R.id.image4);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(image4, "X", 0f, 100f);
        animator4.setDuration(1000);
        animator4.setRepeatCount(-1);
        animator4.start();

        final Button btn1 = findViewById(R.id.btn1);
        final ImageView image5 = findViewById(R.id.image5);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation1 = AnimationUtils.loadAnimation(TestActivity.this, R.anim.anim2);
                animation1.setDuration(2000);
                btn1.startAnimation(animation1);

                Animation animation2 = AnimationUtils.loadAnimation(TestActivity.this, R.anim.anim3);
                animation2.setDuration(2000);
                animation2.setRepeatCount(-1);
                image5.startAnimation(animation2);
            }
        });

        View jzt = findViewById(R.id.jzt);
        ObjectAnimator animator_jzt = ObjectAnimator.ofFloat(jzt, "translationX", -100f, 400f);
        animator_jzt.setDuration(2000);
        animator_jzt.setRepeatCount(-1);
        animator_jzt.start();

        ImageView image6 = findViewById(R.id.image6);
        ImageView image7 = findViewById(R.id.image7);
        ImageView image8 = findViewById(R.id.image8);
//        ObjectAnimator animator6 = ObjectAnimator.ofFloat(image6, "scaleX", 0f, 1f);
//        animator6.setDuration(2000);
//        animator6.setRepeatCount(-1);
//        animator6.start();
//        ObjectAnimator animator6_1 = ObjectAnimator.ofFloat(image6, "scaleY", 0f, 1f);
//        animator6_1.setDuration(2000);
//        animator6_1.setRepeatCount(-1);
//        animator6_1.start();

        PropertyValuesHolder a1 = PropertyValuesHolder.ofFloat("scaleX", 0.2f, 1f);
        PropertyValuesHolder a2 = PropertyValuesHolder.ofFloat("scaleY", 0.2f, 1f);
        ObjectAnimator animator6 = ObjectAnimator.ofPropertyValuesHolder(image6, a1, a2).setDuration(500);
        animator6.setRepeatCount(-1);
        animator6.setRepeatMode(ValueAnimator.REVERSE);
        animator6.start();

        ObjectAnimator animator7 = ObjectAnimator.ofPropertyValuesHolder(image7, a1, a2);
        animator7.setRepeatCount(-1);
        animator7.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet set = new AnimatorSet();
        set.play(animator7);
        set.setDuration(500);
        set.setStartDelay(250);
        set.start();

        ObjectAnimator animator8 = ObjectAnimator.ofPropertyValuesHolder(image8, a1, a2);
        animator8.setRepeatCount(-1);
        animator8.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet set2 = new AnimatorSet();
        set2.play(animator8);
        set2.setDuration(500);
        set2.setStartDelay(500);
        set2.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacks(r);
    }
}
