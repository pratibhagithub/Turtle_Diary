package com.turtlediary.www.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turtlediary.www.R;
import com.turtlediary.www.beans.AccountPage;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;

/**
 * Created by pratibha on 24/10/17.
 */

public class AccountFragment extends Fragment {
    private TextView tvUserName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account,
                container, false);
        AccountPage accountPage= getArguments().getParcelable(AppConstants.ACCOUNT_BEAN);
        setWidgets(view);
        if (accountPage != null)
            setText(accountPage);
        return view;

    }
    private void setWidgets(View view) {
        tvUserName = (TextView) view.findViewById(R.id.tv_username);


    }

    private void setText(AccountPage accountPage ) {
        tvUserName.setText(accountPage.getDescTxt()+" "+Preferences.getUserNAme(getActivity()));

    }
}