package com.emotionwave.fontdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.emotionwave.fontdemo.customview.StrokeTextView;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;


public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("nativelib");
    }
    Context mContext;
    StrokeTextView textView;

    // freetype 사용
    boolean isBold = false;
    boolean isItalic = false;
    boolean isBackground = false;
    boolean isColorPanel = false;
    boolean isShader = false;
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
                                popupmenu.setText("WedgieRegular ");
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
                textView.invalidate();
            }
        });
        Button green = findViewById(R.id.green);
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.GREEN);
                textView.invalidate();
            }
        });
        Button blue = findViewById(R.id.blue);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.BLUE);
                textView.invalidate();
            }
        });
        Button strokered = findViewById(R.id.stroke_red);
        strokered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setStrokeWidth(3);
                textView.setStrokeColor(Color.RED);
                textView.invalidate();
                //textView.setTypeface(textView.getTypeface(), textView.getTypeface().getStyle());
            }
        });
        Button strokegreen = findViewById(R.id.stroke_green);
        strokegreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setStrokeWidth(3);
                textView.setStrokeColor(Color.GREEN);
                textView.invalidate();
                //textView.setTypeface(textView.getTypeface(), textView.getTypeface().getStyle());
            }
        });
        Button strokeblue = findViewById(R.id.stroke_blue);
        strokeblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setStrokeWidth(3);
                textView.setStrokeColor(Color.BLUE);
                textView.invalidate();
                //textView.setTypeface(textView.getTypeface(), textView.getTypeface().getStyle());
            }
        });
        Button bold = findViewById(R.id.bold);
        bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBold) {
                    textView.setTypeface(null, Typeface.NORMAL);
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
                    textView.setTypeface(null, Typeface.NORMAL);
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
                if(isBackground) {
                    Shader shader = new LinearGradient(0, 0, 0, textView.getTextSize(), Color.BLACK, Color.BLACK, Shader.TileMode.CLAMP);
                    textView.getPaint().setShader(shader);
                    textView.invalidate();
                    isBackground = false;
                } else {
                    Shader shader = new LinearGradient(0, 0, 0, textView.getTextSize(), Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
                    textView.getPaint().setShader(shader);
                    textView.invalidate();
                    isBackground = true;
                }

            }
        });
        Button shader = findViewById(R.id.shader);
        shader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShader) {
                    isShader = false;
                    textView.setShadowLayer(0.0f, 0.0f, 0.0f, Color.BLACK);
                } else {
                    isShader = true;
                    textView.setShadowLayer(9.0f, 3.0f, 3.0f, Color.BLUE);
                }
            }
        });
    }
}

