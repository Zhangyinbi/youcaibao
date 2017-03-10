package com.youjuke.library.weights;

import android.content.Context;
import android.util.AttributeSet;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * 描述: 轮播支持百分比布局
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-02-04 14:21
 */
public class AutoBanner extends ConvenientBanner {
    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoBanner(Context context) {
        super(context);
    }

    public AutoBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AutoBanner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public AutoLinearLayout.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new AutoLinearLayout.LayoutParams(getContext(), attrs);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode())
        {
            mHelper.adjustChildren();
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
