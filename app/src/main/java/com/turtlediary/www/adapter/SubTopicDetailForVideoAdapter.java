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
import com.turtlediary.www.activities.VideoContentActivity;
import com.turtlediary.www.beans.VideoBean;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.SoundEffect;
import com.turtlediary.www.utilities.Util;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by pratibha on 7/11/17.
 */

public class SubTopicDetailForVideoAdapter extends RecyclerView.Adapter<SubTopicDetailForVideoAdapter.ItemViewHolder> {

    private List<VideoBean> videoBeanList;
    private Activity activity;
    String sceenNameForEventAnaytics;

    public SubTopicDetailForVideoAdapter(Activity activity, List<VideoBean> videoBeanList, String sceenNameForEventAnaytics) {
        this.videoBeanList = videoBeanList;
        this.activity = activity;
        this.sceenNameForEventAnaytics=sceenNameForEventAnaytics;

    }

    @Override
    public SubTopicDetailForVideoAdapter.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_topic_detail_list_item, viewGroup, false);
        SubTopicDetailForVideoAdapter.ItemViewHolder viewHolder = new SubTopicDetailForVideoAdapter.ItemViewHolder(view);
        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubTopicDetailForVideoAdapter.ItemViewHolder holder,final int position) {
        holder.  tvVideoNAme.setText(videoBeanList.get(position).getVideoName());
        holder.ivFreeBannerImage.setVisibility(View.GONE);

        Glide.with(activity).load(videoBeanList.get(position).getImageName()).into(holder.ivVideoImage);
        holder.linearLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.setGoogleAnayticsData(activity,sceenNameForEventAnaytics,videoBeanList.get(position).getVideoName());
                Util.setFirebaseAnalyticsLog(activity,sceenNameForEventAnaytics,videoBeanList.get(position).getVideoName());
                if (Preferences.getEffectSoundEnability(getApplicationContext()))
                    SoundEffect.getInstance().playSound(activity, false); // true bcz , it is not  a home/back button is not clicked

                Intent intent = new Intent(activity, VideoContentActivity.class);
                intent.putExtra(AppConstants.KEY_SLUG, videoBeanList.get(position).getSlug());
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
        return videoBeanList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivFreeBannerImage;
        TextView  tvVideoNAme;
        ImageView ivVideoImage;
        LinearLayout linearLayoutContainer;


        public ItemViewHolder(View itemView) {
            super(itemView);
            linearLayoutContainer = (LinearLayout) itemView.findViewById(R.id.ll_container);

            tvVideoNAme = (TextView) itemView.findViewById(R.id.tv_sub_topic_name);
            ivVideoImage = (ImageView) itemView.findViewById(R.id.iv_sub_topic_image);
            ivFreeBannerImage = (ImageView) itemView.findViewById(R.id.iv_sub_topic_image_free_banner);

        }
    }
}
