package com.turtlediary.www.beans;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pratibha on 14/11/17.
 */

public class ProductPriceBean implements Parcelable {
    String status,message;
   List<ProductBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProductBean> getData() {
        return data;
    }

    public void setData(List<ProductBean> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.message);
        dest.writeList(this.data);
    }

    public ProductPriceBean() {
    }

    protected ProductPriceBean(Parcel in) {
        this.status = in.readString();
        this.message = in.readString();
        this.data = new ArrayList<ProductBean>();
        in.readList(this.data, ProductBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ProductPriceBean> CREATOR = new Parcelable.Creator<ProductPriceBean>() {
        @Override
        public ProductPriceBean createFromParcel(Parcel source) {
            return new ProductPriceBean(source);
        }

        @Override
        public ProductPriceBean[] newArray(int size) {
            return new ProductPriceBean[size];
        }
    };
}
