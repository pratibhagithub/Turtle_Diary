package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arvind on 31/10/17.
 */

public class GameListModel implements Parcelable {

    @SerializedName("gameId")
    @Expose
    private String gameId;
    @SerializedName("imageName")
    @Expose
    private String imageName;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("gameName")
    @Expose
    private String gameName;
    @SerializedName("groupSlug")
    @Expose
    private String groupSlug;
    @SerializedName("isGroup")
    @Expose
    private Boolean isGroup;
    @SerializedName("gameCategory")
    @Expose
    private String gameCategory;
    @SerializedName("gameVersion")
    @Expose
    private String gameVersion;
    @SerializedName("newTag")
    @Expose
    private Boolean newTag;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("isBlock")
    @Expose
    private Boolean isBlock;
    @SerializedName("isView")
    @Expose
    private String isView;
    @SerializedName("viewUrl")
    @Expose
    private String viewUrl;





    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGroupSlug() {
        return groupSlug;
    }

    public void setGroupSlug(String groupSlug) {
        this.groupSlug = groupSlug;
    }

    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    public String getGameCategory() {
        return gameCategory;
    }

    public void setGameCategory(String gameCategory) {
        this.gameCategory = gameCategory;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public Boolean getNewTag() {
        return newTag;
    }

    public void setNewTag(Boolean newTag) {
        this.newTag = newTag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Boolean isBlock) {
        this.isBlock = isBlock;
    }

    public String getIsView() {
        return isView;
    }

    public void setIsView(String isView) {
        this.isView = isView;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gameId);
        dest.writeString(this.imageName);
        dest.writeString(this.slug);
        dest.writeString(this.gameName);
        dest.writeString(this.groupSlug);
        dest.writeValue(this.isGroup);
        dest.writeString(this.gameCategory);
        dest.writeString(this.gameVersion);
        dest.writeValue(this.newTag);
        dest.writeString(this.type);
        dest.writeValue(this.isBlock);
        dest.writeString(this.isView);
        dest.writeString(this.viewUrl);
    }

    public GameListModel() {
    }

    protected GameListModel(Parcel in) {
        this.gameId = in.readString();
        this.imageName = in.readString();
        this.slug = in.readString();
        this.gameName = in.readString();
        this.groupSlug = in.readString();
        this.isGroup = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.gameCategory = in.readString();
        this.gameVersion = in.readString();
        this.newTag = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.type = in.readString();
        this.isBlock = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isView = in.readString();
        this.viewUrl = in.readString();
    }

    public static final Creator<GameListModel> CREATOR = new Creator<GameListModel>() {
        @Override
        public GameListModel createFromParcel(Parcel source) {
            return new GameListModel(source);
        }

        @Override
        public GameListModel[] newArray(int size) {
            return new GameListModel[size];
        }
    };
}
