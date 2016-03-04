package com.zhy.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhy on 15/12/5.
 */
public class WidthAttr extends AutoAttr
{
    public WidthAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return Attrs.WIDTH;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return true;
    }

    @Override
    protected void execute(View view, int val)
    {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = val;
    }

    public static WidthAttr generate(int val, int baseFlag)
    {
        WidthAttr widthAttr = null;
        switch (baseFlag)
        {
            case AutoAttr.BASE_WIDTH:
                widthAttr = new WidthAttr(val, Attrs.WIDTH, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                widthAttr = new WidthAttr(val, 0, Attrs.WIDTH);
                break;
            case AutoAttr.BASE_DEFAULT:
                widthAttr = new WidthAttr(val, 0, 0);
                break;
        }
        return widthAttr;
    }

}
