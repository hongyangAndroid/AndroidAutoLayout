package com.zhy.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhy on 15/12/5.
 */
public class MarginTopAttr extends AutoAttr
{
    public MarginTopAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return Attrs.MARGIN_TOP;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return false;
    }

    @Override
    protected void execute(View view, int val)
    {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
        {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.topMargin = val;

    }


    public static MarginTopAttr generate(int val, int baseFlag)
    {
        MarginTopAttr attr = null;
        switch (baseFlag)
        {
            case AutoAttr.BASE_WIDTH:
                attr = new MarginTopAttr(val, Attrs.MARGIN_TOP, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                attr = new MarginTopAttr(val, 0, Attrs.MARGIN_TOP);
                break;
            case AutoAttr.BASE_DEFAULT:
                attr = new MarginTopAttr(val, 0, 0);
                break;
        }
        return attr;
    }
}
