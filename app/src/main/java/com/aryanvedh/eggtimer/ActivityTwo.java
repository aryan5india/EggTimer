package com.aryanvedh.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    TextView timerTextV;
    SeekBar mySeekbar;
    Boolean counterIsActive = false;
    Button controllerButton;
    CountDownTimer countDownTimer;

    public void resetTimer() {
        timerTextV.setText("00:30");
        controllerButton.setText("Go");
        mySeekbar.setEnabled(true);
        mySeekbar.setProgress(30);
        counterIsActive = false;
        countDownTimer.cancel();

    }

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String minutesString = Integer.toString(minutes);
        String secondsString = Integer.toString(seconds);

        if (minutes <= 9) minutesString = "0" + minutes;
        if (seconds <= 9) secondsString = "0" + seconds;

        timerTextV.setText(minutesString + ":" + secondsString);

    }

    public void goAndStop(View view) {
        if (!counterIsActive) {

            counterIsActive = true;
            mySeekbar.setEnabled(false);
            controllerButton.setText("Stop");

            countDownTimer = new CountDownTimer(mySeekbar.getProgress() * 1000L, 1000) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        timerTextV = findViewById(R.id.timerText);
        controllerButton = findViewById(R.id.controllerButton);

        mySeekbar = findViewById(R.id.seekBarNew);
        mySeekbar.setMax(600);
        mySeekbar.setProgress(30);

        mySeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

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