package com.zhy.autolayout;

/**
 * Created by zhy on 15/12/1.
 */
public class AutoBase
{
    public static final int WIDTH = 1;
    public static final int HEIGHT = WIDTH << 1;
    public static final int TEXTSIZE = HEIGHT << 1;
    public static final int PADDING = TEXTSIZE << 1;
    public static final int MARGIN = PADDING << 1;
    public static final int MARGIN_LEFT = MARGIN << 1;
    public static final int MARGIN_TOP = MARGIN_LEFT << 1;
    public static final int MARGIN_RIGHT = MARGIN_TOP << 1;
    public static final int MARGIN_BOTTOM = MARGIN_RIGHT << 1;
    public static final int PADDING_LEFT = MARGIN_BOTTOM << 1;
    public static final int PADDING_TOP = PADDING_LEFT << 1;
    public static final int PADDING_RIGHT = PADDING_TOP << 1;
    public static final int PADDING_BOTTOM = PADDING_RIGHT << 1;


    public static boolean contains(int autobase, int flag)
    {
        return (autobase & flag) != 0;
    }


}
