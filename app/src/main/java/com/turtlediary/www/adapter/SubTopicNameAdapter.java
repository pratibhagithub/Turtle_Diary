package com.turtlediary.www.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.turtlediary.www.R;
import com.turtlediary.www.beans.SubTopicListBean;
import com.turtlediary.www.interfaces.OnSubTopicNameClickListner;

import java.util.List;

/**
 * Created by pratibha on 2/11/17.
 */

public class SubTopicNameAdapter extends RecyclerView.Adapter<SubTopicNameAdapter.ItemViewHolder> {

    private List<SubTopicListBean> topicNameList;
    private Activity activity;
    OnSubTopicNameClickListner onSubTopicNameClickListner;
    //public static int selected_item = 0;


    public SubTopicNameAdapter(Activity activity, List<SubTopicListBean> topicNameList, OnSubTopicNameClickListner onSubTopicNameClickListner) {
        this.topicNameList = topicNameList;
        this.activity = activity;
        this.onSubTopicNameClickListner = onSubTopicNameClickListner;
        this.topicNameList= topicNameList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_topic_name_list_item, viewGroup, false);
        SubTopicNameAdapter.ItemViewHolder viewHolder = new SubTopicNameAdapter.ItemViewHolder(view);
        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.tvTopicNAme.setText(topicNameList.get(position).getTopicName());


        if(topicNameList.get(position).isTopicSelected())
        {
            holder.linearLayoutNameContainer.setBackgroundColor((gettingColor(R.color.blue_bottom_bar_bg)));
        }else
        {
            holder.linearLayoutNameContainer.setBackgroundColor((gettingColor(R.color.light_gray)));

        }
        holder.tvTopicNAme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onSubTopicNameClickListner.topicNameClicked(topicNameList.get(position));

            }
        });
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return topicNameList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvTopicNAme;
        LinearLayout linearLayoutNameContainer;
        public ItemViewHolder(View itemView) {
            super(itemView);
             tvTopicNAme = (TextView) itemView.findViewById(R.id.tv_topic_name);
            linearLayoutNameContainer = (LinearLayout) itemView.findViewById(R.id.ll_name_container);
        }
    }
    private int gettingColor(int colorID) {
        return ContextCompat.getColor(activity, colorID);
    }
}
