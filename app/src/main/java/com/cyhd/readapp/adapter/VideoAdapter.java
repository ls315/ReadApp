package com.cyhd.readapp.adapter;

/**
 * Created by huzhimin on 16/3/21.
 */

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.cyhd.readapp.R;
import com.cyhd.readapp.bean.VideoData;
import com.cyhd.readapp.manager.ImageLoaderManager;
import com.cyhd.readapp.util.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<VideoData> list;

    private Context context;

    DisplayImageOptions options;

    MediaController mediaController;

    private OnItemClickLitener mOnItemClickLitener;

    public VideoAdapter(List<VideoData> list, Context context) {

        options = ImageLoaderManager.getInstance().initImageOptions();

        this.list = list;
        this.context = context;
        mediaController = new MediaController(context);

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

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_card, null);
            return new BodyViewHolder(view);

        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_foot, null);
            return new FootViewHolder(view);
        }

        return null;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        VideoData data = list.get(position);

        if (holder instanceof BodyViewHolder) {

            ((BodyViewHolder) holder).title.setText(data.getTitle());

            String cover = data.getCover();
            String topic = data.getTopicImg();
            final String mp4 = data.getMp4_url();

            ((BodyViewHolder) holder).cover.setTag(cover);
            ((BodyViewHolder) holder).topic.setTag(topic);

            if (((BodyViewHolder) holder).cover.getTag() != null && ((BodyViewHolder) holder).cover.getTag().equals(cover)) {
                ImageLoader.getInstance().displayImage(cover, ((BodyViewHolder) holder).cover, options);
            }

            if (((BodyViewHolder) holder).topic.getTag() != null && ((BodyViewHolder) holder).topic.getTag().equals(topic)) {
                ImageLoader.getInstance().displayImage(topic, ((BodyViewHolder) holder).topic, options);
            }

            ((BodyViewHolder) holder).topicName.setText(data.getTopicName());
            String str = Utils.secToTime(data.getLength()) + " / " + Utils.conversion(data.getPlayCount())+"播放";
            ((BodyViewHolder) holder).length.setText(str);

            ((BodyViewHolder) holder).videoView.setTag(mp4);
            if(((BodyViewHolder) holder).videoView.getTag() != null && ((BodyViewHolder) holder).videoView.getTag().equals(mp4)){
                ((BodyViewHolder) holder).videoView.setMediaController(mediaController);
                mediaController.setMediaPlayer(((BodyViewHolder) holder).videoView);
            }

            ((BodyViewHolder) holder).play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BodyViewHolder) holder).play.setVisibility(View.GONE);
                    ((BodyViewHolder) holder).cover.setVisibility(View.GONE);
                    ((BodyViewHolder) holder).length.setVisibility(View.GONE);
                    ((BodyViewHolder) holder).videoView.setVideoURI(Uri.parse(mp4));

                    ((BodyViewHolder) holder).videoView.start();
                    ((BodyViewHolder) holder).videoView.requestFocus();
                }
            });


            ((BodyViewHolder) holder).videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    ((BodyViewHolder) holder).progressBar.setVisibility(View.GONE);
                }
            });

            ((BodyViewHolder) holder).play.setVisibility(View.VISIBLE);
            ((BodyViewHolder) holder).cover.setVisibility(View.VISIBLE);
            ((BodyViewHolder) holder).length.setVisibility(View.VISIBLE);
            ((BodyViewHolder) holder).progressBar.setVisibility(View.VISIBLE);


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
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position == getItemCount() - 1) {
            return 2;
        } else {
            return 1;
        }
    }


    public class BodyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView topicName;
        TextView tie;
        TextView length;

        ImageView cover;
        ImageView play;
        ImageView topic;
        ImageView share;

        VideoView videoView;

        ProgressBar progressBar;

        public BodyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.video_text_title);
            topicName = (TextView) itemView.findViewById(R.id.video_text_topicname);
            tie = (TextView) itemView.findViewById(R.id.video_text_tie);
            length = (TextView) itemView.findViewById(R.id.video_text_length);

            cover = (ImageView) itemView.findViewById(R.id.video_image_cover);
            topic = (ImageView) itemView.findViewById(R.id.video_image_topic);
            share = (ImageView) itemView.findViewById(R.id.video_image_share);
            play = (ImageView) itemView.findViewById(R.id.video_image_play);

            videoView   = (VideoView) itemView.findViewById(R.id.video_video);
            progressBar = (ProgressBar) itemView.findViewById(R.id.video_progress);
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
     * @param readDatas
     */
    public void refreshItem(List<VideoData> readDatas) {

        list.clear();
        list.addAll(readDatas);
        notifyDataSetChanged();
    }


    /**
     * 下拉加载更多数据
     *
     * @param readDatas
     */
    public void moreData(List<VideoData> readDatas) {
        list.addAll(readDatas);
        notifyDataSetChanged();
    }

}
