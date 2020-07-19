package com.kamil184.newmotivate.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kamil184.newmotivate.R;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.kamil184.newmotivate.util.Constants.NO;
import org.greenrobot.greendao.annotation.Generated;

@Entity(generateConstructors = false, generateGettersSetters = false)
public class ToDoItem implements Parcelable {

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
    @Id(autoincrement = true)
    private long id;
    private boolean hasReminder;
    private boolean hasDate;
    private boolean hasQuantity = false;
    @NotNull
    private String title;
    private String note = "";
    @Convert(converter = Repeat.RepeatConverter.class, columnType = Integer.class)
    private Repeat repeat;
    private int quantityNumber;
    private int quantityTextPosition;
    @Convert(converter = CalendarConverter.class, columnType = Date.class)
    private Calendar calendar;
    private int priority = NO;
    @Convert(converter = StepsConverter.class, columnType = String.class)
    private List<Step> steps = new ArrayList<>();
    private boolean isCompleted;
    private int repeatSelected = R.id.repeat_no;
    @Convert(converter = TagsConverter.class, columnType = String.class)
    private List<Tag> tags = new ArrayList<>();
    public ToDoItem() {
        calendar = Calendar.getInstance();
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
        this.steps = in.createTypedArrayList(Step.CREATOR);
        this.isCompleted = in.readByte() != 0;
        this.repeatSelected = in.readInt();
        this.tags = in.createTypedArrayList(Tag.CREATOR);
        this.id = in.readLong();
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean getHasReminder() {
        return hasReminder;
    }

    public void setHasReminder(boolean hasReminder) {
        this.hasReminder = hasReminder;
    }

    public boolean getHasDate() {
        return hasDate;
    }

    public void setHasDate(boolean hasDate) {
        this.hasDate = hasDate;
    }

    public boolean getHasQuantity() {
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

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean completed) {
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

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        dest.writeTypedList(this.steps);
        dest.writeByte(this.isCompleted ? (byte) 1 : (byte) 0);
        dest.writeInt(this.repeatSelected);
        dest.writeTypedList(this.tags);
        dest.writeLong(this.id);
    }

    public static class CalendarConverter implements PropertyConverter<Calendar, Date> {
        @Override
        public Calendar convertToEntityProperty(Date databaseValue) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(databaseValue);
            return calendar;
        }

        @Override
        public Date convertToDatabaseValue(Calendar entityProperty) {
            return entityProperty.getTime();
        }
    }

    public static class StepsConverter implements PropertyConverter<List<Step>, String> {
        @Override
        public List<Step> convertToEntityProperty(String databaseValue) {
            Type listType = new TypeToken<List<Step>>(){}.getType();
            return new Gson().fromJson(databaseValue, listType);
        }

        @Override
        public String convertToDatabaseValue(List<Step> entityProperty) {
            return new Gson().toJson(entityProperty);
        }
    }

    public static class TagsConverter implements PropertyConverter<List<Tag>, String> {
        @Override
        public List<Tag> convertToEntityProperty(String databaseValue) {
            Type listType = new TypeToken<List<Tag>>(){}.getType();
            return new Gson().fromJson(databaseValue, listType);
        }

        @Override
        public String convertToDatabaseValue(List<Tag> entityProperty) {
            return new Gson().toJson(entityProperty);
        }
    }
}