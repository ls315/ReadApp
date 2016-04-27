package com.cyhd.readapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cyhd.readapp.page.WebViewActivity;

import java.util.List;

/**
 * Created by huzhimin on 16/3/16.
 */
public class ViewPageAdapter extends PagerAdapter{

    List<ImageView> list = null;
    List<String> stringList;
    Context context;

    public ViewPageAdapter (List<ImageView> list,List<String> str, Context context) {
        this.list = list;
        this.stringList = str;
        this.context=context;
    }

    @Override
    public int getCount() {
//        return Integer.MAX_VALUE;

        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {


//        ImageView imageViews = list.get(position % list.size());

//        ViewGroup parent = (ViewGroup) imageViews.getParent();
//        if (parent != null) {
//            parent.removeView(imageViews);
////        }
//        container.addView(imageViews);
//
//        return imageViews;

//        if(container.getChildAt(position% list.size())!=null){
//            container.removeView(list.get(position% list.size()));
//        }

        container.addView(list.get(position));
        list.get(position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = stringList.get(position);
                if (url != null) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("url", url);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "url为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(list.get(position% list.size()));

        container.removeView((View) object);
    }

}
