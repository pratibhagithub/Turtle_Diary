package com.turtlediary.www.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turtlediary.www.R;
import com.turtlediary.www.beans.ConfigInformationBean;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Util;

import static com.turtlediary.www.R.id.tv_version_number;

/**
 * Created by pratibha on 24/10/17.
 */

public class InformationFragment extends Fragment {
    private TextView tvLeftHeader, tvRightHeader, tvLeftInfo, tvRightInfo, tvAppVersion,tvInfoHeading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information,
                container, false);
        setWidgets(view);
        ConfigInformationBean informationBeann=getArguments().getParcelable(AppConstants.INFORMATION_BEAN);
        setText(informationBeann);
        setListners();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    private void setWidgets(View view) {
        tvLeftHeader = (TextView) view.findViewById(R.id.tv_left_heading);
        tvInfoHeading = (TextView) view.findViewById(R.id.info_header);
        tvRightHeader = (TextView) view.findViewById(R.id.tv_right_heading);
        tvLeftInfo = (TextView) view.findViewById(R.id.tv_left_information);
        tvRightInfo = (TextView) view.findViewById(R.id.tv_right_info);
        tvAppVersion = (TextView) view.findViewById(tv_version_number);

    }

    private void setListners() {

    }
    private void setText(ConfigInformationBean informationBean)
    {
        tvInfoHeading.setText(informationBean.getInfoHeadingLabel());
        tvLeftHeader.setText(informationBean.getLeftHeading());
        tvRightHeader.setText(informationBean.getRightLabel());
        tvLeftInfo.setText(informationBean.getLeftDesc());
        tvRightInfo.setText(informationBean.getRightDesc());
        tvAppVersion.setText(informationBean.getAppVersion() + " " + Util.getApplicationVersion(getActivity()));
    }
}
