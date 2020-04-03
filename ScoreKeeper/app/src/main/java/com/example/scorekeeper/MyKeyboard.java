package com.example.scorekeeper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyKeyboard extends LinearLayout implements View.OnClickListener {

    // constructors
    public MyKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    // keyboard keys (buttons)
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private Button mButton9;
    private Button mButton10;
    private Button mButton11;
    private Button mButton12;
    private Button mButton13;
    private Button mButton14;
    private Button mButton15;
    private Button mButton16;
    private Button mButton17;
    private Button mButton18;
    private Button mButton19;
    private Button mButton20;
    private Button mButton0;
    private Button mButton25;
    private Button mButton50;

    SparseArray<String> keyValues = new SparseArray<>();
    //InputConnection inputConnection;
    View view;

    private void init(Context context, AttributeSet attrs) {

        // initialize buttons
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        mButton1 = (Button) findViewById(R.id.button_1);
        mButton2 = (Button) findViewById(R.id.button_2);
        mButton3 = (Button) findViewById(R.id.button_3);
        mButton4 = (Button) findViewById(R.id.button_4);
        mButton5 = (Button) findViewById(R.id.button_5);
        mButton6 = (Button) findViewById(R.id.button_6);
        mButton7 = (Button) findViewById(R.id.button_7);
        mButton8 = (Button) findViewById(R.id.button_8);
        mButton9 = (Button) findViewById(R.id.button_9);
        mButton10 = (Button) findViewById(R.id.button_10);
        mButton11 = (Button) findViewById(R.id.button_11);
        mButton12 = (Button) findViewById(R.id.button_12);
        mButton13 = (Button) findViewById(R.id.button_13);
        mButton14 = (Button) findViewById(R.id.button_14);
        mButton15 = (Button) findViewById(R.id.button_15);
        mButton16 = (Button) findViewById(R.id.button_16);
        mButton17 = (Button) findViewById(R.id.button_17);
        mButton18 = (Button) findViewById(R.id.button_18);
        mButton19= (Button) findViewById(R.id.button_19);
        mButton20 = (Button) findViewById(R.id.button_20);
        mButton0 = (Button) findViewById(R.id.button_0);
        mButton25 = (Button) findViewById(R.id.button_25);
        mButton50 = (Button) findViewById(R.id.button_50);

        // set button click listeners
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);
        mButton9.setOnClickListener(this);
        mButton10.setOnClickListener(this);
        mButton11.setOnClickListener(this);
        mButton12.setOnClickListener(this);
        mButton13.setOnClickListener(this);
        mButton14.setOnClickListener(this);
        mButton15.setOnClickListener(this);
        mButton16.setOnClickListener(this);
        mButton17.setOnClickListener(this);
        mButton18.setOnClickListener(this);
        mButton19.setOnClickListener(this);
        mButton20.setOnClickListener(this);
        mButton0.setOnClickListener(this);
        mButton25.setOnClickListener(this);
        mButton50.setOnClickListener(this);

        // map buttons IDs to input strings
        keyValues.put(R.id.button_1, "1");
        keyValues.put(R.id.button_2, "2");
        keyValues.put(R.id.button_3, "3");
        keyValues.put(R.id.button_4, "4");
        keyValues.put(R.id.button_5, "5");
        keyValues.put(R.id.button_6, "6");
        keyValues.put(R.id.button_7, "7");
        keyValues.put(R.id.button_8, "8");
        keyValues.put(R.id.button_9, "9");
        keyValues.put(R.id.button_10, "10");
        keyValues.put(R.id.button_11, "11");
        keyValues.put(R.id.button_12, "12");
        keyValues.put(R.id.button_13, "13");
        keyValues.put(R.id.button_14, "14");
        keyValues.put(R.id.button_15, "15");
        keyValues.put(R.id.button_16, "16");
        keyValues.put(R.id.button_17, "17");
        keyValues.put(R.id.button_18, "18");
        keyValues.put(R.id.button_19, "19");
        keyValues.put(R.id.button_20, "20");
        keyValues.put(R.id.button_0, "0");
        keyValues.put(R.id.button_25, "25");
        keyValues.put(R.id.button_50, "50");
    }

    @Override
    public void onClick(View v) {
        String value = keyValues.get(v.getId());
        if (view instanceof Button)
            ((Button)view).setText(value);
        findViewById(R.id.layout_keyboard).setVisibility(GONE);
    }

    public void setView(View v) {
        view = v;
    }
}
