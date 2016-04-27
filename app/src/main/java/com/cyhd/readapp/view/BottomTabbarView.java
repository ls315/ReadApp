package com.cyhd.readapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyhd.readapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hy on 15/8/22.
 */
public class BottomTabbarView extends LinearLayout {

    /**
     * tabbar item
     */
    public static class TabbarItem {
        public String title;
        public int imageResId;
        public Bitmap imageBitmap;
        public int selectedImageResId;
        public Bitmap selectedImageBitmap;

        public View paddingView;
        public TextView titleText;
        public ImageView iconImage;
        public View contentView;

        public Object tag;


        static int defaultTextColor = -1;
        static int selectedTextColor = -1;
        static int paddingTop = -1;

        public TabbarItem(String title, int image) {
            this.title = title;
            this.imageResId = image;
        }

        public TabbarItem(String title, int defImage, int selImage) {
            this.title = title;
            this.imageResId = defImage;
            this.selectedImageResId = selImage;
        }

        /**
         * 设置或者取消选中状态
         *
         * @param flag
         */
        void setSelected(boolean flag) {
            this.contentView.setSelected(flag);
            if (flag) {
                if (selectedTextColor >= 0) {
                    titleText.setTextColor(selectedTextColor);
                }

                if (selectedImageResId > 0) {
                    iconImage.setImageResource(selectedImageResId);
                } else if (selectedImageBitmap != null) {
                    iconImage.setImageBitmap(selectedImageBitmap);
                }
            } else {
                if (defaultTextColor >= 0) {
                    titleText.setTextColor(defaultTextColor);
                }

                if (imageResId > 0) {
                    iconImage.setImageResource(imageResId);
                } else if (imageBitmap != null) {
                    iconImage.setImageBitmap(imageBitmap);
                }
            }
        }

        /**
         * 初始化界面展示
         *
         * @param context
         */
        void initContentView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.view_tabbar_item, null);

            paddingView = view.findViewById(R.id.tabbar_padding_view);
            titleText = (TextView) view.findViewById(R.id.tabbar_item_title);
            iconImage = (ImageView) view.findViewById(R.id.tabbar_item_image);

            titleText.setText(title);

            if (imageResId > 0) {
                iconImage.setImageResource(imageResId);
            } else if (imageBitmap != null) {
                iconImage.setImageBitmap(imageBitmap);
            }

            if (defaultTextColor >= 0) {
                titleText.setTextColor(defaultTextColor);
            }

            if (paddingTop > 0) {
                paddingView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, paddingTop));
            }

            this.contentView = view;
        }
    }

    /**
     * 用户选择tabbar的监听器
     */
    public interface OnSelectionChangeListener {
        void onSelectionChange(int index, TabbarItem item);
    }

    private View separatorView;
    private LinearLayout tabContainer;

    List<TabbarItem> tabbarItems;

    private OnSelectionChangeListener listener;

    int selectedIndex = -1;

    public BottomTabbarView(Context context) {
        super(context);
        initTabbar();
    }

    public BottomTabbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTabbar();
    }

    public BottomTabbarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTabbar();
    }

    private void initTabbar() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_bottom_tabbar, this);

        separatorView = findViewById(R.id.tabbar_separator);
        tabContainer = (LinearLayout) findViewById(R.id.tabbar_container);

        tabbarItems = new ArrayList<>();
    }

    /**
     * 设置分割线的颜色
     *
     * @param color
     */
    public void setSeparatorColor(int color) {
        separatorView.setBackgroundColor(color);
    }


    /**
     * 设置默认的文字颜色
     *
     * @param color
     */
    public void setDefaultTitleColor(int color) {
        TabbarItem.defaultTextColor = color;
    }

    /**
     * 设置选中文字的颜色
     *
     * @param color
     */
    public void setSelectedTitleColor(int color) {
        TabbarItem.selectedTextColor = color;
    }


    /**
     * 设置图标和文字上面的间距
     *
     * @param height
     */
    public void setPaddingTop(int height) {
        TabbarItem.paddingTop = height;
    }


    /**
     * 添加tabbar item
     *
     * @param item
     */
    public void addTabbarItem(final TabbarItem item) {
        item.initContentView(getContext());

        final int index = tabbarItems.size();

        item.contentView.setClickable(true);
        item.contentView.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (index == selectedIndex) {
                            return;
                        }

                        if (selectedIndex >= 0) {
                            tabbarItems.get(selectedIndex).setSelected(false);
                        }

                        item.setSelected(true);


                        if (listener != null) {
                            listener.onSelectionChange(index, item);
                        }

                        selectedIndex = index;


                    }
                });

        tabbarItems.add(item);

        LayoutParams layoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
        layoutParams.gravity = Gravity.CENTER;
        item.contentView.setLayoutParams(layoutParams);
        tabContainer.addView(item.contentView);
        tabContainer.requestLayout();
    }

    /**
     * 设置当前选中的view
     *
     * @param index
     */
    public void setSelectedTab(int index) {

        if (index < tabbarItems.size()) {
            tabbarItems.get(index).contentView.performClick();
        }
    }

    /**
     * 用户点击tab的监听器
     *
     * @param listener
     */
    public void setOnSelectChangeListener(OnSelectionChangeListener listener) {
        this.listener = listener;
    }
}
