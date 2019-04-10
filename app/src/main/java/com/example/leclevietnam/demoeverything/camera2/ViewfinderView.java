package com.example.leclevietnam.demoeverything.camera2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ViewfinderView extends View {

    RectF finderRect;

    Paint mPaint;

    int markColor;

    RectF previewRect;

    RectF textureRect;

    public ViewfinderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        markColor = Color.parseColor("#60000000");

        previewRect = new RectF();

        finderRect = new RectF();

        textureRect = new RectF();
    }

    public void setPreviewRect(RectF rect) {
        previewRect = rect;
    }

    public void setFinderRect(RectF rect) {
        finderRect = rect;
    }

    public void setTextureRect(RectF rect) {
        textureRect = rect;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (previewRect == null || finderRect == null) {
            return;
        }

        mPaint.setColor(markColor);

        float width = canvas.getWidth();
        float height = canvas.getHeight();

        float realFinderWidth = finderRect.width() * (textureRect.width() / previewRect.width());
        float realFinderHeight = finderRect.height() * (textureRect.height() / previewRect.height());

        float realFinderTop = (height/4 - realFinderHeight/2 + (height/4 - realFinderHeight/2) / height * textureRect.height())/2;
        float realFinderLeft = (width - realFinderWidth)/2;

        canvas.drawRect(realFinderLeft, realFinderTop, realFinderLeft + realFinderWidth, realFinderTop + realFinderHeight, mPaint);

        Log.d("drawFinderView", "canvas -- width : " + width + " -- height : " + height);
        Log.d("drawFinderView", "textureRect : left: " + textureRect.left + " -- top : " + textureRect.top + " -- width : " + textureRect.width() + " -- height : " + textureRect.height());
        Log.d("drawFinderView", "previewRect: left: " + previewRect.left + " -- top : " + previewRect.top + " -- width : " + previewRect.width() + " -- height : " + previewRect.height());
        Log.d("drawFinderView", "finderRect: left: " + finderRect.left + " -- top : " + finderRect.top + " -- width : " + finderRect.width() + " -- height : " + finderRect.height());
        Log.d("drawFinderView", "real rect left: " + realFinderLeft + " -- top : " + realFinderTop + " -- width : " + realFinderWidth + " -- height : " + realFinderHeight);

    }
}
