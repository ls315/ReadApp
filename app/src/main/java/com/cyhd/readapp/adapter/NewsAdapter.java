package com.cyhd.readapp.adapter;

/**
 * Created by huzhimin on 16/3/21.
 */

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cyhd.readapp.R;
import com.cyhd.readapp.bean.NewsData;
import com.cyhd.readapp.manager.ImageLoaderManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<NewsData> list;

    DisplayImageOptions options;

    private OnItemClickLitener mOnItemClickLitener;

    public NewsAdapter(List<NewsData> list, Context context) {

        options = ImageLoaderManager.getInstance().initImageOptions();
        this.list = list;
        this.context = context;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_head, null);
            return new HeadViewHolder(view);

        } else if (viewType == 1) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_body, null);
            return new BodyViewHolder(view);

        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_foot, null);
            return new FootViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        NewsData bean = list.get(position);

        String imageUrl = bean.getImgsrc();

        if (holder instanceof BodyViewHolder) {

            if (bean.getImgextra() != null && bean.getImgextra().size() == 2) {

                ((BodyViewHolder) holder).one.setVisibility(View.VISIBLE);
                ((BodyViewHolder) holder).two.setVisibility(View.GONE);
                ((BodyViewHolder) holder).three.setVisibility(View.GONE);

                String imageUrl1 = bean.getImgextra().get(0).getImgsrc();
                String imageUrl2 = bean.getImgextra().get(1).getImgsrc();

                ((BodyViewHolder) holder).titleOne.setText(bean.getTitle());
                ((BodyViewHolder) holder).numberOne.setText(bean.getReplyCount() + " 跟帖");

                ImageLoader.getInstance().displayImage(imageUrl, ((BodyViewHolder) holder).imageOne, options);
                ImageLoader.getInstance().displayImage(imageUrl1, ((BodyViewHolder) holder).imageTwo, options);
                ImageLoader.getInstance().displayImage(imageUrl2, ((BodyViewHolder) holder).imageThree, options);

            }else if(bean.getImgType()==1){

                ((BodyViewHolder) holder).one.setVisibility(View.GONE);
                ((BodyViewHolder) holder).three.setVisibility(View.VISIBLE);
                ((BodyViewHolder) holder).two.setVisibility(View.GONE);

                ((BodyViewHolder) holder).titleThree.setText(bean.getTitle());
                ImageLoader.getInstance().displayImage(imageUrl, ((BodyViewHolder) holder).imageSign, options);
                ((BodyViewHolder) holder).digestThree.setText(bean.getDigest());
                ((BodyViewHolder) holder).numberThree.setText(bean.getReplyCount() + " 跟帖");
                if(bean.getReplyCount()==0){
                    ((BodyViewHolder) holder).numberThree.setVisibility(View.GONE);
                }

            } else {

                ((BodyViewHolder) holder).one.setVisibility(View.GONE);
                ((BodyViewHolder) holder).three.setVisibility(View.GONE);
                ((BodyViewHolder) holder).two.setVisibility(View.VISIBLE);

                ((BodyViewHolder) holder).photo.setTag(imageUrl);

                if (((BodyViewHolder) holder).photo.getTag() != null && ((BodyViewHolder) holder).photo.getTag().equals(imageUrl)) {
                    ImageLoader.getInstance().displayImage(imageUrl, ((BodyViewHolder) holder).photo, options);
                }

                ((BodyViewHolder) holder).titleTwo.setText(bean.getTitle());
                ((BodyViewHolder) holder).digestTwo.setText(bean.getDigest());
                ((BodyViewHolder) holder).numberTwo.setText(bean.getReplyCount() + " 跟帖");

            }


        } else if (holder instanceof HeadViewHolder) {

            List<ImageView> imageList = new ArrayList<>();

            List<String> listUrl = new ArrayList();

            final List<String> listTitle = new ArrayList<>();

            List<String> goToUrl = new ArrayList<>();

            listUrl.add(imageUrl);

            String url = bean.getUrl_3w();
            goToUrl.add(url);

            String title1 = bean.getTitle();
            listTitle.add(title1);

            if (bean.getAds() != null) {
                String url2 = bean.getAds().get(position).getImgsrc();
                listUrl.add(url2);

                String title2 = bean.getAds().get(position).getTitle();
                listTitle.add(title2);

                String url22 = bean.getAds().get(position).getUrl();
                goToUrl.add(url22);
            }

            for (int i = 0; i < listUrl.size(); i++) {

                ImageView img = new ImageView(context);
                ImageLoader.getInstance().displayImage(listUrl.get(i), img, options);
                img.setScaleType(ImageView.ScaleType.FIT_XY);
                imageList.add(img);
            }

            ViewPageAdapter pageAdapter = new ViewPageAdapter(imageList,goToUrl,context);

            ((HeadViewHolder) holder).viewPager.setAdapter(pageAdapter);
            ((HeadViewHolder) holder).textView.setText(bean.getTitle());

            ((HeadViewHolder) holder).viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    String msg = listTitle.get(position);
                    ((HeadViewHolder) holder).textView.setText(msg);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }

            });

        } else if (holder instanceof FootViewHolder) {
            ((FootViewHolder) holder).textView.setText("加载中...");
        }

        if (mOnItemClickLitener != null) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = holder.getLayoutPosition();

                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return 0;
        } else if (position == getItemCount() - 1) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public class HeadViewHolder extends RecyclerView.ViewHolder {

        ViewPager viewPager;
        TextView textView;

        public HeadViewHolder(View itemView) {
            super(itemView);
            itemView.setFocusable(true);
            viewPager = (ViewPager) itemView.findViewById(R.id.item_head);
            textView = (TextView) itemView.findViewById(R.id.item_text_title);
        }
    }

    public class BodyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout one;
        ImageView imageOne;
        ImageView imageTwo;
        ImageView imageThree;
        TextView titleOne;
        TextView numberOne;

        RelativeLayout two;
        ImageView photo;
        TextView titleTwo;
        TextView digestTwo;
        TextView numberTwo;

        RelativeLayout three;
        TextView titleThree;
        ImageView imageSign;
        TextView digestThree;
        TextView numberThree;


        public BodyViewHolder(View itemView) {
            super(itemView);

            one = (RelativeLayout) itemView.findViewById(R.id.body_one_layout);
            imageOne = (ImageView) itemView.findViewById(R.id.body_one_image_one);
            imageTwo = (ImageView) itemView.findViewById(R.id.body_one_image_two);
            imageThree = (ImageView) itemView.findViewById(R.id.body_one_image_three);
            titleOne = (TextView) itemView.findViewById(R.id.body_one_text_title);
            numberOne = (TextView) itemView.findViewById(R.id.body_one_text_number);

            two = (RelativeLayout) itemView.findViewById(R.id.body_two_layout);
            photo = (ImageView) itemView.findViewById(R.id.body_two_image);
            titleTwo = (TextView) itemView.findViewById(R.id.body_two_text_title);
            digestTwo = (TextView) itemView.findViewById(R.id.body_two_text_digest);
            numberTwo = (TextView) itemView.findViewById(R.id.body_two_text_number);


            three = (RelativeLayout) itemView.findViewById(R.id.body_three_layout);
            titleThree = (TextView) itemView.findViewById(R.id.body_three_text_title);
            imageSign = (ImageView) itemView.findViewById(R.id.body_three_image);
            digestThree = (TextView) itemView.findViewById(R.id.body_three_text_digest);
            numberThree = (TextView) itemView.findViewById(R.id.body_three_text_number);


        }
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {

        ProgressBar bar;
        TextView textView;

        public FootViewHolder(View itemView) {
            super(itemView);
            bar = (ProgressBar) itemView.findViewById(R.id.foot_progressBar);
            textView = (TextView) itemView.findViewById(R.id.foot_text);
        }
    }


    /**
     * 上拉刷新数据
     *
     * @param newDatas
     */
    public void refreshItem(List<NewsData> newDatas) {

        list.clear();
        list.addAll(newDatas);
        notifyDataSetChanged();
    }


    /**
     * 下拉加载更多数据
     *
     * @param moreDates
     */
    public void moreData(List<NewsData> moreDates) {
        list.addAll(moreDates);
        notifyDataSetChanged();
    }

}
