package com.example.leclevietnam.demoeverything.camera2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.leclevietnam.demoeverything.R;

public class Camera2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_2);

        Toolbar titleToolBar = findViewById(R.id.titleToolBar);
        setSupportActionBar(titleToolBar);
        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .replace(R.id.container, Camera2Preview.newInstance())
                    .commit();
        }
    }
}
