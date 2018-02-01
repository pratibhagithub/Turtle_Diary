package com.turtlediary.www.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.turtlediary.www.R;
import com.turtlediary.www.adapter.SubTopicNameAdapter;
import com.turtlediary.www.beans.SubTopicBean;
import com.turtlediary.www.beans.SubTopicListBean;
import com.turtlediary.www.interfaces.OnSubTopicEventListener;
import com.turtlediary.www.interfaces.OnSubTopicNameClickListner;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.SoundEffect;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SubTopicNameFragment extends Fragment {

    RecyclerView rvTopicName;
    OnSubTopicEventListener mCallback;
    private   SubTopicNameAdapter subTopicNameAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_topic_name, container, false);
        initWidgets(view);
        SubTopicBean topicbean=getArguments().getParcelable(AppConstants.SUBTOPIC_BEAN);
        List<SubTopicListBean> topicNameList=fillinngList(topicbean);
        setAdapter(topicNameList);
        return view;

    }

    // this we are doing , beacause "onAttach(Context context)", this function is not calling for API <23 ,
    // and hence we are not able to get signUpFragmentChangeListner and app was getting crash
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnSubTopicEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


 @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnSubTopicEventListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
    private void initWidgets(View view)
    {
        rvTopicName=(RecyclerView) view.findViewById(R.id.rv_topic_name);

    }

    private List<SubTopicListBean> fillinngList( SubTopicBean topicbean)
    {
        List<SubTopicListBean> sunTopic=topicbean.getSubtopic();
        return  sunTopic;
    }
    private void setAdapter(final List<SubTopicListBean> nameList)
    {
         subTopicNameAdapter =new SubTopicNameAdapter(getActivity(), nameList, new OnSubTopicNameClickListner() {
            @Override
            public void topicNameClicked(SubTopicListBean subTopicListBean) {
                mCallback.onSubTopicNameClicked(subTopicListBean);
                if(Preferences.getEffectSoundEnability(getApplicationContext()))
                    SoundEffect.getInstance().playSound(getActivity(),false); // true bcz , it is  not  a home/back button is not clicked
                for(int i=0;i<nameList.size();i++ )
                {
                    if(nameList.get(i).equals(subTopicListBean))
                    {
                        nameList.get(i).setTopicSelected(true);

                    }else
                    {
                        nameList.get(i).setTopicSelected(false);                    }
                }
                subTopicNameAdapter.notifyDataSetChanged();

            }

        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvTopicName.setLayoutManager(mLayoutManager);
        rvTopicName.setAdapter(subTopicNameAdapter);
    }
}


