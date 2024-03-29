package com.example.define;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Create by Politness Chen on 2019/9/5--14:46
 * 自定义属性
 */
public class MyAttributeView extends View{

    private int myAge;
    private String myName;
    private Bitmap myBg;

    public MyAttributeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        //获取属性三种方式
        //1.用命名空间去获取
        String str = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","my_age");  //强制类型转换？
        Log.e("main", "MyAttributeView: "+str);
        //2.遍历属性集合
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            Log.e("main", attrs.getAttributeName(i) + attrs.getAttributeValue(i));
        }

        //3.使用系统工具，获取属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyAttributeView);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.MyAttributeView_my_age:
                    myAge = typedArray.getInt(index,0);
                    break;
                case R.styleable.MyAttributeView_my_name:
                    myName = typedArray.getString(index);
                    break;
                case R.styleable.MyAttributeView_my_bg:
                    Drawable drawable = typedArray.getDrawable(index);
//                    if (Object instanceof Drawable)
                    BitmapDrawable drawable1 = (BitmapDrawable) drawable;
                    myBg = drawable1.getBitmap();
                    break;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        canvas.drawText(myName + "-----" + myAge,50,50,paint);
        canvas.drawBitmap(myBg,50,50,paint);
    }
}
