package com.cyhd.readapp.adapter;

/**
 * Created by huzhimin on 16/3/21.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cyhd.readapp.R;
import com.cyhd.readapp.bean.TopicData;
import com.cyhd.readapp.manager.ImageLoaderManager;
import com.cyhd.readapp.util.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<TopicData.DataEntity.ExpertListEntity> list;

    DisplayImageOptions options;
    DisplayImageOptions roundOptions;

    private OnItemClickLitener mOnItemClickLitener;

    public TopicAdapter(List<TopicData.DataEntity.ExpertListEntity> list, Context context) {

        options = ImageLoaderManager.getInstance().initImageOptions();
        roundOptions = ImageLoaderManager.getInstance().roundImageOptions();
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

        if (viewType == 1) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_card, null);
            return new BodyViewHolder(view);

        } else if (viewType == 2) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_foot, null);
            return new FootViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        TopicData.DataEntity.ExpertListEntity bean = list.get(position);

        if (holder instanceof BodyViewHolder) {

            String picUrl = bean.getPicurl();
            String headPicUrl = bean.getHeadpicurl();

            ((BodyViewHolder) holder).pic.setTag(picUrl);
            ((BodyViewHolder) holder).headPic.setTag(headPicUrl);

            if (((BodyViewHolder) holder).pic.getTag() != null && ((BodyViewHolder) holder).pic.getTag().equals(picUrl)) {
                ImageLoader.getInstance().displayImage(picUrl, ((BodyViewHolder) holder).pic, options);
            }

            if (((BodyViewHolder) holder).headPic.getTag() != null && ((BodyViewHolder) holder).headPic.getTag().equals(headPicUrl)) {
                ImageLoader.getInstance().displayImage(headPicUrl, ((BodyViewHolder) holder).headPic, roundOptions);
            }

            Spannable spannable = new SpannableString(bean.getClassification() + " | " + Utils.conversion(bean.getConcernCount()) + "关注 | " + bean.getQuestionCount() + "提问");
            spannable.setSpan(new ForegroundColorSpan(Color.BLUE),0,2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

            ((BodyViewHolder) holder).name.setText(bean.getName() + " / " + bean.getTitle());
            ((BodyViewHolder) holder).alias.setText(bean.getAlias());
            ((BodyViewHolder) holder).classification.setText(spannable);

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

        if (position == getItemCount() - 1) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class BodyViewHolder extends RecyclerView.ViewHolder {


        ImageView pic;
        ImageView headPic;
        TextView name;
        TextView alias;
        TextView classification;


        public BodyViewHolder(View itemView) {
            super(itemView);

            pic = (ImageView) itemView.findViewById(R.id.topic_image_pic);
            headPic = (ImageView) itemView.findViewById(R.id.topic_image_headpic);
            name = (TextView) itemView.findViewById(R.id.topic_text_name);
            alias = (TextView) itemView.findViewById(R.id.topic_text_alias);
            classification = (TextView) itemView.findViewById(R.id.topic_text_classification);

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
    public void refreshItem(List<TopicData.DataEntity.ExpertListEntity> newDatas) {

        list.clear();
        list.addAll(newDatas);
        notifyDataSetChanged();
    }


    /**
     * 下拉加载更多数据
     *
     * @param moreDates
     */
    public void moreData(List<TopicData.DataEntity.ExpertListEntity> moreDates) {
        list.addAll(moreDates);
        notifyDataSetChanged();
    }

}
