package com.turtlediary.www.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.turtlediary.www.R;
import com.turtlediary.www.activities.LessonContentActivity;
import com.turtlediary.www.beans.LessonBean;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.SoundEffect;
import com.turtlediary.www.utilities.Util;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by pratibha on 7/11/17.
 */

public class SubTopicDetailForLessonAdapter extends RecyclerView.Adapter<SubTopicDetailForLessonAdapter.ItemViewHolder> {
    private List<LessonBean> lessonBeanList;
    private Activity activity;
    private String sceenNameForEventAnaytics;

    public SubTopicDetailForLessonAdapter(Activity activity, List<LessonBean> lessonBeanList, String sceenNameForEventAnaytics) {
        this.lessonBeanList = lessonBeanList;
        this.activity = activity;
        this.sceenNameForEventAnaytics = sceenNameForEventAnaytics;
    }

    @Override
    public SubTopicDetailForLessonAdapter.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_topic_detail_list_item, viewGroup, false);
        SubTopicDetailForLessonAdapter.ItemViewHolder viewHolder = new SubTopicDetailForLessonAdapter.ItemViewHolder(view);
        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubTopicDetailForLessonAdapter.ItemViewHolder holder, final int position) {
        holder.tvLessonName.setText(lessonBeanList.get(position).getLessonName());
        holder.ivFreeBannerImage.setVisibility(View.GONE);

        Glide.with(activity).load(lessonBeanList.get(position).getImageName()).into(holder.ivLessonImage);
        holder.linearLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Preferences.getEffectSoundEnability(getApplicationContext()))
                    SoundEffect.getInstance().playSound(activity, false); // true bcz , it is not  a home/back button is not clicked
                Util.setGoogleAnayticsData(activity, sceenNameForEventAnaytics, lessonBeanList.get(position).getLessonName());
                Util.setFirebaseAnalyticsLog(activity, sceenNameForEventAnaytics, lessonBeanList.get(position).getLessonName());
                Intent intent = new Intent(activity, LessonContentActivity.class);
                intent.putExtra(AppConstants.KEY_SLUG, lessonBeanList.get(position).getSlug());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return lessonBeanList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvLessonName;
        ImageView ivLessonImage;
        ImageView ivFreeBannerImage;
        LinearLayout linearLayoutContainer;

        public ItemViewHolder(View itemView) {
            super(itemView);
            linearLayoutContainer = (LinearLayout) itemView.findViewById(R.id.ll_container);
            tvLessonName = (TextView) itemView.findViewById(R.id.tv_sub_topic_name);
            ivLessonImage = (ImageView) itemView.findViewById(R.id.iv_sub_topic_image);
            ivFreeBannerImage = (ImageView) itemView.findViewById(R.id.iv_sub_topic_image_free_banner);
        }
    }
}
