package com.zhy.autolayout.attr;

/**
 * Created by zhy on 15/12/5.
 * <p/>
 * 与attrs.xml中数值对应
 */
public interface Attrs
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
    public static final int MIN_WIDTH = PADDING_BOTTOM << 1;
    public static final int MAX_WIDTH = MIN_WIDTH << 1;
    public static final int MIN_HEIGHT = MAX_WIDTH << 1;
    public static final int MAX_HEIGHT = MIN_HEIGHT << 1;

}
