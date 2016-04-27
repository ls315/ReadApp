package com.cyhd.readapp.adapter;

/**
 * Created by huzhimin on 16/3/21.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cyhd.readapp.R;
import com.cyhd.readapp.bean.ReadData;
import com.cyhd.readapp.manager.ImageLoaderManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class ReadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ReadData> list;

    private Context context;

    DisplayImageOptions options;

    private OnItemClickLitener mOnItemClickLitener;

    public ReadAdapter(List<ReadData> list,Context context) {

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


        if (viewType == 1) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_read_card, null);
            return new BodyViewHolder(view);

        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_foot, null);
            return new FootViewHolder(view);
        }

        return null;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        ReadData readData = list.get(position);

        String imageUrl = readData.getImgsrc();

        if(holder instanceof BodyViewHolder){

            if (readData.getImgnewextra() != null && readData.getImgnewextra().size() == 2) {

                ((BodyViewHolder) holder).layoutOne.setVisibility(View.GONE);
                ((BodyViewHolder) holder).layoutTwo.setVisibility(View.VISIBLE);
                ((BodyViewHolder) holder).titleTwo.setVisibility(View.VISIBLE);

                String imageUrl1 = readData.getImgnewextra().get(0).getImgsrc();
                String imageUrl2 = readData.getImgnewextra().get(1).getImgsrc();

                ((BodyViewHolder) holder).titleTwo.setText(readData.getTitle());

                ImageLoader.getInstance().displayImage(imageUrl, ((BodyViewHolder) holder).imageOne, options);
                ImageLoader.getInstance().displayImage(imageUrl1, ((BodyViewHolder) holder).imageTwo, options);
                ImageLoader.getInstance().displayImage(imageUrl2, ((BodyViewHolder) holder).imageThree, options);

            }else {

                ((BodyViewHolder) holder).layoutOne.setVisibility(View.VISIBLE);
                ((BodyViewHolder) holder).layoutTwo.setVisibility(View.GONE);
                ((BodyViewHolder) holder).titleTwo.setVisibility(View.GONE);

                ((BodyViewHolder) holder).title.setText(readData.getTitle());

                ((BodyViewHolder) holder).imageView.setTag(imageUrl);

                if (((BodyViewHolder) holder).imageView.getTag() != null && ((BodyViewHolder) holder).imageView.getTag().equals(imageUrl)) {
                    ImageLoader.getInstance().displayImage(imageUrl, ((BodyViewHolder) holder).imageView, options);
                }

            }

            ((BodyViewHolder) holder).source.setText(readData.getSource());

        }else if (holder instanceof FootViewHolder) {
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

        LinearLayout layoutOne;
        ImageView imageView;
        TextView title;

        LinearLayout layoutTwo;
        ImageView imageOne;
        ImageView imageTwo;
        ImageView imageThree;
        TextView titleTwo;

        TextView source;

        public BodyViewHolder(View itemView) {
            super(itemView);

            layoutOne = (LinearLayout) itemView.findViewById(R.id.card_layout_one);
            imageView = (ImageView) itemView.findViewById(R.id.card_image_picture);
            title = (TextView) itemView.findViewById(R.id.card_text_title);

            layoutTwo = (LinearLayout) itemView.findViewById(R.id.card_layout_two);
            imageOne = (ImageView) itemView.findViewById(R.id.card_two_image_one);
            imageTwo = (ImageView) itemView.findViewById(R.id.card_two_image_two);
            imageThree = (ImageView) itemView.findViewById(R.id.card_two_image_three);
            titleTwo = (TextView) itemView.findViewById(R.id.card_two_text_title);

            source = (TextView) itemView.findViewById(R.id.card_text_type);
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
    public void refreshItem(List<ReadData> readDatas) {

        list.clear();
        list.addAll(readDatas);
        notifyDataSetChanged();
    }


    /**
     * 下拉加载更多数据
     *
     * @param readDatas
     */
    public void moreData(List<ReadData> readDatas) {
        list.addAll(readDatas);
        notifyDataSetChanged();
    }

}
