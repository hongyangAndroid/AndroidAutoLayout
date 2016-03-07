package com.zhy.autolayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by hupei on 2016/3/7 16:44.
 */
public class AutoLayoutWidgetActivity extends AutoLayoutActivity {

    private static final String ACTION_MENU_ITEM_VIEW = "android.support.v7.view.menu.ActionMenuItemView";
    private static final String TAB_LAYOUT = "android.support.design.widget.TabLayout";

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;

        if (name.equals(ACTION_MENU_ITEM_VIEW)) {
            view = new AutoActionMenuItemView(context, attrs);
        }
        if (name.equals(TAB_LAYOUT)) {
            view = new AutoTabLayout(context, attrs);
        }

        if (view != null) return view;
        return super.onCreateView(name, context, attrs);
    }
}