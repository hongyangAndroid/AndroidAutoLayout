package com.zhy.autolayout.attr;

import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;


/**
 * Created by zhy on 15/12/4.
 */
public abstract class AutoAttr
{
    protected int pxVal;
    protected int baseWidth;
    protected int baseHeight;


    public AutoAttr(int pxVal, int baseWidth, int baseHeight)
    {
        this.pxVal = pxVal;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
    }

    public void apply(View view)
    {
        int val;
        if (useDefault())
        {
            val = defaultBaseWidth() ? getPercentWidthSize() : getPercentHeightSize();
        } else if (baseWidth())
        {
            val = getPercentWidthSize();
        } else
        {
            val = getPercentHeightSize();
        }
        execute(view, val);
    }

    protected int getPercentWidthSize()
    {
        return AutoUtils.getPercentWidthSize(pxVal);
    }

    protected int getPercentHeightSize()
    {
        return AutoUtils.getPercentHeightSize(pxVal);
    }


    protected boolean baseWidth()
    {
        return contains(baseWidth, attrVal());
    }

    protected boolean useDefault()
    {
        return contains(baseHeight, attrVal()) || contains(baseWidth, attrVal());
    }

    protected boolean contains(int baseVal, int flag)
    {
        return (baseVal & flag) != 0;
    }

    protected abstract int attrVal();

    protected abstract boolean defaultBaseWidth();

    protected abstract void execute(View view, int val);

    @Override
    public String toString()
    {
        return "AutoAttr{" +
                "pxVal=" + pxVal +
                ", baseWidth=" + baseWidth() +
                ", defaultBaseWidth=" + defaultBaseWidth() +
                '}';
    }
}
