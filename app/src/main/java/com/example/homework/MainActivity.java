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
    private Handler handler;
    private Runnable runnable;
    private int count = 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        startButton = findViewById(R.id.button);
        stopButton = findViewById(R.id.button2);
        handler = new Handler();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    isRunning = true;
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            count++;
                            textView.setText(String.valueOf(count));
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
    }
}