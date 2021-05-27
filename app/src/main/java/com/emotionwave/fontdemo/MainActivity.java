package com.emotionwave.fontdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.renderscript.ScriptIntrinsicHistogram;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("nativelib");
    }

    Context mContext;
    TextView textView;
    boolean isBold = false;
    boolean isItalic = false;
    boolean isBackground = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplication();
        textView = findViewById(R.id.textview);
        Button cinderella = findViewById(R.id.cinderella);
        cinderella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("getAssets", getAssets().toString());
                textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/cinderella.otf"));
            }
        });
        Button lanehum = findViewById(R.id.lanehum);
        lanehum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/lanehum.ttf"));
            }
        });
        Button orangejuice = findViewById(R.id.orangejuice);
        orangejuice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/orangejuice.ttf"));
            }
        });
        Button ormontlight = findViewById(R.id.ormontlight);
        ormontlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/ormontlight.ttf"));
            }
        });
        Button wedgieregular = findViewById(R.id.wedgieregular);
        wedgieregular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/wedgieregular.ttf"));
            }
        });
        Button red = findViewById(R.id.red);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.RED);
            }
        });
        Button green = findViewById(R.id.green);
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.GREEN);
            }
        });
        Button blue = findViewById(R.id.blue);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.BLUE);
            }
        });
        Button bold = findViewById(R.id.bold);
        bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBold) {
                    textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
                    isBold = false;
                } else {
                    textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                    isBold = true;
                }
            }
        });
        Button italic = findViewById(R.id.italic);
        italic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isItalic) {
                    textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
                    isItalic = false;
                } else {
                    textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
                    isItalic = true;
                }
            }
        });
        Button strike = findViewById(R.id.strikethru);
        strike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getPaintFlags() == (Paint.STRIKE_THRU_TEXT_FLAG | textView.getPaintFlags())) {
                    textView.setPaintFlags(textView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                } else {
                    textView.setPaintFlags(textView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });
        Button underline = findViewById(R.id.underline);
        underline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getPaintFlags() == (Paint.UNDERLINE_TEXT_FLAG | textView.getPaintFlags())) {
                    textView.setPaintFlags(textView.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));
                } else {
                    textView.setPaintFlags(textView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
                }
            }
        });
        Button background = findViewById(R.id.background);
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBackground) {
                    textView.setBackgroundResource(0);
                    isBackground = false;
                } else {
                    textView.setBackgroundResource(R.drawable.gradation);
                    isBackground = true;
                }

            }
        });
    }
}

