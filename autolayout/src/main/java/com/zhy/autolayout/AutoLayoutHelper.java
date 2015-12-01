/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhy.autolayout;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AutoLayoutHelper
{
    private final ViewGroup mHost;

    private static final int[] LL = new int[]
            { //
                    android.R.attr.textSize,
                    android.R.attr.padding,//
                    android.R.attr.paddingLeft,//
                    android.R.attr.paddingTop,//
                    android.R.attr.paddingRight,//
                    android.R.attr.paddingBottom,//
                    android.R.attr.layout_width,//
                    android.R.attr.layout_height,//
                    android.R.attr.layout_margin,//
                    android.R.attr.layout_marginLeft,//
                    android.R.attr.layout_marginTop,//
                    android.R.attr.layout_marginRight,//
                    android.R.attr.layout_marginBottom,//

            };

    private static final int INDEX_TEXT_SIZE = 0;
    private static final int INDEX_PADDING = 1;
    private static final int INDEX_PADDING_LEFT = 2;
    private static final int INDEX_PADDING_TOP = 3;
    private static final int INDEX_PADDING_RIGHT = 4;
    private static final int INDEX_PADDING_BOTTOM = 5;
    private static final int INDEX_WIDTH = 6;
    private static final int INDEX_HEIGHT = 7;
    private static final int INDEX_MARGIN = 8;
    private static final int INDEX_MARGIN_LEFT = 9;
    private static final int INDEX_MARGIN_TOP = 10;
    private static final int INDEX_MARGIN_RIGHT = 11;
    private static final int INDEX_MARGIN_BOTTOM = 12;

    private static final String VAL_WRAP_CONTENT = "-2";
    private static final String VAL_MATCH_PARENT = "-1";

    /**
     * TODO  move to other place
     */
    private AutoLayoutConifg mAutoLayout;

    public AutoLayoutHelper(ViewGroup host)
    {
        mHost = host;
        mAutoLayout = new AutoLayoutConifg();

        boolean ignoreStatusBar = true;
        if (host.getContext() instanceof UseStatusBar)
        {
            ignoreStatusBar = false;
        }
        mAutoLayout.auto((Activity) host.getContext(), ignoreStatusBar);
    }


    public void adjustChildren()
    {
        for (int i = 0, n = mHost.getChildCount(); i < n; i++)
        {
            View view = mHost.getChildAt(i);
            ViewGroup.LayoutParams params = view.getLayoutParams();

            if (params instanceof AutoLayoutParams)
            {
                AutoLayoutInfo info =
                        ((AutoLayoutParams) params).getPercentLayoutInfo();
                if (info != null)
                {
                    supportTextSize(view, info);

                    supportPadding(view, info);

                    if (params instanceof ViewGroup.MarginLayoutParams)
                    {
                        info.fillMarginLayoutParams((ViewGroup.MarginLayoutParams) params,
                                getAvailableWidth(), getAvailableHeight(), getDesignWidth(), getDesignHeight());
                    } else
                    {
                        info.fillLayoutParams(params,
                                getAvailableWidth(), getAvailableHeight(), getDesignWidth(), getDesignHeight());
                    }
                }
            }
        }


    }

    private void supportPadding(View view, AutoLayoutInfo info)
    {

        int avaWidth = getAvailableWidth();
        int avaHeight = getAvailableHeight();
        int designWidth = getDesignWidth();
        int designHeight = getDesignHeight();

        int left = view.getPaddingLeft(), right = view.getPaddingRight(), top = view.getPaddingTop(), bottom = view.getPaddingBottom();

        if (info.padding != 0)
        {
            if (AutoBase.contains(info.autobaseWidth, AutoBase.PADDING))
            {
                left = top = right = bottom = (int) (info.padding * 1.0f / designWidth * avaWidth);

            } else if (AutoBase.contains(info.autobaseHeight, AutoBase.PADDING))
            {
                left = top = right = bottom = (int) (info.padding * 1.0f / designHeight * avaHeight);
            } else
            {
                int vertical = (int) (info.padding * 1.0f / designHeight * avaHeight);
                int horizon = (int) (info.padding * 1.0f / designWidth * avaWidth);
                left = right = horizon;
                top = bottom = vertical;
            }
        }
        if (info.paddingLeft != 0)
        {
            if (AutoBase.contains(info.autobaseHeight, AutoBase.PADDING_LEFT))
            {
                left = (int) (info.paddingLeft * 1.0f / designHeight * avaHeight);
            } else
            {
                left = (int) (info.paddingLeft * 1.0f / designWidth * avaWidth);
            }

        }

        if (info.paddingTop != 0)
        {
            if (AutoBase.contains(info.autobaseWidth, AutoBase.PADDING_TOP))
            {
                top = (int) (info.paddingTop * 1.0f / designWidth * avaWidth);
            } else
            {
                top = (int) (info.paddingTop * 1.0f / designHeight * avaHeight);
            }
        }

        if (info.paddingRight != 0)
        {
            if (AutoBase.contains(info.autobaseHeight, AutoBase.PADDING_RIGHT))
            {
                right = (int) (info.paddingRight * 1.0f / designHeight * avaHeight);
            } else
            {
                right = (int) (info.paddingRight * 1.0f / designWidth * avaWidth);
            }
        }

        if (info.paddingBottom != 0)
        {
            if (AutoBase.contains(info.autobaseWidth, AutoBase.PADDING_TOP))
            {
                bottom = (int) (info.paddingBottom * 1.0f / designWidth * avaWidth);
            } else
            {
                bottom = (int) (info.paddingBottom * 1.0f / designHeight * avaHeight);
            }
        }

        view.setPadding(left, top, right, bottom);
    }

    private int getAvailableWidth()
    {
        return mAutoLayout.getAvailableWidth();
    }

    private int getAvailableHeight()
    {
        return mAutoLayout.getAvailaleHeight();

    }

    private int getDesignWidth()
    {
        return mAutoLayout.getDesignWidth();
    }

    private int getDesignHeight()
    {
        return mAutoLayout.getDesignHeight();
    }


    private void supportTextSize(View view, AutoLayoutInfo info)
    {
        if (!(view instanceof TextView)) return;
        if (info.textSize == 0) return;

        float textSize;
        if (AutoBase.contains(info.autobaseWidth, AutoBase.TEXTSIZE))
        {
            textSize = info.textSize * 1.0f / getDesignWidth() * getAvailableWidth();
        } else
        {
            textSize = info.textSize * 1.0f / getDesignHeight() * getAvailableHeight();
        }
        //textSize = textSize / 1.34f;
        ((TextView) view).setIncludeFontPadding(false);
        ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }


    public static AutoLayoutInfo getAutoLayoutInfo(Context context,
                                                   AttributeSet attrs)
    {
        AutoLayoutInfo info = new AutoLayoutInfo();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoLayout_Layout);
        info.autobaseWidth = a.getInt(R.styleable.AutoLayout_Layout_layout_auto_basewidth, 0);
        info.autobaseHeight = a.getInt(R.styleable.AutoLayout_Layout_layout_auto_baseheight, 0);
        a.recycle();

        TypedArray array = context.obtainStyledAttributes(attrs, LL);

        int n = array.getIndexCount();


        for (int i = 0; i < n; i++)
        {
            int index = array.getIndex(i);
            String val = array.getString(index);
            if (val.equals(VAL_WRAP_CONTENT) || val.equals(VAL_MATCH_PARENT))
            {
                continue;
            }
            if (!val.endsWith("px"))
            {
                continue;
            }
            switch (index)
            {
                case INDEX_TEXT_SIZE:
                    info.textSize = array.getDimensionPixelOffset(index, 0);
                    break;
                case INDEX_PADDING:
                    info.padding = array.getDimensionPixelOffset(index, 0);
                    break;
                case INDEX_PADDING_LEFT:
                    info.paddingLeft = array.getDimensionPixelOffset(index, 0);
                    break;
                case INDEX_PADDING_TOP:
                    info.paddingTop = array.getDimensionPixelOffset(index, 0);
                    break;
                case INDEX_PADDING_RIGHT:
                    info.paddingRight = array.getDimensionPixelOffset(index, 0);
                    break;
                case INDEX_PADDING_BOTTOM:
                    info.paddingBottom = array.getDimensionPixelOffset(index, 0);
                    break;
                case INDEX_WIDTH:
                    info.widthPx = array.getDimensionPixelSize(index, 0);
                    break;
                case INDEX_HEIGHT:
                    info.heightPx = array.getDimensionPixelSize(index, 0);
                    break;
                case INDEX_MARGIN:
                    info.margin = array.getDimensionPixelOffset(index, 0);
                    break;
                case INDEX_MARGIN_LEFT:
                    info.marginLeft = array.getDimensionPixelOffset(index, 0);
                    break;
                case INDEX_MARGIN_TOP:
                    info.marginTop = array.getDimensionPixelOffset(index, 0);
                    break;
                case INDEX_MARGIN_RIGHT:
                    info.marginRight = array.getDimensionPixelOffset(index, 0);
                    break;
                case INDEX_MARGIN_BOTTOM:
                    info.marginBottom = array.getDimensionPixelOffset(index, 0);
                    break;
            }
        }
        array.recycle();
        L.e(info.toString());
        return info;
    }


    public static class AutoLayoutInfo
    {
        public int widthPx;
        public int heightPx;

        private int margin;
        private int marginLeft;
        private int marginRight;
        private int marginTop;
        private int marginBottom;

        private int textSize;

        private int padding;
        private int paddingLeft;
        private int paddingRight;
        private int paddingTop;
        private int paddingBottom;

        private int autobaseWidth = 0;
        private int autobaseHeight = 0;


        public void fillLayoutParams(ViewGroup.LayoutParams params, int avaWidth,
                                     int avaHeight, int designWidth, int designHeight)
        {

            if (widthPx != 0)
            {
                if (AutoBase.contains(autobaseHeight, AutoBase.WIDTH))
                {
                    params.width = (int) (widthPx * 1.0f / designHeight * avaHeight);
                } else
                {
                    params.width = (int) (widthPx * 1.0f / designWidth * avaWidth);
                }

            }
            if (heightPx != 0)
            {
                if (AutoBase.contains(autobaseWidth, AutoBase.HEIGHT))
                {
                    params.height = (int) (heightPx * 1.0f / designWidth * avaWidth);
                } else
                {
                    params.height = (int) (heightPx * 1.0f / designHeight * avaHeight);
                }


            }
        }

        @Override
        public String toString()
        {
            return "AutoLayoutInfo{" +
                    "widthPx=" + widthPx +
                    ", heightPx=" + heightPx +
                    ", margin=" + margin +
                    ", marginLeft=" + marginLeft +
                    ", marginRight=" + marginRight +
                    ", marginTop=" + marginTop +
                    ", marginBottom=" + marginBottom +
                    ", textSize=" + textSize +
                    ", padding=" + padding +
                    ", paddingLeft=" + paddingLeft +
                    ", paddingRight=" + paddingRight +
                    ", paddingTop=" + paddingTop +
                    ", paddingBottom=" + paddingBottom +
                    '}';
        }

        public void fillMarginLayoutParams(ViewGroup.MarginLayoutParams params, int avaWidth,
                                           int avaHeight, int designWidth, int designHeight)
        {

            if (margin != 0)
            {
                if (AutoBase.contains(autobaseWidth, AutoBase.MARGIN))
                {
                    int marginSize = (int) (margin * 1.0f / designWidth * avaWidth);
                    params.leftMargin = params.topMargin = params.rightMargin = params.bottomMargin = marginSize;

                } else if (AutoBase.contains(autobaseHeight, AutoBase.MARGIN))
                {
                    int marginSize = (int) (margin * 1.0f / designHeight * avaHeight);
                    params.leftMargin = params.topMargin = params.rightMargin = params.bottomMargin = marginSize;
                } else
                {
                    int vertical = (int) (margin * 1.0f / designHeight * avaHeight);
                    int horizon = (int) (margin * 1.0f / designWidth * avaWidth);
                    params.leftMargin = params.rightMargin = horizon;
                    params.topMargin = params.bottomMargin = vertical;
                }
            }
            if (marginLeft != 0)
            {
                if (AutoBase.contains(autobaseHeight, AutoBase.MARGIN_LEFT))
                {
                    params.leftMargin = (int) (marginLeft * 1.0f / designHeight * avaHeight);
                } else
                {
                    params.leftMargin = (int) (marginLeft * 1.0f / designWidth * avaWidth);
                }
            }
            if (marginTop != 0)
            {
                if (AutoBase.contains(autobaseWidth, AutoBase.MARGIN_TOP))
                {
                    params.topMargin = (int) (marginTop * 1.0f / designWidth * avaWidth);
                } else
                {
                    params.topMargin = (int) (marginTop * 1.0f / designHeight * avaHeight);
                }
            }
            if (marginRight != 0)
            {
                if (AutoBase.contains(autobaseHeight, AutoBase.MARGIN_RIGHT))
                {
                    params.rightMargin = (int) (marginRight * 1.0f / designHeight * avaHeight);
                } else
                {
                    params.rightMargin = (int) (marginRight * 1.0f / designWidth * avaWidth);
                }
            }
            if (marginBottom != 0)
            {
                if (AutoBase.contains(autobaseWidth, AutoBase.MARGIN_BOTTOM))
                {
                    params.bottomMargin = (int) (marginBottom * 1.0f / designWidth * avaWidth);
                } else
                {
                    params.bottomMargin = (int) (marginBottom * 1.0f / designHeight * avaHeight);
                }

            }
            fillLayoutParams(params, avaWidth, avaHeight, designWidth, designHeight);
        }


    }

    public interface AutoLayoutParams
    {
        AutoLayoutInfo getPercentLayoutInfo();
    }
}
