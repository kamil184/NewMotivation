package com.kamil184.newmotivate.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.kamil184.newmotivate.util.ColorGenerator;

public class Tag implements Parcelable {

    public static final String TAG_JSON_FILENAME = "Tags.json";

    private String text;
    private int color;

    public Tag(String text){
        this.text = text;
        /*int R = (int)(Math.random()*256);
        int G = (int)(Math.random()*256);
        int B = (int)(Math.random()*256);
        color = Color.rgb(R, G, B);*/
        color = new ColorGenerator().getRandomColor();
    }

    public Tag(String text, int color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeInt(this.color);
    }

    protected Tag(Parcel in) {
        this.text = in.readString();
        this.color = in.readInt();
    }

    public static final Parcelable.Creator<Tag> CREATOR = new Parcelable.Creator<Tag>() {
        @Override
        public Tag createFromParcel(Parcel source) {
            return new Tag(source);
        }

        @Override
        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };

}
