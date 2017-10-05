package com.example.uri.iungointerface.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;


import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.activities.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

/***
 * In this activity the app must look if the user is logged.
 * If it's logged it redirects the user to the app
 * Else it looks if is the first time the user uses the app.
 */
public class SplashActivity extends AppCompatActivity {
    private Timer timer;

    private ProgressBar progressBar;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialization of components
        progressBar = (ProgressBar) findViewById(R.id.splash_progressBar);
        progressBar.setProgress(0);

        final long period = 33;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //this repeats every 100 ms
                if (i < 100) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                    progressBar.setProgress(i);
                    i++;
                } else {
                    //closing the timer
                    timer.cancel();
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 0, period);
    }
}
