package com.zhy.autolayout.attr;

import android.view.View;

/**
 * Created by zhy on 15/12/4.
 */
public enum AutoAttrEnum
{
    TEXT_SIZE
            {
                @Override
                public void apply(View view)
                {

                }
            };

    private int pxVal ;

    public int getPxVal()
    {
        return pxVal;
    }

    public void setPxVal(int pxVal)
    {
        this.pxVal = pxVal;
    }

    public abstract void apply(View view);





}
