package com.kamil184.newmotivate.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {
    private boolean isChecked;
    private String text;

    public Step(boolean isChecked, String text) {
        this.isChecked = isChecked;
        this.text = text;
    }

    public Step() {
        isChecked = false;
        text = "";
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        dest.writeString(this.text);
    }

    protected Step(Parcel in) {
        this.isChecked = in.readByte() != 0;
        this.text = in.readString();
    }

    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
}
