package com.dingo.germanforkids.Usage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


public class PaintPotView extends View implements View.OnTouchListener {
    private final int DEFAULT_COLOR;
    private final int DEFAULT_DOT_SIZE;
    private float mOldX;
    private float mOldY;
    private Paint mPaint;
    private ArrayList<Paint> mPaints;
    private Path mPath;
    private ArrayList<Path> mPaths;
    private int mPenColor;
    private float mX;
    private float mY;

    public PaintPotView(Context context) {
        super(context);
        this.DEFAULT_DOT_SIZE = 35;
        this.DEFAULT_COLOR = -16776961;
        init(-65536);
    }

    public PaintPotView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.DEFAULT_DOT_SIZE = 35;
        this.DEFAULT_COLOR = -16776961;
        init(-65536);
    }

    public PaintPotView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.DEFAULT_DOT_SIZE = 35;
        this.DEFAULT_COLOR = -16776961;
        init(-65536);
    }

    private void init(int i) {
        mPenColor = i;
        mPath = new Path();
        mPaths = new ArrayList<>();
        mPaints = new ArrayList<>();
        addPath(false);
        mOldY = 0.0f;
        mOldX = 0.0f;
        mY = 0.0f;
        mX = 0.0f;
        setOnTouchListener(this);
    }

    private void addPath(boolean z) {
        Path path = new Path();
        mPath = path;
        mPaths.add(path);
        Paint paint = new Paint();
        mPaint = paint;
        mPaints.add(paint);
        mPaint.setColor(mPenColor);
        if (!z) {
            mPaint.setStyle(Paint.Style.STROKE);
        }
        mPaint.setStrokeWidth(35.0f);
    }

    public int getPenColor() {
        return mPenColor;
    }

    public void setPenColor(int i) {
        mPenColor = i;
    }

    public void reset() {
        init(mPenColor);
        invalidate();
    }

    @Override 
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mPaths.size(); i++) {
            canvas.drawPath(mPaths.get(i), mPaints.get(i));
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        mX = motionEvent.getX();
        mY = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            addPath(true);
            mPath.addCircle(mX, mY, 17.0f, Path.Direction.CW);
            addPath(false);
            mPath.moveTo(mX, mY);
        } else if (action == 1) {
            addPath(true);
            float f = mOldX;
            float f2 = mX;
            if (f == f2) {
                float f3 = mOldY;
                float f4 = mY;
                if (f3 == f4) {
                    mPath.addCircle(f2, f4, 17.0f, Path.Direction.CW);
                }
            }
        } else if (action == 2) {
            mPath.lineTo(mX, mY);
        }
        invalidate();
        mOldX = mX;
        mOldY = mY;
        return true;
    }
}
