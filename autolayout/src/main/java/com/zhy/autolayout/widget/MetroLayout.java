package com.zhy.autolayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.AutoLayoutInfo;
import com.zhy.autolayout.R;
import com.zhy.autolayout.utils.AutoLayoutHelper;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhy on 15/12/10.
 *
 * //do not use
 */
public class MetroLayout extends ViewGroup
{

    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    private static class MetroBlock
    {
        int left;
        int top;
        int width;
    }

    private List<MetroBlock> mAvailablePos = new ArrayList<>();
    private int mDivider;

    public MetroLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MetroLayout);
        mDivider = a.getDimensionPixelOffset(R.styleable.MetroLayout_metro_divider, 0);
        mDivider = AutoUtils.getPercentWidthSizeBigger(mDivider);
        a.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        if (true)
            randomColor();

        if (!isInEditMode())
            mHelper.adjustChildren();

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private void randomColor()
    {
        Random r = new Random(255);

        for (int i = 0, n = getChildCount(); i < n; i++)
        {
            View v = getChildAt(i);

            v.setBackgroundColor(Color.argb(100, r.nextInt(), r.nextInt(), r.nextInt()));
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {

        initAvailablePosition();

        int left = 0;
        int top = 0;
        int divider = mDivider;

        for (int i = 0, n = getChildCount(); i < n; i++)
        {
            View v = getChildAt(i);
            if (v.getVisibility() == View.GONE) continue;

            MetroBlock newPos = findAvailablePos(v);
            left = newPos.left;
            top = newPos.top;

            int childWidth = v.getMeasuredWidth();
            int childHeight = v.getMeasuredHeight();

            int right = left + childWidth;
            int bottom = top + childHeight;

            v.layout(left, top, right, bottom);

            if (childWidth + divider < newPos.width)
            {
                newPos.left += childWidth + divider;
                newPos.width -= childWidth + divider;
            } else
            {
                mAvailablePos.remove(newPos);
            }

            MetroBlock p = new MetroBlock();
            p.left = left;
            p.top = bottom + divider;
            p.width = childWidth;
            mAvailablePos.add(p);

            mergeAvailablePosition();

        }
    }

    private void mergeAvailablePosition()
    {
        if (mAvailablePos.size() <= 1) return;

        List<MetroBlock> needRemoveBlocks = new ArrayList<>();

        MetroBlock one = mAvailablePos.get(0);
        MetroBlock two = mAvailablePos.get(1);

        for (int i = 1, n = mAvailablePos.size(); i < n - 1; i++)
        {
            if (one.top == two.top)
            {
                one.width = one.width + two.width;
                needRemoveBlocks.add(one);
                two.left = one.left;
                two = mAvailablePos.get(i + 1);
            } else
            {
                one = mAvailablePos.get(i);
                two = mAvailablePos.get(i + 1);
            }
        }

        mAvailablePos.removeAll(needRemoveBlocks);

    }

    private void initAvailablePosition()
    {
        mAvailablePos.clear();
        MetroBlock first = new MetroBlock();
        first.left = getPaddingLeft();
        first.top = getPaddingTop();
        first.width = getMeasuredWidth();
        mAvailablePos.add(first);
    }

    private MetroBlock findAvailablePos(View view)
    {
        MetroBlock p = new MetroBlock();
        if (mAvailablePos.size() == 0)
        {
            p.left = getPaddingLeft();
            p.top = getPaddingTop();
            p.width = getMeasuredWidth();
            return p;
        }
        int min = mAvailablePos.get(0).top;
        MetroBlock minHeightPos = mAvailablePos.get(0);
        for (MetroBlock _p : mAvailablePos)
        {
            if (_p.top < min)
            {
                min = _p.top;
                minHeightPos = _p;
            }
        }
        return minHeightPos;
    }


    @Override
    public MetroLayout.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams
            implements AutoLayoutHelper.AutoLayoutParams
    {
        private AutoLayoutInfo mAutoLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs)
        {
            super(c, attrs);
            mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
        }

        public LayoutParams(int width, int height)
        {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source)
        {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source)
        {
            super(source);
        }

        public LayoutParams(LayoutParams source)
        {
            this((ViewGroup.LayoutParams) source);
            mAutoLayoutInfo = source.mAutoLayoutInfo;
        }

        @Override
        public AutoLayoutInfo getAutoLayoutInfo()
        {
            return mAutoLayoutInfo;
        }


    }

}
