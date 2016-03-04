package com.zhy.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhy on 15/12/5.
 */
public class MarginAttr extends AutoAttr
{
    public MarginAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return Attrs.MARGIN;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return false;
    }

    @Override
    public void apply(View view)
    {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
        {
            return;
        }
        if (useDefault())
        {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            lp.leftMargin = lp.rightMargin = getPercentWidthSize();
            lp.topMargin = lp.bottomMargin = getPercentHeightSize();
            return;
        }
        super.apply(view);
    }

    @Override
    protected void execute(View view, int val)
    {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.leftMargin = lp.rightMargin = lp.topMargin = lp.bottomMargin = val;
    }

}
