package com.turtlediary.www.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turtlediary.www.R;
import com.turtlediary.www.activities.ContentWebviewActivity;
import com.turtlediary.www.adapter.SubTopicDetailForLessonAdapter;
import com.turtlediary.www.adapter.SubTopicDetailForQuizAdapter;
import com.turtlediary.www.adapter.SubTopicDetailForVideoAdapter;
import com.turtlediary.www.beans.PracticBean;
import com.turtlediary.www.beans.SubTopicBean;
import com.turtlediary.www.custonViews.CustomDialog;
import com.turtlediary.www.interfaces.OnQuizItemClickedListner;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.SoundEffect;
import com.turtlediary.www.utilities.Util;

import static com.facebook.FacebookSdk.getApplicationContext;


public class SubTopicDetailFragment extends Fragment {
    private RecyclerView rvTopicDetail;
    private int mCalledFor;
    private TextView tvPracticeHeader;
    private String eventName;
    private SubTopicBean subTopicBeen;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_topic_detail, container, false);
        subTopicBeen = getArguments().getParcelable(AppConstants.SUBTOPIC_BEAN);
        eventName = getArguments().getString(AppConstants.HEADER);
        mCalledFor = getArguments().getInt(AppConstants.CALLED_FOR, 0);
        initWidgets(view);
        tvPracticeHeader.setText(getArguments().getString(AppConstants.CONTENT_TYPE));
        setAdapter(subTopicBeen);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initWidgets(View view) {
        rvTopicDetail = (RecyclerView) view.findViewById(R.id.rv_topic_detail);
        tvPracticeHeader = (TextView) view.findViewById(R.id.practice_header);
        if (mCalledFor == AppConstants.QUIZ)
            tvPracticeHeader.setBackgroundColor(Util.gettingColor(getActivity(), R.color.green_quizz_header));
        if (mCalledFor == AppConstants.VIDEO)
            tvPracticeHeader.setBackgroundColor(Util.gettingColor(getActivity(), R.color.orange_video_header));
        if (mCalledFor == AppConstants.LESSON)
            tvPracticeHeader.setBackgroundColor(Util.gettingColor(getActivity(), R.color.blue_bottom_bar_bg));

    }

    private void setAdapter(SubTopicBean subTopicBeen) {
        int numberOfColumns = 2;
        if (getResources().getBoolean(R.bool.isTablet)) {
            numberOfColumns = 2;
        } else {
            numberOfColumns = 1;
        }
        if (mCalledFor == AppConstants.QUIZ) {
            SubTopicDetailForQuizAdapter subTopicDetailForQuizAdapter = new SubTopicDetailForQuizAdapter(getActivity(), subTopicBeen.getPractic(), eventName, new OnQuizItemClickedListner() {
                @Override
                public void onQuizClicked(PracticBean practicBean) {
                    if (Preferences.getEffectSoundEnability(getApplicationContext()))
                        SoundEffect.getInstance().playSound(getActivity(), false); // true bcz , it is  not  a home/back button is not clicked

                    Log.e("Hello", "" + practicBean.getWebView());
                    //to call login Dialog
                    if (!Util.isPremeiumMember(getActivity())) {
                        CustomDialog.loginViewDialog(getActivity());
                       /* if(practicBean.isBlock()) {
                            CustomDialog.loginViewDialog(getActivity());

                        }else
                        {
                            if (practicBean.getWebView() != null && !TextUtils.isEmpty(practicBean.getWebView())) {
                                Intent intent = new Intent(getActivity(), ContentWebviewActivity.class);
                                intent.putExtra(AppConstants.URL_TO_LOAD, practicBean.getWebView());
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "URL is null", Toast.LENGTH_SHORT).show();
                            }

                        }*/
                    } else {
                        if (practicBean.getWebView() != null && !TextUtils.isEmpty(practicBean.getWebView())) {
                            Intent intent = new Intent(getActivity(), ContentWebviewActivity.class);
                            intent.putExtra(AppConstants.URL_TO_LOAD, practicBean.getWebView());
                            startActivity(intent);
                        } else {
                          //  Toast.makeText(getApplicationContext(), "URL is null", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            rvTopicDetail.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
            rvTopicDetail.setAdapter(subTopicDetailForQuizAdapter);
        }
        if (mCalledFor == AppConstants.LESSON) {
            SubTopicDetailForLessonAdapter subTopicDetailForLessonAdapter = new SubTopicDetailForLessonAdapter(getActivity(), subTopicBeen.getLesson(), eventName);
            rvTopicDetail.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
            rvTopicDetail.setAdapter(subTopicDetailForLessonAdapter);
        }
        if (mCalledFor == AppConstants.VIDEO) {
            SubTopicDetailForVideoAdapter subTopicDetailForVideoAdapter = new SubTopicDetailForVideoAdapter(getActivity(), subTopicBeen.getVideo(), eventName);
            rvTopicDetail.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
            rvTopicDetail.setAdapter(subTopicDetailForVideoAdapter);
        }
    }
}
