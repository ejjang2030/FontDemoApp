package com.emotionwave.fontdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.renderscript.ScriptIntrinsicHistogram;
import android.renderscript.Type;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("nativelib");
    }
    Context mContext;
    TextView textView;
    boolean isBold = false;
    boolean isItalic = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplication();
        textView = findViewById(R.id.textview);
        TextView popupmenu = findViewById(R.id.popupmenu);
        popupmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getApplicationContext(), v);
                getMenuInflater().inflate(R.menu.select_font_type, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.cinderella:
                                textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/cinderella.otf"));
                                popupmenu.setText("Cinderella");
                                break;
                            case R.id.lanehum:
                                textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/lanehum.ttf"));
                                popupmenu.setText("Lanehum");
                                break;
                            case R.id.orangejuice:
                                textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/orangejuice.ttf"));
                                popupmenu.setText("OrangeJuice");
                                break;
                            case R.id.ormontlight:
                                textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/ormontlight.ttf"));
                                popupmenu.setText("OrmontLight");
                                break;
                            case R.id.wedgieregular:
                                textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/wedgieregular.ttf"));
                                popupmenu.setText("WedgieRegular");
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
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
                // https://www.youtube.com/watch?v=q2GtM1_RmMw 참고
                Shader shader = new LinearGradient(0, 0, 0, textView.getTextSize(), Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
                textView.getPaint().setShader(shader);
            }
        });
    }
}

