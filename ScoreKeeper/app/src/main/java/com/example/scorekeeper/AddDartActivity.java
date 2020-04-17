package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddDartActivity extends AppCompatActivity {
    public final static String POINTS = "com.example.scorekeeper.POINTS";
    Button buttonCurrScore;
    int currScore = 0;
    int currMultiplier = 1;
    int resultScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dart);

        buttonCurrScore = findViewById(R.id.currScore);
        buttonCurrScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                currScore = Integer.parseInt(s.toString());
                calcSinglePoints();
            }
        });

        RadioGroup rg_multiplier = findViewById(R.id.rg_multiplier);
        rg_multiplier.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        currMultiplier = 1;
                        break;
                    case R.id.rb_2:
                        currMultiplier = 2;
                        break;
                    case R.id.rb_3:
                        currMultiplier = 3;
                        break;
                }
                calcSinglePoints();
            }
        });
    }

    private void calcSinglePoints() {
        if ((currScore == 25 || currScore == 50) && currMultiplier != 1)
            ((RadioButton) findViewById(R.id.rb_1)).setChecked(true);
        else {
            resultScore = currScore * currMultiplier;
            ((TextView)findViewById(R.id.result_score)).setText(String.valueOf(resultScore));
        }
    }

    public void onClickSetPoint(View view) {
        MyKeyboard keyboard = findViewById(R.id.keyboard);
        keyboard.setView(buttonCurrScore);

        LinearLayout layoutBoard = findViewById(R.id.layout_keyboard);
        layoutBoard.setVisibility(View.VISIBLE);
    }

    public void addDartPoints(View view) {
        Intent answerIntent = new Intent();

        // put data for MainActivity (key, result)
        if (currScore == 50 || currMultiplier == 2)
            answerIntent.putExtra("FINISH", true);
        else
            answerIntent.putExtra("FINISH", false);
        answerIntent.putExtra("POINTS", resultScore);

        setResult(RESULT_OK, answerIntent);
        finish();
    }

    public void hideKeyboard(View view) {
        LinearLayout layoutBoard = findViewById(R.id.layout_keyboard);
        layoutBoard.setVisibility(View.GONE);
    }
}
