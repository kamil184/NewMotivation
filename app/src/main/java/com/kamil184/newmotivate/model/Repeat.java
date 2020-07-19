package com.kamil184.newmotivate.model;

import org.greenrobot.greendao.converter.PropertyConverter;

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
                if (!days[i]) {
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

    public abstract int getCount();

    public abstract void setCount(int count);

    public int getSelection() {
        return selection;
    }

    public boolean[] getDays() {
        return new boolean[0];
    }

    public void setDays(boolean[] days) {
    }

    public static class RepeatConverter implements PropertyConverter<Repeat, Integer> {
        @Override
        public Repeat convertToEntityProperty(Integer databaseValue) {
            for (Repeat role : Repeat.values()) {
                if (role.selection == databaseValue) {
                    return role;
                }
            }
            return null;
        }

        @Override
        public Integer convertToDatabaseValue(Repeat entityProperty) {
            return entityProperty == null ? null : entityProperty.selection;
        }
    }
}
