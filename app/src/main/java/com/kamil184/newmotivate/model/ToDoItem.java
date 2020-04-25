package com.kamil184.newmotivate.model;

import java.io.Serializable;
import java.util.Calendar;

public class ToDoItem implements Serializable {

    //public static final String TITLE = "title";
    //public static final String DATE = "date";
    //public static final String HAS_REMINDER = "has reminder";

    private boolean hasReminder;
    private boolean hasDate;
    private String title;
    private Repeat repeat;
    private int duration; // in minutes
    private Calendar calendar;

    public ToDoItem() {
        calendar = Calendar.getInstance();
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public enum Repeat {
        DAY(0) {
            int count;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }


            public String getText() {
                if (count == 1) return "Daily";
                return "Every " + count + " days";
            }
        },
        WEEK(1) {
            int count;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            private boolean[] days = new boolean[7];

            public boolean[] getDays() {
                return days;
            }

            public void setDays(boolean[] days) {
                this.days = days;
            }

            public String getText() {
                boolean isDaily = true;
                if (count != 1) isDaily = false;
                for (int i = 0; i < 7; i++) {
                    if(!days[i]){
                        isDaily = false;
                        break;
                    }
                }
                if (isDaily) return "Daily";
                if (count == 1) return "Weekly";
                return "Every " + count + " weeks";
            }
        },
        MONTH(2) {
            int count;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getText() {
                if (count == 1) return "Monthly";
                return "Every " + count + " months";
            }
        },
        YEAR(3) {
            int count;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getText() {
                if (count == 1) return "Yearly";
                return "Every " + count + " years";
            }
        };
        private int selection;

        Repeat(int selection) {
            this.selection = selection;
        }

        public abstract String getText();

        public abstract void setCount(int count);

        public abstract int getCount();

        public int getSelection() {
            return selection;
        }

        public boolean[] getDays() {
            return null;
        }

        public void setDays(boolean[] days) {
        }
    }
}
