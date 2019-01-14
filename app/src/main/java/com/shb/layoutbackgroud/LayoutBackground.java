package com.shb.layoutbackgroud;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

public class LayoutBackground extends FrameLayout {
    private static final String TAG = "LayoutBackground";
    private static final int v = 0;
    private int solidColor;
    private int defaultColor;
    private int strokeColor;
    private int pressColor;
    private float conners;
    private int strokeWidth;
    private float topLeftConner;
    private float topRightConner;
    private float bottomLeftConner;
    private float bottomRightConner;
    private float mDefaultConner;
    private boolean isItemType;

    public LayoutBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LayoutBackground);
        defaultColor = typedArray.getColor(R.styleable.LayoutBackground_defaultColor, Color.TRANSPARENT);
        pressColor = typedArray.getColor(R.styleable.LayoutBackground_pressColor, Color.TRANSPARENT);
        solidColor = typedArray.getColor(R.styleable.LayoutBackground_solidColor, Color.TRANSPARENT);
        strokeColor = typedArray.getColor(R.styleable.LayoutBackground_strokeColor, Color.TRANSPARENT);
        mDefaultConner = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, context.getResources().getDisplayMetrics());
        conners = typedArray.getDimension(R.styleable.LayoutBackground_conners, mDefaultConner);
        topRightConner = typedArray.getDimension(R.styleable.LayoutBackground_topRightConner, mDefaultConner);
        topLeftConner = typedArray.getDimension(R.styleable.LayoutBackground_topLeftConner, mDefaultConner);
        bottomLeftConner = typedArray.getDimension(R.styleable.LayoutBackground_bottomLeftConner, mDefaultConner);
        bottomRightConner = typedArray.getDimension(R.styleable.LayoutBackground_bottomRightConner, mDefaultConner);
        strokeWidth = (int) Math.ceil(typedArray.getDimension(R.styleable.LayoutBackground_strokeWidth, 0));
        isItemType = typedArray.getBoolean(R.styleable.LayoutBackground_itemType, false);


        typedArray.recycle();

        if (isItemType) {
            //todo
            StateListDrawable listDrawable = new StateListDrawable();
            int state_pressed = android.R.attr.state_pressed;
            listDrawable.addState(new int[]{state_pressed}, getClickDrawable(getContext(),
                    android.R.attr.selectableItemBackground));
            this.setBackground(listDrawable);

        } else {
            if (topLeftConner != mDefaultConner) {
                this.setBackground(getStateListDrawable(pressColor, solidColor, topLeftConner, topRightConner, bottomLeftConner, bottomRightConner
                ));

            } else {
                this.setBackground(getStateListDrawable(pressColor, solidColor, conners));
            }
        }


    }

    private Drawable getStateListDrawable(int pressColor, int solidColor, float topLeftConner, float topRightConner, float bottomLeftConner, float bottomRightConner) {
        StateListDrawable listDrawable = new StateListDrawable();
        int state_pressed = android.R.attr.state_pressed;
        // int state_click = android.R.attr.clickable;

        float[] floats = {topLeftConner, topRightConner, bottomLeftConner, bottomRightConner};

        GradientDrawable pressDrawable = new GradientDrawable();
        pressDrawable.setColor(pressColor);
        pressDrawable.setCornerRadii(floats);
        pressDrawable.setStroke(strokeWidth, strokeColor);


        GradientDrawable solidDrawable = new GradientDrawable();
        solidDrawable.setColor(solidColor);
        solidDrawable.setCornerRadii(floats);
        solidDrawable.setStroke(strokeWidth, strokeColor);


        listDrawable.addState(new int[]{state_pressed}, pressDrawable);
        listDrawable.addState(new int[]{-state_pressed}, solidDrawable);
        //listDrawable.addState(new int[]{state_click}, getClickDrawable(getContext(),
        //       android.R.attr.selectableItemBackground));

        return listDrawable;
    }


    private static Drawable getClickDrawable(@NonNull Context context, @AttrRes int attr) {
        TypedArray ta = context.obtainStyledAttributes(new int[]{
                attr
        });
        Drawable drawable = ta.getDrawable(0);
        ta.recycle();
        return drawable;
    }

    private Drawable getStateListDrawable(int pressColor, int solidColor, float conners) {
        StateListDrawable listDrawable = new StateListDrawable();
        int state_pressed = android.R.attr.state_pressed;


        GradientDrawable pressDrawable = new GradientDrawable();
        pressDrawable.setColor(pressColor);
        pressDrawable.setCornerRadius(conners);
        pressDrawable.setStroke(strokeWidth, strokeColor);


        GradientDrawable solidDrawable = new GradientDrawable();
        solidDrawable.setColor(solidColor);
        solidDrawable.setCornerRadius(conners);
        solidDrawable.setStroke(strokeWidth, strokeColor);


        listDrawable.addState(new int[]{state_pressed}, pressDrawable);
        listDrawable.addState(new int[]{-state_pressed}, solidDrawable);


        return listDrawable;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

       /* int left = getLeft();
        int right = getRight();
        int top = getTop();
        int bottom = getBottom();


        Log.d(TAG, "onMeasure: left: " + top);
        Log.d(TAG, "onMeasure: right: " + right);
        Log.d(TAG, "onMeasure: top: " + top);
        Log.d(TAG, "onMeasure: bottom: " + bottom);*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isItemType) {
//            Paint paint = new Paint();
//            paint.setStyle(Paint.Style.STROKE);
//            paint.setDither(true);
//            paint.setAntiAlias(true);
//            paint.setColor(strokeColor);
//            paint.setStrokeWidth(strokeWidth);
//            paint.setStrokeCap(Paint.Cap.ROUND);
            //canvas.drawRect(new RectF(0, 0, getWidth(), getHeight()), paint);

            // canvas.drawRoundRect(new RectF(conners/2, conners/2, getWidth() - conners/2, getHeight() - conners/2), conners, conners, paint);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

    }
}
