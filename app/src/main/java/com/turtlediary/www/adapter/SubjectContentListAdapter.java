package com.turtlediary.www.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.turtlediary.www.R;
import com.turtlediary.www.beans.SubjectContentListItemBean;
import com.turtlediary.www.interfaces.OnLessonTopicClickListner;

import java.util.List;

/**
 * Created by arvind on 1/11/17.
 */

public class SubjectContentListAdapter extends RecyclerView.Adapter<SubjectContentListAdapter.SubjectContentListViewHolder> {


    private List<SubjectContentListItemBean> subjectTopicList;
    private Context mContext;
    private OnLessonTopicClickListner onLessonTopicClickListner;


    public SubjectContentListAdapter(Context context, List<SubjectContentListItemBean> subjectTopicList, OnLessonTopicClickListner onLessonTopicClickListner) {
        this.mContext = context;
        this.subjectTopicList = subjectTopicList;
        this.onLessonTopicClickListner = onLessonTopicClickListner;

    }

    @Override
    public SubjectContentListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.subject_content_list_item, viewGroup, false);
        SubjectContentListViewHolder viewHolder = new SubjectContentListViewHolder(view);
        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubjectContentListViewHolder holder, final int position) {
        loadImages(holder.mGameIcon, subjectTopicList.get(position).getTopicImage());
        holder.mGameIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLessonTopicClickListner.onLessonTopicClicked(subjectTopicList.get(position));
            }
        });

    }

    private void loadImages(ImageView img, String imgUrl) {
        Glide.with(mContext).load(imgUrl).into(img);
    }

    @Override
    public int getItemCount() {
        return subjectTopicList.size();
    }

    public class SubjectContentListViewHolder extends RecyclerView.ViewHolder {
        private ImageView mGameIcon;


        public SubjectContentListViewHolder(View itemView) {
            super(itemView);

            mGameIcon = (ImageView) itemView.findViewById(R.id.img_game);


        }
    }


}