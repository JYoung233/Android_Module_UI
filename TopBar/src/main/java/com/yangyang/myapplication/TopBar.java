package com.yangyang.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import android.util.AttributeSet;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by asus on 2016/3/6.
 */
public class TopBar extends RelativeLayout {
    private Button leftbutton,rightbutton;
    private TextView tvTitle;
    //获取组件中的属性
    private String titleText,leftText,rightText;
    private int titleColor,leftColor,rightColor;
    private float titleSize;
    private Drawable leftBackground,rightBackground;

    private LayoutParams leftparams,rightparams,titleparams;
    private OnTopBarListenner listenner;

    public interface OnTopBarListenner{
        public void leftclick();
        public void rightclick();
    }
    public void setOnClikTopBarListenner(OnTopBarListenner listenner){
        this.listenner=listenner;
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.TopBar);
        titleColor=ta.getColor(R.styleable.TopBar_TitleColor, 0);
        titleSize=ta.getDimension(R.styleable.TopBar_TextSize, 0);
        titleText=ta.getString(R.styleable.TopBar_TitleText);
        
        leftText=ta.getString(R.styleable.TopBar_LeftText);
        leftColor=ta.getColor(R.styleable.TopBar_LeftColor, 0);
        leftBackground=ta.getDrawable(R.styleable.TopBar_LeftBackground);

        rightText=ta.getString(R.styleable.TopBar_RightText);
        rightColor=ta.getColor(R.styleable.TopBar_RightColor, 0);
        rightBackground=ta.getDrawable(R.styleable.TopBar_RightBackground);

        ta.recycle();
        leftbutton=new Button(context);
        rightbutton=new Button(context);
        tvTitle=new TextView(context);
        
        leftbutton.setText(leftText);
       // leftbutton.setBackground(leftBackground);//这里注意sdk不能低于16
        leftbutton.setTextColor(leftColor);

        rightbutton.setText(rightText);
       // rightbutton.setBackground(rightBackground);//这里注意sdk不能低于16
        rightbutton.setTextColor(rightColor);

        tvTitle.setText(titleText);
        tvTitle.setTextColor(titleColor);
        tvTitle.setTextSize(titleSize);
        tvTitle.setGravity(Gravity.CENTER);

        setBackgroundColor(0x33a3dcff);

        leftparams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);//这里的true是relativeLayout定义的常量

        rightparams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);

        titleparams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleparams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(leftbutton, leftparams);
        addView(rightbutton, rightparams);
        addView(tvTitle,titleparams);

        leftbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listenner.leftclick();
            }
        });
        rightbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listenner.rightclick();
            }
        });


    }
}
