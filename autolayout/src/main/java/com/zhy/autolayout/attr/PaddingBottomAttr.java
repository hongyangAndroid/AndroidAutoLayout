package com.zhy.autolayout.attr;

import android.view.View;

/**
 * Created by zhy on 15/12/5.
 */
public class PaddingBottomAttr extends AutoAttr
{
    public PaddingBottomAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return Attrs.PADDING_BOTTOM;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return false;
    }

    @Override
    protected void execute(View view, int val)
    {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = val;
        view.setPadding(l, t, r, b);

    }
}
