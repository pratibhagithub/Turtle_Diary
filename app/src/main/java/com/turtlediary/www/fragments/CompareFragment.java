package com.turtlediary.www.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.turtlediary.www.R;
import com.turtlediary.www.beans.ConfigBean;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Util;

/**
 * Created by pratibha on 14/11/17.
 */

public class CompareFragment extends Fragment {
    private ImageView imgCompare;
    private TextView textViewStart;
    private TextView textViewCondition;
    private TextView textViewPolicy;
    ConfigBean configBean;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_compare, container, false);
        configBean = getArguments().getParcelable(AppConstants.KEY_BASE_BEAN);
        setWidgets(view);
        setListners();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    // this we are doing , beacause "onAttach(Context context)", this function is not calling for API <23 ,
    // and hence we are not able to get signUpFragmentChangeListner and app was getting crash
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    private void setWidgets(View view) {
        imgCompare = (ImageView) view.findViewById(R.id.compare_image);
        textViewStart = (TextView) view.findViewById(R.id.start_today_tv);
        textViewCondition = (TextView) view.findViewById(R.id.tv_condition);
        textViewPolicy = (TextView) view.findViewById(R.id.tv_policys);
        textViewStart.setText(configBean.getNewLoginScreen().getComparePage().getBtnA());
        textViewCondition.setText(configBean.getNewLoginScreen().getComparePage().getConditions());
        textViewPolicy.setText(configBean.getNewLoginScreen().getComparePage().getPolicy());
    }

    private void setListners() {
        Glide.with(getActivity()).load(configBean.getNewLoginScreen().getComparePage().getLoginoptionscreen()).into(imgCompare);

        textViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        textViewCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.callWebview(getActivity(), configBean.getNewLoginScreen().getComparePage().getConditionsUrl());
            }
        });
        textViewPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.callWebview(getActivity(), configBean.getNewLoginScreen().getComparePage().getPolicyUrl());
            }
        });
    }

}
