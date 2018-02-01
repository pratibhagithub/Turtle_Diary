package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 12/12/17.
 */

public class PrintableContentList implements Parcelable {
    @SerializedName("coloring-sheets")
    @Expose
    private PrintableContentData coloring_sheets;

    @SerializedName("kids-arts-and-crafts")
    @Expose
    private PrintableContentData kids_arts_and_crafts;


    @SerializedName("worksheets")
    @Expose
    private PrintableContentData worksheets;


    @SerializedName("worksheet-generator")
    @Expose
    private PrintableContentData worksheet_generator;

    public PrintableContentData getColoring_sheets() {
        return coloring_sheets;
    }

    public void setColoring_sheets(PrintableContentData coloring_sheets) {
        this.coloring_sheets = coloring_sheets;
    }

    public PrintableContentData getKids_arts_and_crafts() {
        return kids_arts_and_crafts;
    }

    public void setKids_arts_and_crafts(PrintableContentData kids_arts_and_crafts) {
        this.kids_arts_and_crafts = kids_arts_and_crafts;
    }

    public PrintableContentData getWorksheets() {
        return worksheets;
    }

    public void setWorksheets(PrintableContentData worksheets) {
        this.worksheets = worksheets;
    }

    public PrintableContentData getWorksheet_generator() {
        return worksheet_generator;
    }

    public void setWorksheet_generator(PrintableContentData worksheet_generator) {
        this.worksheet_generator = worksheet_generator;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.coloring_sheets, flags);
        dest.writeParcelable(this.kids_arts_and_crafts, flags);
        dest.writeParcelable(this.worksheets, flags);
        dest.writeParcelable(this.worksheet_generator, flags);
    }

    public PrintableContentList() {
    }

    protected PrintableContentList(Parcel in) {
        this.coloring_sheets = in.readParcelable(PrintableContentData.class.getClassLoader());
        this.kids_arts_and_crafts = in.readParcelable(PrintableContentData.class.getClassLoader());
        this.worksheets = in.readParcelable(PrintableContentData.class.getClassLoader());
        this.worksheet_generator = in.readParcelable(PrintableContentData.class.getClassLoader());
    }

    public static final Parcelable.Creator<PrintableContentList> CREATOR = new Parcelable.Creator<PrintableContentList>() {
        @Override
        public PrintableContentList createFromParcel(Parcel source) {
            return new PrintableContentList(source);
        }

        @Override
        public PrintableContentList[] newArray(int size) {
            return new PrintableContentList[size];
        }
    };
}


