package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 30/10/17.
 */

public class ConfigSettingBean implements Parcelable {
    @SerializedName("soundHeading")
    @Expose
    private String soundHeading;
    @SerializedName("backgroundsoundHeading")
    @Expose
    private String backgroundsoundHeading;
    @SerializedName("effectsoundHeading")
    @Expose
    private String effectsoundHeading;
    @SerializedName("logoutBtn")
    @Expose
    private String logoutBtn;
    @SerializedName("restore")
    @Expose
    private String restore;
    @SerializedName("RestoreSuccessFully")
    @Expose
    private String restoreSuccessFully;
    @SerializedName("RestoreFailure")
    @Expose
    private String restoreFailure;
    @SerializedName("RestoreOkBtn")
    @Expose
    private String restoreOkBtn;

    public String getSoundHeading() {
        return soundHeading;
    }

    public void setSoundHeading(String soundHeading) {
        this.soundHeading = soundHeading;
    }

    public String getBackgroundsoundHeading() {
        return backgroundsoundHeading;
    }

    public void setBackgroundsoundHeading(String backgroundsoundHeading) {
        this.backgroundsoundHeading = backgroundsoundHeading;
    }

    public String getEffectsoundHeading() {
        return effectsoundHeading;
    }

    public void setEffectsoundHeading(String effectsoundHeading) {
        this.effectsoundHeading = effectsoundHeading;
    }

    public String getLogoutBtn() {
        return logoutBtn;
    }

    public void setLogoutBtn(String logoutBtn) {
        this.logoutBtn = logoutBtn;
    }

    public String getRestore() {
        return restore;
    }

    public void setRestore(String restore) {
        this.restore = restore;
    }

    public String getRestoreSuccessFully() {
        return restoreSuccessFully;
    }

    public void setRestoreSuccessFully(String restoreSuccessFully) {
        this.restoreSuccessFully = restoreSuccessFully;
    }

    public String getRestoreFailure() {
        return restoreFailure;
    }

    public void setRestoreFailure(String restoreFailure) {
        this.restoreFailure = restoreFailure;
    }

    public String getRestoreOkBtn() {
        return restoreOkBtn;
    }

    public void setRestoreOkBtn(String restoreOkBtn) {
        this.restoreOkBtn = restoreOkBtn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.soundHeading);
        dest.writeString(this.backgroundsoundHeading);
        dest.writeString(this.effectsoundHeading);
        dest.writeString(this.logoutBtn);
        dest.writeString(this.restore);
        dest.writeString(this.restoreSuccessFully);
        dest.writeString(this.restoreFailure);
        dest.writeString(this.restoreOkBtn);
    }

    public ConfigSettingBean() {
    }

    protected ConfigSettingBean(Parcel in) {
        this.soundHeading = in.readString();
        this.backgroundsoundHeading = in.readString();
        this.effectsoundHeading = in.readString();
        this.logoutBtn = in.readString();
        this.restore = in.readString();
        this.restoreSuccessFully = in.readString();
        this.restoreFailure = in.readString();
        this.restoreOkBtn = in.readString();
    }

    public static final Parcelable.Creator<ConfigSettingBean> CREATOR = new Parcelable.Creator<ConfigSettingBean>() {
        @Override
        public ConfigSettingBean createFromParcel(Parcel source) {
            return new ConfigSettingBean(source);
        }

        @Override
        public ConfigSettingBean[] newArray(int size) {
            return new ConfigSettingBean[size];
        }
    };
}
