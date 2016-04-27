package com.cyhd.readapp.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by huzhimin on 16/3/25.
 */
public class VerticalSwipeRefreshLayout extends SwipeRefreshLayout {
    private int touchSlop;//系统滑动常量
    private float lastX;


    public VerticalSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                final float eventX = event.getX();
                float diff = Math.abs(eventX - lastX);

                if (diff > touchSlop + 100)//增大容差，x偏移足够大，false
                    return false;
        }
        return super.onInterceptTouchEvent(event);

    }

}
