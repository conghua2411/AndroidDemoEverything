package com.example.leclevietnam.demoeverything.FilterScreenRecord;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraPreview1 extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;
    String filter_effect;

    public CameraPreview1(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // create the surface and start camera preview
            if (mCamera == null) {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();
            }
        } catch (IOException e) {
            Log.d(VIEW_LOG_TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    @SuppressWarnings("deprecation")
    public void refreshCamera(Camera camera,String effect) {
        filter_effect=effect;
        if (mHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }
        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }
        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
        setCamera(camera);
        try {
            Camera.Parameters params = mCamera.getParameters();
            if(filter_effect.equals("none"))
            {
                params.setColorEffect(Camera.Parameters.EFFECT_NONE);
            }
            else if(filter_effect.equals("mono"))
            {
                params.setColorEffect(Camera.Parameters.EFFECT_MONO);
            }
            else if(filter_effect.equals("negative"))
            {
                params.setColorEffect(Camera.Parameters.EFFECT_NEGATIVE);
            }
            else if(filter_effect.equals("solarize"))
            {
                params.setColorEffect(Camera.Parameters.EFFECT_SOLARIZE);
            }
            else if(filter_effect.equals("sepia"))
            {
                params.setColorEffect(Camera.Parameters.EFFECT_SEPIA);
            }
            else if(filter_effect.equals("posterize"))
            {
                params.setColorEffect(Camera.Parameters.EFFECT_POSTERIZE);
            }
            else if(filter_effect.equals("whiteboard"))
            {
                params.setColorEffect(Camera.Parameters.EFFECT_WHITEBOARD);
            }
            else if(filter_effect.equals("blackboard"))
            {
                params.setColorEffect(Camera.Parameters.EFFECT_BLACKBOARD);
            }
            else if(filter_effect.equals("aqua"))
            {
                params.setColorEffect(Camera.Parameters.EFFECT_AQUA);
            }
            mCamera.setParameters(params);
            mCamera.setPreviewDisplay(mHolder);

            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();
        } catch (Exception e) {
            Log.d(VIEW_LOG_TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

        refreshCamera(mCamera,filter_effect);
    }

    public void setCamera(Camera camera) {
        //method to set a camera instance
        mCamera = camera;
        Camera.Parameters params = camera.getParameters();
        if (params.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        } else {
            //Choose another supported mode
        }
        camera.setParameters(params);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        // mCamera.release();

    }
}