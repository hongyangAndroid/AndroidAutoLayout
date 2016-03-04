package com.zhy.autolayout.attr;

import android.view.View;

/**
 * Created by zhy on 15/12/5.
 */
public class PaddingRightAttr extends AutoAttr
{
    public PaddingRightAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return Attrs.PADDING_RIGHT;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return true;
    }

    @Override
    protected void execute(View view, int val)
    {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = val;
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);

    }


    public static PaddingRightAttr generate(int val, int baseFlag)
    {
        PaddingRightAttr attr = null;
        switch (baseFlag)
        {
            case AutoAttr.BASE_WIDTH:
                attr = new PaddingRightAttr(val, Attrs.PADDING_RIGHT, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                attr = new PaddingRightAttr(val, 0, Attrs.PADDING_RIGHT);
                break;
            case AutoAttr.BASE_DEFAULT:
                attr = new PaddingRightAttr(val, 0, 0);
                break;
        }
        return attr;
    }
}
