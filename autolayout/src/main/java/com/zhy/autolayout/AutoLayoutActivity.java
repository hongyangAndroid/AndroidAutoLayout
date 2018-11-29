package com.zhy.autolayout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

;import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * Created by zhy on 15/11/19.
 */
public class AutoLayoutActivity extends AppCompatActivity {

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = AutoLayoutHelper.onCreateAutoLayoutView(name, context, attrs);
        return view != null ? view : super.onCreateView(name, context, attrs);
    }
}
