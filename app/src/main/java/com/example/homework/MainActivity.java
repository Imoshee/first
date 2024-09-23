package com.example.homework;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;

    private Handler handler;
    private Runnable runnable;

    private int seconds = 0;
    private int minutes = 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        startButton = findViewById(R.id.button);
        stopButton = findViewById(R.id.button2);
        resetButton = findViewById(R.id.button3);
        handler = new Handler();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    isRunning = true;
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            seconds++;
                            if (seconds == 60) {
                                seconds = 0;
                                minutes++;
                            }
                            updateTextView();
                            if (isRunning) {
                                handler.postDelayed(this, 1000);
                            }
                        }
                    };
                    handler.postDelayed(runnable, 1000);
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
                handler.removeCallbacks(runnable);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
                handler.removeCallbacks(runnable);
                seconds = 0;
                minutes = 0;
                updateTextView();
            }
        });
    }

    private void updateTextView() {
        String time = String.format("%02d:%02d", minutes, seconds);
        textView.setText(time);
    }
}