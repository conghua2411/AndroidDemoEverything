package com.example.leclevietnam.demoeverything.camera2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

public class AutoFixTextureView extends TextureView {

    private int mRatioWidth = 0;
    private int mRatioHeight = 0;

    public AutoFixTextureView(Context context) {
        super(context);
    }

    public AutoFixTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoFixTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAspectRatio(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Size cannot be nagative");
        }

        mRatioWidth = width;
        mRatioHeight = height;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if(0 == mRatioHeight || 0 == mRatioWidth) {
            setMeasuredDimension(width, height);
        } else {
            if (width < height * mRatioWidth / mRatioHeight) {
                setMeasuredDimension(width, width * mRatioHeight / mRatioWidth);
//                setMeasuredDimension(height * mRatioWidth / mRatioHeight, height);
            } else {
                setMeasuredDimension(height * mRatioWidth / mRatioHeight, height);
//                setMeasuredDimension(width, width * mRatioHeight / mRatioWidth);
            }
        }
    }
}
