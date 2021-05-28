package com.emotionwave.fontdemo.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.emotionwave.fontdemo.R;

import org.w3c.dom.Attr;

public class StrokeTextView extends androidx.appcompat.widget.AppCompatTextView {
    private int strokeWidth;
    private Integer strokeColor;
    private Paint.Join strokeJoin;
    private float strokeMiter;

    public StrokeTextView(Context context) {
        super(context);
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @SuppressLint("ResourceType")
    public void init(Context context, AttributeSet attrs) {
        @SuppressLint("Recycle") TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView);
        if (a.hasValue(R.styleable.StrokeTextView_strokeColor)) {
            int strokeWidth = a.getDimensionPixelSize(R.styleable.StrokeTextView_strokeWidth, 1);
            int strokeColor = a.getColor(R.styleable.StrokeTextView_strokeColor, 0xff000000);
            float strokeMiter = a.getDimensionPixelSize(R.styleable.StrokeTextView_strokeMiter, 10);
            Paint.Join strokeJoin = null;
            switch (a.getInt(R.styleable.StrokeTextView_strokeWidth, 0)) {
                case (0):
                    strokeJoin = Paint.Join.MITER;
                    break;
                case (1):
                    strokeJoin = Paint.Join.BEVEL;
                    break;
                case (2):
                    strokeJoin = Paint.Join.ROUND;
                    break;
            }
            this.setStroke(strokeWidth, strokeColor, strokeJoin, strokeMiter);
        }
    }

    private void setStroke(int strokeWidth, int strokeColor, Paint.Join strokeJoin, float strokeMiter) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
        this.strokeJoin = strokeJoin;
        this.strokeMiter = strokeMiter;
    }


    public void setStrokeWidth(int width) {
        strokeWidth = width;
    }

    public void setStrokeColor(int color) {
        strokeColor = color;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int restoreColor = this.getCurrentTextColor();
        if(strokeColor != null) {
            ColorStateList colorStateList = getTextColors();
            getPaint().setStyle(Paint.Style.STROKE);
            getPaint().setStrokeWidth(strokeWidth);
            this.setTextColor(strokeColor);
            super.onDraw(canvas);
            getPaint().setStyle(Paint.Style.FILL);
            this.setTextColor(colorStateList);
        }
        super.onDraw(canvas);
    }
}
