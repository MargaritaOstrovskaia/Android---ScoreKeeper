    package com.example.scorekeeper;

    import androidx.appcompat.app.AppCompatActivity;

    import android.app.AlertDialog;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.os.Bundle;
    import android.text.Editable;
    import android.text.InputType;
    import android.text.TextWatcher;
    import android.view.View;
    import android.view.inputmethod.EditorInfo;
    import android.view.inputmethod.InputConnection;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.LinearLayout;
    import android.widget.RadioButton;
    import android.widget.RadioGroup;
    import android.widget.TextView;
    import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {
        static final private int POINTS = 1;
        boolean isFinish = false;

        int startScore = 301;
        int currPlayer = 1;
        int currDart = 0;

        int totalScoreA = 301;
        int roundPlayerAScore1 = 0;
        int roundPlayerAScore2 = 0;
        int roundPlayerAScore3 = 0;
        int roundPlayerATotalScore = 0;

        int totalScoreB = 301;
        int roundPlayerBScore1 = 0;
        int roundPlayerBScore2 = 0;
        int roundPlayerBScore3 = 0;
        int roundPlayerBTotalScore = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == POINTS)
                if (resultCode == RESULT_OK) {
                    int score = data.getIntExtra("POINTS", 0);
                    isFinish = data.getBooleanExtra("FINISH", false);

                    if (currPlayer == 1) {
                        switch (currDart) {
                            case 1:
                                roundPlayerAScore1 = score;
                                ((Button)findViewById(R.id.button_a_1)).setText(String.valueOf(roundPlayerAScore1));
                                break;
                            case 2:
                                roundPlayerAScore2 = score;
                                ((Button)findViewById(R.id.button_a_2)).setText(String.valueOf(roundPlayerAScore2));
                                break;
                            case 3:
                                roundPlayerAScore3 = score;
                                ((Button)findViewById(R.id.button_a_3)).setText(String.valueOf(roundPlayerAScore3));
                                break;
                        }
                        roundPlayerATotalScore = roundPlayerAScore1 + roundPlayerAScore2 + roundPlayerAScore3;
                        ((TextView)findViewById(R.id.button_a_sum)).setText(String.valueOf(roundPlayerATotalScore));
                    }
                    if (currPlayer == 2) {
                        switch (currDart) {
                            case 1:
                                roundPlayerBScore1 = score;
                                ((Button)findViewById(R.id.button_b_1)).setText(String.valueOf(roundPlayerBScore1));
                                break;
                            case 2:
                                roundPlayerBScore2 = score;
                                ((Button)findViewById(R.id.button_b_2)).setText(String.valueOf(roundPlayerBScore2));
                                break;
                            case 3:
                                roundPlayerBScore3 = score;
                                ((Button)findViewById(R.id.button_b_3)).setText(String.valueOf(roundPlayerBScore3));
                                break;
                        }
                        roundPlayerBTotalScore = roundPlayerBScore1 + roundPlayerBScore2 + roundPlayerBScore3;
                        ((TextView)findViewById(R.id.button_b_sum)).setText(String.valueOf(roundPlayerBTotalScore));
                    }
                }
        }

        public void setPlayerAPoint1(View view) {
            setPoints(1);
        }
        public void setPlayerAPoint2(View view) {
            setPoints(2);
        }
        public void setPlayerAPoint3(View view) {
            setPoints(3);
        }

        public void setPlayerBPoint1(View view) {
            setPoints(1);
        }
        public void setPlayerBPoint2(View view) {
            setPoints(2);
        }
        public void setPlayerBPoint3(View view) {
            setPoints(3);
        }

        private void setPoints(int dart) {
            currDart = dart;
            Intent intent = new Intent(MainActivity.this, AddDartActivity.class);
            startActivityForResult(intent, POINTS);
        }

        public void onClickResetAllData(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.sure);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    totalScoreA = startScore;
                    ((TextView)findViewById(R.id.total_score_a)).setText(String.valueOf(totalScoreA));

                    totalScoreB = startScore;
                    ((TextView)findViewById(R.id.total_score_b)).setText(String.valueOf(totalScoreB));

                    currPlayer = 1;
                    resetRoundScore(0);
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        private int calcTotalScore(int totalScore, int roundTotalScore) {
            String text = getResources().getString(R.string.info);
            TextView info = findViewById(R.id.textViewInfo);
            int result = totalScore - roundTotalScore;
            if (result > 1) {
                info.setText(text);
                return result;
            }
            if (currPlayer == 1)
                text = getResources().getString(R.string.player_a) + ": ";
            else
                text = getResources().getString(R.string.player_b) + ": ";
            if (result == 0) {
                if (isFinish) {
                    text += getResources().getString(R.string.win);
                    info.setText(text);
                    return 0;
                }
                else
                    text += getResources().getString(R.string.skip_round);
            }
            else
                text += getResources().getString(R.string.bust_score);

            info.setText(text);
            return totalScore;
        }

        public void onClickAddPlayerAPoints(View view) {
            totalScoreA = calcTotalScore(totalScoreA, roundPlayerATotalScore);
            ((TextView)findViewById(R.id.total_score_a)).setText(String.valueOf(totalScoreA));
            if (totalScoreA != 0) {
                resetRoundScore(1);
                currPlayer = 2;
            }
            else {
                setEnabledPlayerA(false);
                setEnabledPlayerB(false);
            }
        }

        public void onClickAddPlayerBPoints(View view) {
            totalScoreB = calcTotalScore(totalScoreB, roundPlayerBTotalScore);
            ((TextView)findViewById(R.id.total_score_b)).setText(String.valueOf(totalScoreB));
            if (totalScoreB != 0) {
                resetRoundScore(2);
                currPlayer = 1;
            }
            else {
                setEnabledPlayerA(false);
                setEnabledPlayerB(false);
            }
        }

        private void resetRoundScore(int player) {
            if (player == 0 || player == 1) {
                roundPlayerAScore1 = 0;
                roundPlayerAScore2 = 0;
                roundPlayerAScore3 = 0;
                roundPlayerATotalScore = 0;

                ((Button)findViewById(R.id.button_a_1)).setText(String.valueOf(roundPlayerAScore1));
                ((Button)findViewById(R.id.button_a_2)).setText(String.valueOf(roundPlayerAScore2));
                ((Button)findViewById(R.id.button_a_3)).setText(String.valueOf(roundPlayerAScore3));
                ((TextView) findViewById(R.id.button_a_sum)).setText(String.valueOf(roundPlayerATotalScore));

                if (player == 1) {
                    setEnabledPlayerA(false);
                    setEnabledPlayerB(true);
                }
            }
            if (player == 0 || player == 2) {
                roundPlayerBScore1 = 0;
                roundPlayerBScore2 = 0;
                roundPlayerBScore3 = 0;
                roundPlayerBTotalScore = 0;

                ((Button)findViewById(R.id.button_b_1)).setText(String.valueOf(roundPlayerBScore1));
                ((Button)findViewById(R.id.button_b_2)).setText(String.valueOf(roundPlayerBScore2));
                ((Button)findViewById(R.id.button_b_3)).setText(String.valueOf(roundPlayerBScore3));
                ((TextView) findViewById(R.id.button_b_sum)).setText(String.valueOf(roundPlayerBTotalScore));

                setEnabledPlayerA(true);
                setEnabledPlayerB(false);
            }
        }

        private void setEnabledPlayerA(boolean isEnabled) {
            findViewById(R.id.button_a_1).setEnabled(isEnabled);
            findViewById(R.id.button_a_2).setEnabled(isEnabled);
            findViewById(R.id.button_a_3).setEnabled(isEnabled);
            findViewById(R.id.button_add_points_a).setEnabled(isEnabled);
        }

        private void setEnabledPlayerB(boolean isEnabled) {
            findViewById(R.id.button_b_1).setEnabled(isEnabled);
            findViewById(R.id.button_b_2).setEnabled(isEnabled);
            findViewById(R.id.button_b_3).setEnabled(isEnabled);
            findViewById(R.id.button_add_points_b).setEnabled(isEnabled);
        }
    }