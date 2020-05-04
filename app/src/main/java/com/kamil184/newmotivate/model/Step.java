package com.kamil184.newmotivate.model;

public class Step {
    private boolean isChecked;
    private String text;

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

    public Step(boolean isChecked, String text) {
        this.isChecked = isChecked;
        this.text = text;
    }

    public Step() {
        isChecked = false;
        text = "";
    }
}
