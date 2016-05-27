package com.vivam.circleprogressbar.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.vivam.circleprogressbar.R;

/**
 * 圆形进度条
 */
public class CircleProgressBar extends View {

    private static final int DEFAULT_MAX = 100;
    private static final int DEFAULT_THICKNESS = 5;
    private static final int DEFAULT_PROGRESS_BACKGROUND = 0xffbdbdbd;
    private static final int DEFAULT_PROGRESS_COLOR = 0xff00695c;
    private static final int DEFAULT_SECONDARY_PROGRESS_COLOR = 0xffb2dfdb;

    private int mProgressBackground;
    private int mProgressColor;
    private int mSecondaryProgressColor;

    private int mThickness;

    private int mMax;
    private int mProgress;
    private int mSecondaryProgress;

    private Paint mBgPaint = new Paint();
    private Paint mProgressPaint = new Paint();
    private Paint mSecondaryPaint = new Paint();

    private RectF mRect = new RectF();

    private boolean mNeedUpdateBound = false;

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.CircleProgressBar, defStyleAttr, 0);

        mProgressBackground = a.getColor(
                R.styleable.CircleProgressBar_progressBackground, DEFAULT_PROGRESS_BACKGROUND);

        mProgressColor = a.getColor(
                R.styleable.CircleProgressBar_progressColor, DEFAULT_PROGRESS_COLOR);

        mSecondaryProgressColor = a.getColor(
                R.styleable.CircleProgressBar_secondaryProgressColor,
                DEFAULT_SECONDARY_PROGRESS_COLOR);

        mThickness = a.getInt(R.styleable.CircleProgressBar_android_thickness, DEFAULT_THICKNESS);

        setMax(a.getInt(R.styleable.CircleProgressBar_android_max, DEFAULT_MAX));
        setProgress(a.getInt(R.styleable.CircleProgressBar_android_progress, 0));
        setSecondaryProgress(a.getInt(R.styleable.CircleProgressBar_android_secondaryProgress, 0));

        a.recycle();

        setBound();
        setPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mNeedUpdateBound) {
            setBound();
        }

        canvas.drawArc(mRect, 360, 360, false, mBgPaint);

        if (mSecondaryProgress > 0) {
            canvas.drawArc(mRect, -90, mSecondaryProgress * 360 / mMax, false, mSecondaryPaint);
        }

        if (mProgress > 0) {
            canvas.drawArc(mRect, -90, mProgress * 360 / mMax, false, mProgressPaint);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mNeedUpdateBound = true;
    }

    private void setPaint() {
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStrokeWidth(mThickness);
        mBgPaint.setStyle(Paint.Style.STROKE);
        mBgPaint.setColor(mProgressBackground);

        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStrokeWidth(mThickness);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setColor(mProgressColor);

        mSecondaryPaint.setAntiAlias(true);
        mSecondaryPaint.setStrokeWidth(mThickness);
        mSecondaryPaint.setStyle(Paint.Style.STROKE);
        mSecondaryPaint.setColor(mSecondaryProgressColor);
    }

    private void setBound() {
        mRect.left = getPaddingLeft() + mThickness;
        mRect.top = getPaddingTop() + mThickness;
        mRect.right = getWidth() - getPaddingRight() - mThickness;
        mRect.bottom = getHeight() - getPaddingBottom() - mThickness;
        mNeedUpdateBound = false;
    }

    public int getMax() {
        return mMax;
    }

    public void setMax(int max) {
        if (mMax != max) {
            mMax = max;
            postInvalidate();
        }
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        }

        if (progress > mMax) {
            progress = mMax;
        }

        if (mProgress != progress) {
            mProgress = progress;
            postInvalidate();
        }
    }

    public int getSecondaryProgress() {
        return mSecondaryProgress;
    }

    public void setSecondaryProgress(int secondaryProgress) {
        if (secondaryProgress < 0) {
            secondaryProgress = 0;
        }

        if (secondaryProgress > mMax) {
            secondaryProgress = mMax;
        }

        if (mSecondaryProgress != secondaryProgress) {
            mSecondaryProgress = secondaryProgress;
            postInvalidate();
        }
    }

    public void setProgressBackground(int color) {
        if (mProgressBackground != color) {
            mProgressBackground = color;
            postInvalidate();
        }
    }

    public void setProgressColor(int color) {
        if (mProgressColor != color) {
            mProgressColor = color;
            postInvalidate();
        }
    }

    public void setSecondaryProgressColor(int color) {
        if (mSecondaryProgressColor != color) {
            mSecondaryProgressColor = color;
            postInvalidate();
        }
    }
}
