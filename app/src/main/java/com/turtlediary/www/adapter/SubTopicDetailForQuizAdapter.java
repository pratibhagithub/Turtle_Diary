package com.turtlediary.www.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.turtlediary.www.R;
import com.turtlediary.www.beans.PracticBean;
import com.turtlediary.www.interfaces.OnQuizItemClickedListner;

import java.util.List;

/**
 * Created by pratibha on 2/11/17.
 */

public class SubTopicDetailForQuizAdapter extends RecyclerView.Adapter<SubTopicDetailForQuizAdapter.ItemViewHolder> {

    private List<PracticBean> practicBeen;
    private Activity activity;
    String screenNameForEventAnalytics;
    private OnQuizItemClickedListner onQuizItemClickedListne;

    public SubTopicDetailForQuizAdapter(Activity activity, List<PracticBean> practicBeen, String screenNameForEventAnalytics, OnQuizItemClickedListner onQuizItemClickedListner) {
        this.practicBeen = practicBeen;
        this.activity = activity;
        this.screenNameForEventAnalytics = screenNameForEventAnalytics;
        this.onQuizItemClickedListne = onQuizItemClickedListner;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_topic_detail_list_item, viewGroup, false);
        SubTopicDetailForQuizAdapter.ItemViewHolder viewHolder = new SubTopicDetailForQuizAdapter.ItemViewHolder(view);
        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.tvPracticeName.setText(practicBeen.get(position).getPracticeName());
        /*if(practicBeen.get(position).isBlock())
        holder.ivFreeBannerImage.setVisibility(View.GONE);
        else
        holder.ivFreeBannerImage.setVisibility(View.VISIBLE);
*/
        holder.ivFreeBannerImage.setVisibility(View.GONE);

        Glide.with(activity).load(practicBeen.get(position).getImageName()).into(holder.ivPracticeImage);
        holder.ivPracticeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Hello", "" + practicBeen.get(position).getWebView());
                onQuizItemClickedListne.onQuizClicked(practicBeen.get(position));
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return practicBeen.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivFreeBannerImage;
        TextView tvPracticeName, tvPracticeHeader;
        ImageView ivPracticeImage;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvPracticeName = (TextView) itemView.findViewById(R.id.tv_sub_topic_name);
            //  tvPracticeHeader = (TextView) itemView.findViewById(R.id.practice_header);
            ivPracticeImage = (ImageView) itemView.findViewById(R.id.iv_sub_topic_image);
            ivFreeBannerImage = (ImageView) itemView.findViewById(R.id.iv_sub_topic_image_free_banner);


        }
    }
}
