package com.zhy.autolayout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.attr.Attrs;
import com.zhy.autolayout.attr.AutoAttr;
import com.zhy.autolayout.attr.HeightAttr;
import com.zhy.autolayout.attr.MarginBottomAttr;
import com.zhy.autolayout.attr.MarginLeftAttr;
import com.zhy.autolayout.attr.MarginRightAttr;
import com.zhy.autolayout.attr.MarginTopAttr;
import com.zhy.autolayout.attr.MaxHeightAttr;
import com.zhy.autolayout.attr.MaxWidthAttr;
import com.zhy.autolayout.attr.MinHeightAttr;
import com.zhy.autolayout.attr.MinWidthAttr;
import com.zhy.autolayout.attr.PaddingBottomAttr;
import com.zhy.autolayout.attr.PaddingLeftAttr;
import com.zhy.autolayout.attr.PaddingRightAttr;
import com.zhy.autolayout.attr.PaddingTopAttr;
import com.zhy.autolayout.attr.TextSizeAttr;
import com.zhy.autolayout.attr.WidthAttr;

import java.util.ArrayList;
import java.util.List;

public class AutoLayoutInfo
{
    private List<AutoAttr> autoAttrs = new ArrayList<>();

    public void addAttr(AutoAttr autoAttr)
    {
        autoAttrs.add(autoAttr);
    }


    public void fillAttrs(View view)
    {
        for (AutoAttr autoAttr : autoAttrs)
        {
            autoAttr.apply(view);
        }
    }


    public static AutoLayoutInfo getAttrFromView(View view, int attrs, int base)
    {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) return null;
        AutoLayoutInfo autoLayoutInfo = new AutoLayoutInfo();

        // width & height
        if ((attrs & Attrs.WIDTH) != 0 && params.width > 0)
        {
            autoLayoutInfo.addAttr(WidthAttr.generate(params.width, base));
        }

        if ((attrs & Attrs.HEIGHT) != 0 && params.height > 0)
        {
            autoLayoutInfo.addAttr(HeightAttr.generate(params.height, base));
        }

        //margin
        if (params instanceof ViewGroup.MarginLayoutParams)
        {
            if ((attrs & Attrs.MARGIN) != 0)
            {
                autoLayoutInfo.addAttr(MarginLeftAttr.generate(((ViewGroup.MarginLayoutParams) params).leftMargin, base));
                autoLayoutInfo.addAttr(MarginTopAttr.generate(((ViewGroup.MarginLayoutParams) params).topMargin, base));
                autoLayoutInfo.addAttr(MarginRightAttr.generate(((ViewGroup.MarginLayoutParams) params).rightMargin, base));
                autoLayoutInfo.addAttr(MarginBottomAttr.generate(((ViewGroup.MarginLayoutParams) params).bottomMargin, base));
            }
            if ((attrs & Attrs.MARGIN_LEFT) != 0)
            {
                autoLayoutInfo.addAttr(MarginLeftAttr.generate(((ViewGroup.MarginLayoutParams) params).leftMargin, base));
            }
            if ((attrs & Attrs.MARGIN_TOP) != 0)
            {
                autoLayoutInfo.addAttr(MarginTopAttr.generate(((ViewGroup.MarginLayoutParams) params).topMargin, base));
            }
            if ((attrs & Attrs.MARGIN_RIGHT) != 0)
            {
                autoLayoutInfo.addAttr(MarginRightAttr.generate(((ViewGroup.MarginLayoutParams) params).rightMargin, base));
            }
            if ((attrs & Attrs.MARGIN_BOTTOM) != 0)
            {
                autoLayoutInfo.addAttr(MarginBottomAttr.generate(((ViewGroup.MarginLayoutParams) params).bottomMargin, base));
            }
        }

        //padding
        if ((attrs & Attrs.PADDING) != 0)
        {
            autoLayoutInfo.addAttr(PaddingLeftAttr.generate(view.getPaddingLeft(), base));
            autoLayoutInfo.addAttr(PaddingTopAttr.generate(view.getPaddingTop(), base));
            autoLayoutInfo.addAttr(PaddingRightAttr.generate(view.getPaddingRight(), base));
            autoLayoutInfo.addAttr(PaddingBottomAttr.generate(view.getPaddingBottom(), base));
        }
        if ((attrs & Attrs.PADDING_LEFT) != 0)
        {
            autoLayoutInfo.addAttr(MarginLeftAttr.generate(view.getPaddingLeft(), base));
        }
        if ((attrs & Attrs.PADDING_TOP) != 0)
        {
            autoLayoutInfo.addAttr(MarginTopAttr.generate(view.getPaddingTop(), base));
        }
        if ((attrs & Attrs.PADDING_RIGHT) != 0)
        {
            autoLayoutInfo.addAttr(MarginRightAttr.generate(view.getPaddingRight(), base));
        }
        if ((attrs & Attrs.PADDING_BOTTOM) != 0)
        {
            autoLayoutInfo.addAttr(MarginBottomAttr.generate(view.getPaddingBottom(), base));
        }

        //minWidth ,maxWidth , minHeight , maxHeight
        if ((attrs & Attrs.MIN_WIDTH) != 0)
        {
            autoLayoutInfo.addAttr(MinWidthAttr.generate(MinWidthAttr.getMinWidth(view), base));
        }
        if ((attrs & Attrs.MAX_WIDTH) != 0)
        {
            autoLayoutInfo.addAttr(MaxWidthAttr.generate(MaxWidthAttr.getMaxWidth(view), base));
        }
        if ((attrs & Attrs.MIN_HEIGHT) != 0)
        {
            autoLayoutInfo.addAttr(MinHeightAttr.generate(MinHeightAttr.getMinHeight(view), base));
        }
        if ((attrs & Attrs.MAX_HEIGHT) != 0)
        {
            autoLayoutInfo.addAttr(MaxHeightAttr.generate(MaxHeightAttr.getMaxHeight(view), base));
        }

        //textsize

        if (view instanceof TextView)
        {
            if ((attrs & Attrs.TEXTSIZE) != 0)
            {
                autoLayoutInfo.addAttr(TextSizeAttr.generate((int) ((TextView) view).getTextSize(), base));
            }
        }
        return autoLayoutInfo;
    }


    @Override
    public String toString()
    {
        return "AutoLayoutInfo{" +
                "autoAttrs=" + autoAttrs +
                '}';
    }
}