package com.zhy.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhy on 15/12/5.
 */
public class HeightAttr extends AutoAttr
{
    public HeightAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return Attrs.HEIGHT;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return false;
    }

    @Override
    protected void execute(View view, int val)
    {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = val;
    }

    public static HeightAttr generate(int val, int baseFlag)
    {
        HeightAttr heightAttr = null;
        switch (baseFlag)
        {
            case AutoAttr.BASE_WIDTH:
                heightAttr = new HeightAttr(val, Attrs.HEIGHT, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                heightAttr = new HeightAttr(val, 0, Attrs.HEIGHT);
                break;
            case AutoAttr.BASE_DEFAULT:
                heightAttr = new HeightAttr(val, 0, 0);
                break;
        }
        return heightAttr;
    }


}
