package com.kamil184.newmotivate.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.kamil184.newmotivate.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static com.kamil184.newmotivate.util.Constants.NO;

public class ToDoItem implements Parcelable {

    private boolean hasReminder;
    private boolean hasDate;
    private boolean hasQuantity = false;
    private String title;
    private String note = "";
    private Repeat repeat;
    private int quantityNumber;
    private int quantityTextPosition;
    private Calendar calendar;
    private int priority = NO;
    private List<Step> steps = new ArrayList<>();
    private boolean isCompleted;
    private int repeatSelected = R.id.repeat_no;
    private List<Tag> tags = new ArrayList<>();
    private UUID uuid;

    public ToDoItem() {
        calendar = Calendar.getInstance();
        uuid = UUID.randomUUID();
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean hasReminder() {
        return hasReminder;
    }

    public void setHasReminder(boolean hasReminder) {
        this.hasReminder = hasReminder;
    }

    public boolean hasDate() {
        return hasDate;
    }

    public void setHasDate(boolean hasDate) {
        this.hasDate = hasDate;
    }

    public boolean hasQuantity() {
        return hasQuantity;
    }

    public void setHasQuantity(boolean hasQuantity) {
        this.hasQuantity = hasQuantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Repeat getRepeat() {
        return repeat;
    }

    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }

    public int getQuantityNumber() {
        return quantityNumber;
    }

    public void setQuantityNumber(int quantityNumber) {
        this.quantityNumber = quantityNumber;
    }

    public int getQuantityTextPosition() {
        return quantityTextPosition;
    }

    public void setQuantityTextPosition(int quantityTextPosition) {
        this.quantityTextPosition = quantityTextPosition;
        hasQuantity = true;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getRepeatSelected() {
        return repeatSelected;
    }

    public void setRepeatSelected(int repeatSelected) {
        this.repeatSelected = repeatSelected;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.hasReminder ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasDate ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasQuantity ? (byte) 1 : (byte) 0);
        dest.writeString(this.title);
        dest.writeString(this.note);
        dest.writeInt(this.repeat == null ? -1 : this.repeat.ordinal());
        dest.writeInt(this.quantityNumber);
        dest.writeInt(this.quantityTextPosition);
        dest.writeSerializable(this.calendar);
        dest.writeInt(this.priority);
        dest.writeList(this.steps);
        dest.writeByte(this.isCompleted ? (byte) 1 : (byte) 0);
        dest.writeInt(this.repeatSelected);
        dest.writeList(this.tags);
        dest.writeSerializable(this.uuid);
    }

    protected ToDoItem(Parcel in) {
        this.hasReminder = in.readByte() != 0;
        this.hasDate = in.readByte() != 0;
        this.hasQuantity = in.readByte() != 0;
        this.title = in.readString();
        this.note = in.readString();
        int tmpRepeat = in.readInt();
        this.repeat = tmpRepeat == -1 ? null : Repeat.values()[tmpRepeat];
        this.quantityNumber = in.readInt();
        this.quantityTextPosition = in.readInt();
        this.calendar = (Calendar) in.readSerializable();
        this.priority = in.readInt();
        this.steps = new ArrayList<Step>();
        in.readList(this.steps, Step.class.getClassLoader());
        this.isCompleted = in.readByte() != 0;
        this.repeatSelected = in.readInt();
        this.tags = new ArrayList<Tag>();
        in.readList(this.tags, Tag.class.getClassLoader());
        this.uuid = (UUID) in.readSerializable();
    }

    public static final Creator<ToDoItem> CREATOR = new Creator<ToDoItem>() {
        @Override
        public ToDoItem createFromParcel(Parcel source) {
            return new ToDoItem(source);
        }

        @Override
        public ToDoItem[] newArray(int size) {
            return new ToDoItem[size];
        }
    };
}