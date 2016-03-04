package com.zhy.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhy on 15/12/5.
 */
public class MarginBottomAttr extends AutoAttr
{
    public MarginBottomAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return Attrs.MARGIN_BOTTOM;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return false;
    }

    @Override
    protected void execute(View view, int val)
    {
        if(!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
        {
            return ;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.bottomMargin = val;
    }

    public static MarginBottomAttr generate(int val, int baseFlag)
    {
        MarginBottomAttr attr = null;
        switch (baseFlag)
        {
            case AutoAttr.BASE_WIDTH:
                attr = new MarginBottomAttr(val, Attrs.MARGIN_BOTTOM, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                attr = new MarginBottomAttr(val, 0, Attrs.MARGIN_BOTTOM);
                break;
            case AutoAttr.BASE_DEFAULT:
                attr = new MarginBottomAttr(val, 0, 0);
                break;
        }
        return attr;
    }
}
