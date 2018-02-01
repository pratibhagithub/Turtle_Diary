package com.turtlediary.www.custonViews;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.turtlediary.www.R;
import com.turtlediary.www.activities.SigninActivity;
import com.turtlediary.www.applicationn.MyApplication;
import com.turtlediary.www.beans.BaseBean;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Util;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by pratibha on 14/11/17.
 */

public class CustomDialog extends DialogFragment {

    public static void loginViewDialog(final Activity activity) {
        TextView tvHeader, tvMsg, tvCancel, tvOk;
        MyApplication myApplication = (MyApplication)getApplicationContext();
        final  BaseBean contentTypeBean= myApplication.getBaseBean();
         android.support.v7.app.AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //, android.R.style.Theme_DeviceDefault_Light_Dialog
            builder = new android.support.v7.app.AlertDialog.Builder(activity, R.style.AlertDialogTheme);
        } else {
            builder = new android.support.v7.app.AlertDialog.Builder(activity, R.style.AlertDialogTheme
            );
        }
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.login_view_dialog, null);
        tvHeader = (TextView) dialogView.findViewById(R.id.tv_ques_view_header);
        tvMsg = (TextView) dialogView.findViewById(R.id.tv_msg);
        tvCancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
        tvOk = (TextView) dialogView.findViewById(R.id.tvOk);
        tvMsg.setText(contentTypeBean.getConfig().getTDregisterMsgNav());
        tvOk.setText(contentTypeBean.getConfig().getTDloginBtnMsg());
        tvCancel.setText(contentTypeBean.getConfig().getUpdateAlertMsg().getCancelBtn());

        tvHeader.setText(contentTypeBean.getConfig().getAppUpdatedbtnText());
        builder.setCancelable(false);
        builder.setView(dialogView);
        final android.support.v7.app.AlertDialog dialog = builder.show();
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                Intent intent = new Intent(activity, SigninActivity.class);
                intent.putExtra(AppConstants.CONFIG_BEAN, contentTypeBean.getConfig());
                intent.putExtra(AppConstants.sCALLEDFROM, AppConstants.sDialog);
                activity.startActivity(intent);
            }
        });

    }


    public static void appUpdateDialog(final Activity activity, final boolean isForceUpdate) {
        TextView tvHeader, tvMsg, tvCancel, tvOk;
        MyApplication myApplication = (MyApplication)getApplicationContext();
        final  BaseBean contentTypeBean= myApplication.getBaseBean();
        android.support.v7.app.AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //, android.R.style.Theme_DeviceDefault_Light_Dialog
            builder = new android.support.v7.app.AlertDialog.Builder(activity, R.style.AlertDialogTheme);
        } else {
            builder = new android.support.v7.app.AlertDialog.Builder(activity, R.style.AlertDialogTheme
            );
        }
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.login_view_dialog, null);
        tvHeader = (TextView) dialogView.findViewById(R.id.tv_ques_view_header);
        tvMsg = (TextView) dialogView.findViewById(R.id.tv_msg);
        tvCancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
        tvOk = (TextView) dialogView.findViewById(R.id.tvOk);
        tvOk.setText(contentTypeBean.getConfig().getUpdateAlertMsg().getOkBtn());
        tvCancel.setText(contentTypeBean.getConfig().getUpdateAlertMsg().getCancelBtn());
        tvHeader.setText(contentTypeBean.getConfig().getUpdateAlertMsg().getUPdateheading());
        if(isForceUpdate)
            tvMsg.setText(contentTypeBean.getConfig().getUpdateAlertMsg().getForceUpgradeMsg());
        else
            tvMsg.setText(contentTypeBean.getConfig().getUpdateAlertMsg().getUPdateMsg());

        builder.setCancelable(false);
        builder.setView(dialogView);
        final android.support.v7.app.AlertDialog dialog = builder.show();
        dialog.setCancelable(false);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isForceUpdate){
                    activity.finish();
                }
                    dialog.cancel();
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.openAppInPlayStore(activity);
                dialog.cancel();
                if(isForceUpdate){
                    activity.finish();
                }
            }
        });

    }




}