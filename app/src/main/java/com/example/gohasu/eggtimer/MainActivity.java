package com.example.gohasu.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int currentTime;
    CountDownTimer countDownTimer;

    TextView timeShowing;
    SeekBar seekBar;
    Button goButton;

    Button stopButton;

    public void clickGoButton(View view) {
        timeShowing = (TextView) findViewById(R.id.textView2);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.airhorn);
        goButton = findViewById(R.id.goButton);

        stopButton = findViewById(R.id.stopButton);

        stopButton.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        seekBar.setEnabled(false);

        countDownTimer = new CountDownTimer(seekBar.getProgress(), 1000) {

            public void onTick(long millisecondsUntilDone) {
                updateTimer((int) millisecondsUntilDone);

             }

            public void onFinish() {
                mediaPlayer.start();
                stopButton.setVisibility(View.INVISIBLE);
                goButton.setVisibility(View.VISIBLE);
                updateTimer(30000);
            }
        }.start();

    }

    public void clickStopButton(View view) {

        stopButton.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);
        countDownTimer.cancel();
        seekBar.setProgress(30000);
        seekBar.setEnabled(true);
        updateTimer(30000);

    }

    public void updateTimer(int secondsLeft) {

        timeShowing.setText(String.format("%02d:%02d",secondsLeft/60000,(secondsLeft/1000) % 60 ));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeShowing = findViewById(R.id.textView2);

        seekBar = findViewById(R.id.seekBar3);

        currentTime = 30000;

        seekBar.setMax(300000);

        seekBar.setProgress(currentTime);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(i);

//                int minute = i/60;
//                int second = i -(minute * 60);
//
//                String secondString = Integer.toString(second);
//                if(secondString.equals("0")) {
//                    secondString = "00";
//                }
//
//                timeShowing.setText(Integer.toString(minute) + ":" + secondString);

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
