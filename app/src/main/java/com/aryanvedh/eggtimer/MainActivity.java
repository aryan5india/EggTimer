package com.aryanvedh.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Boolean counterIsActive = false;
    Button controlbtn;
    CountDownTimer countDownTimer;

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;
        String minuteString = Integer.toString(minutes);
        String secondsString = Integer.toString(seconds);

//        if (secondsString.equals("0")) {
//            secondsString = "00";
//        }
        if (seconds <= 9) secondsString = "0" + seconds;
        if (minutes <= 9) minuteString = "0" + minutes;
        timerTextView.setText(minuteString + ":" + secondsString);

    }

    public void controlTimer(View view) {

        if (!counterIsActive) {

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controlbtn.setText("Stop");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000L, 1000) {

                @Override
                public void onTick(long millisecondsFinished) {
                    updateTimer((int) millisecondsFinished / 1000);

                }

                @Override
                public void onFinish() {
                    resetTimer();
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mPlayer.start();

                }
            }.start();
        } else {
            resetTimer();
        }
    }

    private void resetTimer() {
        timerTextView.setText("00:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        controlbtn.setText("GO");
        counterIsActive = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.seekBar2);
        timerTextView = findViewById(R.id.timerTextView);
        controlbtn = findViewById(R.id.controllerBtn);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}