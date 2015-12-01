package com.zhy.autolayout;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by zhy on 15/11/18.
 */
public class AutoLayoutConifg
{
    private boolean mStatusBarAvailable;
    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";


    DisplayMetrics outMetrics = new DisplayMetrics();

    private int mAvailableWidth;
    private int mAvailaleHegiht;


    private static final String KEY_DESIGN_WIDTH = "design_width";
    private static final String KEY_DESIGN_HEIGHT = "design_height";

    private int mDesignWidth;
    private int mDesignHeight;


    public int getAvailableWidth()
    {
        return mAvailableWidth;
    }

    public int getAvailaleHeight()
    {
        return mAvailaleHegiht;
    }

    public int getDesignWidth()
    {
        return mDesignWidth;
    }

    public int getDesignHeight()
    {
        return mDesignHeight;
    }

    public void auto(Activity activity)
    {
        auto(activity, true);
    }


    public void auto(Activity activity, boolean ignoreStatusBar)
    {
        getMetaData(activity);

        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mAvailableWidth = outMetrics.widthPixels;
        mAvailaleHegiht = outMetrics.heightPixels;
        mAvailaleHegiht -= getStatusHeight(activity.getResources());

        if (ignoreStatusBar)
            return;

        checkStatusBar(activity);

        if (mStatusBarAvailable)
        {
            mAvailaleHegiht += getStatusHeight(activity.getResources());
        }

        L.e("mAvailableWidth =" + mAvailableWidth + " , mAvailaleHegiht = " + mAvailaleHegiht);
    }

    private void getMetaData(Context context)
    {
        if (mDesignWidth > 0 && mDesignHeight > 0) return;
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo;
        try
        {
            applicationInfo = packageManager.getApplicationInfo(context
                    .getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo != null && applicationInfo.metaData != null)
            {
                mDesignWidth = (int) applicationInfo.metaData.get(KEY_DESIGN_WIDTH);
                mDesignHeight = (int) applicationInfo.metaData.get(KEY_DESIGN_HEIGHT);
            }
        } catch (PackageManager.NameNotFoundException e)
        {
            throw new RuntimeException(
                    "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.", e);
        }

        L.e("mDesignWidth =" + mDesignWidth + " , mDesignHeight = " + mDesignHeight);
    }

    private void checkStatusBar(Activity activity)
    {
        Window win = activity.getWindow();
        ViewGroup decorViewGroup = (ViewGroup) win.getDecorView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            // check theme attrs
            int[] attrs = {android.R.attr.windowTranslucentStatus,
                    android.R.attr.windowTranslucentNavigation};
            TypedArray a = activity.obtainStyledAttributes(attrs);
            try
            {
                mStatusBarAvailable = a.getBoolean(0, false);
            } finally
            {
                a.recycle();
            }

            // check window flags
            WindowManager.LayoutParams winParams = win.getAttributes();
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if ((winParams.flags & bits) != 0)
            {
                mStatusBarAvailable = true;
            }

        }
    }


    private int getStatusHeight(Resources res)
    {
        int result = 0;
        int resourceId = res.getIdentifier(STATUS_BAR_HEIGHT_RES_NAME, "dimen", "android");
        if (resourceId > 0)
        {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }




}
