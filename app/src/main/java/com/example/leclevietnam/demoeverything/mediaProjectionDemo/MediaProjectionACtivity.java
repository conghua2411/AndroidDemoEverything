package com.example.leclevietnam.demoeverything.mediaProjectionDemo;

import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.leclevietnam.demoeverything.R;

public class MediaProjectionACtivity extends AppCompatActivity {

    private static int PERMISSION_RECORD = 123;
    private MediaProjectionManager mMediaProjectionManager;
    private MediaProjection mMediaProjection;
    private VirtualDisplay mVirtualDisplay;
    private SurfaceView surfaceView;
    private Button btnStart;
    private boolean running = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_projection_activity);

        mMediaProjectionManager = (MediaProjectionManager) getSystemService(
                Context.MEDIA_PROJECTION_SERVICE
        );

        surfaceView = findViewById(R.id.svPreview);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!running) {
                    startActivityForResult(
                            mMediaProjectionManager.createScreenCaptureIntent(),
                            PERMISSION_RECORD
                    );
                } else {
                    stopVirtualDisplay();
                }
                running = !running;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != PERMISSION_RECORD) {
            return;
        }
        if (resultCode != RESULT_OK) {
            Toast.makeText(this,
                    "Screen Cast Permission Denied", Toast.LENGTH_SHORT).show();
            return;
        }
        mMediaProjection = mMediaProjectionManager.getMediaProjection(resultCode, data);
        mMediaProjection.registerCallback(new MediaProjection.Callback() {
            @Override
            public void onStop() {
                stopVirtualDisplay();
            }
        }, null);
        mVirtualDisplay = createVirtualDisplay();
    }

    private void stopVirtualDisplay() {
        if (mVirtualDisplay == null) {
            return;
        }
        mVirtualDisplay.release();
    }

    private VirtualDisplay createVirtualDisplay() {
        return mMediaProjection.createVirtualDisplay("MainActivity",
                surfaceView.getWidth(), surfaceView.getHeight(), 1,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                surfaceView.getHolder().getSurface(), null /*Callbacks*/, null /*Handler*/);
    }
}
