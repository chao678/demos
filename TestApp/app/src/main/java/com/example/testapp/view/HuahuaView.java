package com.example.testapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class HuahuaView extends View {
    //画笔
    Paint mPaint;

    public HuahuaView(Context context) {
        super(context);
        init();
    }

    public HuahuaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HuahuaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //创建画笔
        mPaint = new Paint();
        //设置画笔颜色
        mPaint.setColor(Color.BLUE);
        //设置画笔宽度
        mPaint.setStrokeWidth(5f);
        //设置画笔模式为填充
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();

        //获取控件的宽度和高度
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;

        //设置圆的半径 = 宽，高最小值的2分之1
        int r = Math.min(width, height)/2;
        //画圆
//        canvas.drawCircle(paddingLeft + width/2, paddingTop + height/2, r, mPaint);
//        canvas.drawRoundRect(2.5f, 2.5f, 102.5f, 102.5f, 5, 5, mPaint);
        /* 设置渐变色 这个正方形的颜色是改变的 */
        Shader mShader = new LinearGradient(0, 0, 100, 100,
                new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
                        Color.LTGRAY }, null, Shader.TileMode.REPEAT); // 一个材质,打造出一个线性梯度沿著一条线。
        mPaint.setShader(mShader);
        canvas.drawRoundRect(new RectF(2.5f, 2.5f, 102.5f, 102.5f), 10, 10,mPaint);
        canvas.drawRect(new Rect(115,115,215,315), mPaint);
    }
}
