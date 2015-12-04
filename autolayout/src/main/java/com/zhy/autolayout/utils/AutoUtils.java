package com.zhy.autolayout.utils;

import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by zhy on 15/12/4.
 */
public class AutoUtils
{

    /**
     * 会直接将view的LayoutParams上设置的width，height直接进行百分比处理
     *
     * @param view
     */
    public static void auto(View view)
    {
        autoSize(view);
        autoPadding(view);
        autoMargin(view);
    }

    public static void autoMargin(View view)
    {

        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
            return;

        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.leftMargin = getPercentWidthSize(lp.leftMargin);
        lp.topMargin = getPercentHeightSize(lp.topMargin);
        lp.rightMargin = getPercentWidthSize(lp.rightMargin);
        lp.bottomMargin = getPercentHeightSize(lp.bottomMargin);

    }

    public static void autoPadding(View view)
    {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();

        l = getPercentWidthSize(l);
        t = getPercentHeightSize(t);
        r = getPercentWidthSize(r);
        b = getPercentHeightSize(b);

        view.setPadding(l, t, r, b);
    }

    public static void autoSize(View view)
    {
        ViewGroup.LayoutParams lp = view.getLayoutParams();

        int screenWidth = AutoLayoutConifg.getInstance().getScreenWidth();
        int screenHeight = AutoLayoutConifg.getInstance().getScreenHeight();

        int designWidth = AutoLayoutConifg.getInstance().getDesignWidth();
        int designHeight = AutoLayoutConifg.getInstance().getDesignHeight();

        lp.width = (int) (lp.width * 1.0f / designWidth * screenWidth);
        lp.height = (int) (lp.height * 1.0f / designHeight / screenHeight);

    }

    public static int getPercentWidthSize(int val)
    {
        int screenWidth = AutoLayoutConifg.getInstance().getScreenWidth();
        int designWidth = AutoLayoutConifg.getInstance().getDesignWidth();

        return (int) (val * 1.0f / designWidth * screenWidth);
    }

    public static int getPercentHeightSize(int val)
    {
        int screenHeight = AutoLayoutConifg.getInstance().getScreenHeight();
        int designHeight = AutoLayoutConifg.getInstance().getDesignHeight();

        return (int) (val * 1.0f / designHeight * screenHeight);
    }
}
