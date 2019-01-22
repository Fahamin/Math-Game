package app.cave.mathgame;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView numberOne, numberTWO, scoreDIV, result, timerTV, singID;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    Button ansOne, ansTwo, ansThree, ansFour;
    int score;
    private CountDownTimer countDownTimer;
    int numberofQuestion;
    int locationOffCorrectanswer;
    boolean isCounterRunning = false;
    Random rand = new Random();

    MediaPlayer soundYes;

    int sing = 0;

    AdView adView;
    @SuppressLint("WrongViewCast")
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        result = findViewById(R.id.reslutTV);
        timerTV = findViewById(R.id.timeID);
        scoreDIV = findViewById(R.id.dividerMath);
        numberOne = findViewById(R.id.numberOne);
        numberTWO = findViewById(R.id.numberTwo);
        ansFour = findViewById(R.id.anseFour);
        ansOne = findViewById(R.id.anserOne);
        ansTwo = findViewById(R.id.anserTwo);
        ansThree = findViewById(R.id.anserThree);
        singID = findViewById(R.id.singID);
        generateAnswer();

       /* adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);*/

        soundYes = MediaPlayer.create(MainActivity.this, R.raw.yes);
        countDownTimer = new CountDownTimer(31000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTV.setText(String.valueOf(millisUntilFinished / 1000) + " S");
            }

            @Override
            public void onFinish() {
                isCounterRunning = false;
                timerTV.setText("0S");
                result.setText("your score is" + Integer.toString(score));// + " / " + Integer.toString(numberofQuestion));
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addID:
                singID.setText("+");
                generateAnswer();
                break;
            case R.id.mulID:
                sing = 3;
                singID.setText("x");

                mulGenerateAnser();

                break;
            case R.id.DivID:
                sing = 2;
                singID.setText("/");

                divGenerateAnswer();
                break;
            case R.id.subID:
                sing = 1;
                singID.setText("-");

                subGenerateAnswer();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void generateAnswer() {
        answer.clear();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        numberOne.setText(Integer.toString(a));
        numberTWO.setText(Integer.toString(b));

        locationOffCorrectanswer = rand.nextInt(4);

        int innCorrect;

        for (int i = 0; i < 4; i++) {
            if (i == locationOffCorrectanswer) {
                answer.add(a + b);
            } else {
                innCorrect = rand.nextInt(41);
                while (innCorrect == a + b) {
                    innCorrect = rand.nextInt(41);
                }
                answer.add(innCorrect);
            }
        }

        ansOne.setText(Integer.toString(answer.get(0)));
        ansTwo.setText(Integer.toString(answer.get(1)));
        ansThree.setText(Integer.toString(answer.get(2)));
        ansThree.setText(Integer.toString(answer.get(3)));

    }

    void subGenerateAnswer() {
        answer.clear();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        numberOne.setText(Integer.toString(a));
        numberTWO.setText(Integer.toString(b));

        locationOffCorrectanswer = rand.nextInt(4);

        int innCorrect;

        for (int i = 0; i < 4; i++) {
            if (i == locationOffCorrectanswer) {
                answer.add(a - b);
            } else {
                innCorrect = rand.nextInt(41);
                while (innCorrect == a - b) {
                    innCorrect = rand.nextInt(41);
                }
                answer.add(innCorrect);
            }
        }

        ansOne.setText(Integer.toString(answer.get(0)));
        ansTwo.setText(Integer.toString(answer.get(1)));
        ansThree.setText(Integer.toString(answer.get(2)));
        ansThree.setText(Integer.toString(answer.get(3)));

    }

    public void chooseAnswer(View view) {
        //soundYes.start();

//        countDownTimer.onFinish();
        // timeGenerate();

        if (!isCounterRunning) {
            isCounterRunning = true;
            countDownTimer.start();
        } else {
            countDownTimer.cancel(); // cancel
            countDownTimer.start();  // then restart
        }


/*
        Log.d("kk", ansOne.getText().toString());
        Log.d("kk", ansTwo.getText().toString());
        Log.d("kk", ansThree.getText().toString());
        Log.d("kk", ansFour.getText().toString());
        Log.d("kkr", String.valueOf(answer.get(locationOffCorrectanswer)));*/

        if (view.getId() == R.id.anserOne) {
            String s1 = ansOne.getText().toString();
            if (s1.equals(String.valueOf(answer.get(locationOffCorrectanswer)))) {
                score+=100;
                soundYes.start();
                result.setText(" Correct");
            } else {
                result.setText(" incorrect");

            }
        }
        if (view.getId() == R.id.anserTwo) {
            String s1 = ansTwo.getText().toString();
            if (s1.equals(String.valueOf(answer.get(locationOffCorrectanswer)))) {
                score+=100;
                soundYes.start();
                result.setText(" Correct");
            } else {
                result.setText(" incorrect");

            }

        }
        if (view.getId() == R.id.anserThree) {
            String s1 = ansThree.getText().toString();
            if (s1.equals(String.valueOf(answer.get(locationOffCorrectanswer)))) {
                score+=100;
                soundYes.start();

                result.setText(" Correct");
            } else {
                result.setText(" incorrect");

            }

        }
        if (view.getId() == R.id.anseFour) {
            String s1 = ansFour.getText().toString();
            if (s1.equals(String.valueOf(answer.get(locationOffCorrectanswer)))) {
                soundYes.start();

                score+=100;
                result.setText(" Correct");
            } else {
                result.setText(" incorrect");

            }

        }
        numberofQuestion++;
        scoreDIV.setText(Integer.toString(score));// + " / " + Integer.toString(numberofQuestion));


        if (sing == 0) {
            generateAnswer();
        }
        if (sing == 1) {
            subGenerateAnswer();
        }
        if (sing == 2) {
            divGenerateAnswer();
        }
        if (sing == 3) {
            mulGenerateAnser();
        }

    }

    private void mulGenerateAnser() {
        answer.clear();
        int a = rand.nextInt(9);
        int b = rand.nextInt(9);
        numberOne.setText(Integer.toString(a));
        numberTWO.setText(Integer.toString(b));
        locationOffCorrectanswer = rand.nextInt(4);

        int innCorrect;

        for (int i = 0; i < 4; i++) {
            if (i == locationOffCorrectanswer) {
                answer.add(a * b);
            } else {
                innCorrect = rand.nextInt(91);
                while (innCorrect == a * b) {
                    innCorrect = rand.nextInt(91);
                }
                answer.add(innCorrect);
            }
        }

        ansOne.setText(Integer.toString(answer.get(0)));
        ansTwo.setText(Integer.toString(answer.get(1)));
        ansThree.setText(Integer.toString(answer.get(2)));
        ansThree.setText(Integer.toString(answer.get(3)));

    }

    private void divGenerateAnswer() {
        answer.clear();
        int a = rand.nextInt(21);
        int b = rand.nextInt(10);
        numberOne.setText(Integer.toString(a));
        numberTWO.setText(Integer.toString(b));

        locationOffCorrectanswer = rand.nextInt(4);

        int innCorrect;

        for (int i = 0; i < 4; i++) {
            if (i == locationOffCorrectanswer) {
                answer.add(a / b);
            } else {
                innCorrect = rand.nextInt(10);
                while (innCorrect == a / b) {
                    innCorrect = rand.nextInt(10);
                }
                answer.add(innCorrect);
            }
        }

        ansOne.setText(Integer.toString(answer.get(0)));
        ansTwo.setText(Integer.toString(answer.get(1)));
        ansThree.setText(Integer.toString(answer.get(2)));
        ansThree.setText(Integer.toString(answer.get(3)));

    }
}
