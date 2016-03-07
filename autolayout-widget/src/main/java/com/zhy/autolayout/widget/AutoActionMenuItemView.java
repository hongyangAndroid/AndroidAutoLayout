package com.zhy.autolayout.widget;

import android.content.Context;
import android.content.res.TypedArray;

import android.support.v7.view.menu.ActionMenuItemView;
import android.util.AttributeSet;
import android.util.TypedValue;


import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.autolayout.utils.DimenUtils;

/**
 * Created by hupei on 2016/3/7 14:44.
 */
public class AutoActionMenuItemView extends ActionMenuItemView {
    private static final int NO_VALID = -1;
    private int mMenuTextSize;

    public AutoActionMenuItemView(Context context) {
        this(context, null);
    }

    public AutoActionMenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoActionMenuItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Theme,
                defStyle, R.style.ThemeOverlay_AppCompat);
        int menuTextAppearance = a.getResourceId(R.styleable.Theme_actionBarTheme,
                R.style.ThemeOverlay_AppCompat_ActionBar);
        mMenuTextSize = loadTextSizeFromTextAppearance(menuTextAppearance);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!this.isInEditMode()) {
            setUpTitleTextSize();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int loadTextSizeFromTextAppearance(int textAppearanceResId) {
        TypedArray a = getContext().obtainStyledAttributes(textAppearanceResId,
                R.styleable.TextAppearance);
        try {
            if (!DimenUtils.isPxVal(a.peekValue(R.styleable.TextAppearance_android_textSize)))
                return NO_VALID;
            return a.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, NO_VALID);
        } finally {
            a.recycle();
        }
    }

    private void setUpTitleTextSize() {
        if (mMenuTextSize == -1) return;
        int autoTextSize = AutoUtils.getPercentHeightSize(mMenuTextSize);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, autoTextSize);
    }
}
