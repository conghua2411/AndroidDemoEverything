package com.example.leclevietnam.demoeverything.demoMuxer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.leclevietnam.demoeverything.R;

public class DemoMuxerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_muxer);

        findViewById(R.id.btnMuxer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muxer();
            }
        });
    }

    private void muxer() {
        String outputFile;
    }
}
