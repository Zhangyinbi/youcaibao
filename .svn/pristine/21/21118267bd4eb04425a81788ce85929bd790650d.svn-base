package com.youjuke.optimalmaterialtreasure.weights;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.youjuke.optimalmaterialtreasure.R;


/**
 * 支持滑动变色的View
 */
public class MyView extends View {

    private String mText;
    private float mTextSize;
    private Rect mTextBounds;
    private Paint mTextPaint;
    private int xText;
    private int yText;
    private int mAlpha;
    private int mTextColor;
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //从xml定义属性中拿到对应数值
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        int indexCount = ta.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = ta.getIndex(i);
            switch (index) {
                case R.styleable.MyView_text:
                    mText = ta.getString(index);
                    break;
                case R.styleable.MyView_textsize:
                    mTextSize = ta.getDimension(index, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.MyView_textColor:
                    mTextColor = ta.getColor(index, 0x333333);
                    break;
            }
        }
        //初始化字体画笔
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mTextBounds = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBounds);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //拿到测量宽高
        int width =  getMeasuredWidth();
        int height = getMeasuredHeight();
        //初始化bitmap的绘制区域
        xText = width / 2-mTextBounds.width()/2;
        yText=height/2 - (mTextBounds.bottom + mTextBounds.top)/2;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if (i!=1){
            drawText(canvas);
        }else {
            drawText1(canvas);
        }
    }

    private void drawText1(Canvas canvas) {
        mTextPaint.setColor(mTextColor);
        mTextPaint.setAlpha(255);
        canvas.drawText(mText, xText, yText, mTextPaint);
    }

    int i=0;
    private void drawText(Canvas canvas) {
        mTextPaint.setColor(0x333333);
        mTextPaint.setAlpha(255 - mAlpha);

        canvas.drawText(mText, xText, yText, mTextPaint);
        mTextPaint.setColor(0xee0005);
        mTextPaint.setAlpha(mAlpha);
        canvas.drawText(mText, xText, yText, mTextPaint);
    }
    public void setAlpha(float alpha) {
        int ceil = (int) Math.ceil(255 * alpha);
        mAlpha = ceil;
        invalidate();
    }
    public void setTextColor(int color){
        mTextColor=color;
        i=1;
        invalidate();
    }
}
