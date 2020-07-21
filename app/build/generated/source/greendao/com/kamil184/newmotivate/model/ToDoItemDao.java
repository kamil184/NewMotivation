package com.kamil184.newmotivate.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.kamil184.newmotivate.model.Repeat.RepeatConverter;
import com.kamil184.newmotivate.model.ToDoItem.CalendarConverter;
import com.kamil184.newmotivate.model.ToDoItem.StepsConverter;
import com.kamil184.newmotivate.model.ToDoItem.TagsConverter;
import java.util.Calendar;
import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TO_DO_ITEM".
*/
public class ToDoItemDao extends AbstractDao<ToDoItem, Long> {

    public static final String TABLENAME = "TO_DO_ITEM";

    /**
     * Properties of entity ToDoItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property HasReminder = new Property(1, boolean.class, "hasReminder", false, "HAS_REMINDER");
        public final static Property HasDate = new Property(2, boolean.class, "hasDate", false, "HAS_DATE");
        public final static Property HasQuantity = new Property(3, boolean.class, "hasQuantity", false, "HAS_QUANTITY");
        public final static Property Title = new Property(4, String.class, "title", false, "TITLE");
        public final static Property Note = new Property(5, String.class, "note", false, "NOTE");
        public final static Property Repeat = new Property(6, Integer.class, "repeat", false, "REPEAT");
        public final static Property QuantityNumber = new Property(7, int.class, "quantityNumber", false, "QUANTITY_NUMBER");
        public final static Property QuantityTextPosition = new Property(8, int.class, "quantityTextPosition", false, "QUANTITY_TEXT_POSITION");
        public final static Property Calendar = new Property(9, java.util.Date.class, "calendar", false, "CALENDAR");
        public final static Property Priority = new Property(10, int.class, "priority", false, "PRIORITY");
        public final static Property Steps = new Property(11, String.class, "steps", false, "STEPS");
        public final static Property IsCompleted = new Property(12, boolean.class, "isCompleted", false, "IS_COMPLETED");
        public final static Property RepeatSelected = new Property(13, int.class, "repeatSelected", false, "REPEAT_SELECTED");
        public final static Property Tags = new Property(14, String.class, "tags", false, "TAGS");
    }

    private final RepeatConverter repeatConverter = new RepeatConverter();
    private final CalendarConverter calendarConverter = new CalendarConverter();
    private final StepsConverter stepsConverter = new StepsConverter();
    private final TagsConverter tagsConverter = new TagsConverter();

    public ToDoItemDao(DaoConfig config) {
        super(config);
    }
    
    public ToDoItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TO_DO_ITEM\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"HAS_REMINDER\" INTEGER NOT NULL ," + // 1: hasReminder
                "\"HAS_DATE\" INTEGER NOT NULL ," + // 2: hasDate
                "\"HAS_QUANTITY\" INTEGER NOT NULL ," + // 3: hasQuantity
                "\"TITLE\" TEXT," + // 4: title
                "\"NOTE\" TEXT," + // 5: note
                "\"REPEAT\" INTEGER," + // 6: repeat
                "\"QUANTITY_NUMBER\" INTEGER NOT NULL ," + // 7: quantityNumber
                "\"QUANTITY_TEXT_POSITION\" INTEGER NOT NULL ," + // 8: quantityTextPosition
                "\"CALENDAR\" INTEGER," + // 9: calendar
                "\"PRIORITY\" INTEGER NOT NULL ," + // 10: priority
                "\"STEPS\" TEXT," + // 11: steps
                "\"IS_COMPLETED\" INTEGER NOT NULL ," + // 12: isCompleted
                "\"REPEAT_SELECTED\" INTEGER NOT NULL ," + // 13: repeatSelected
                "\"TAGS\" TEXT);"); // 14: tags
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TO_DO_ITEM\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ToDoItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getHasReminder() ? 1L: 0L);
        stmt.bindLong(3, entity.getHasDate() ? 1L: 0L);
        stmt.bindLong(4, entity.getHasQuantity() ? 1L: 0L);
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        String note = entity.getNote();
        if (note != null) {
            stmt.bindString(6, note);
        }
 
        Repeat repeat = entity.getRepeat();
        if (repeat != null) {
            stmt.bindLong(7, repeatConverter.convertToDatabaseValue(repeat));
        }
        stmt.bindLong(8, entity.getQuantityNumber());
        stmt.bindLong(9, entity.getQuantityTextPosition());
 
        Calendar calendar = entity.getCalendar();
        if (calendar != null) {
            stmt.bindLong(10, calendarConverter.convertToDatabaseValue(calendar).getTime());
        }
        stmt.bindLong(11, entity.getPriority());
 
        List steps = entity.getSteps();
        if (steps != null) {
            stmt.bindString(12, stepsConverter.convertToDatabaseValue(steps));
        }
        stmt.bindLong(13, entity.getIsCompleted() ? 1L: 0L);
        stmt.bindLong(14, entity.getRepeatSelected());
 
        List tags = entity.getTags();
        if (tags != null) {
            stmt.bindString(15, tagsConverter.convertToDatabaseValue(tags));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ToDoItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getHasReminder() ? 1L: 0L);
        stmt.bindLong(3, entity.getHasDate() ? 1L: 0L);
        stmt.bindLong(4, entity.getHasQuantity() ? 1L: 0L);
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        String note = entity.getNote();
        if (note != null) {
            stmt.bindString(6, note);
        }
 
        Repeat repeat = entity.getRepeat();
        if (repeat != null) {
            stmt.bindLong(7, repeatConverter.convertToDatabaseValue(repeat));
        }
        stmt.bindLong(8, entity.getQuantityNumber());
        stmt.bindLong(9, entity.getQuantityTextPosition());
 
        Calendar calendar = entity.getCalendar();
        if (calendar != null) {
            stmt.bindLong(10, calendarConverter.convertToDatabaseValue(calendar).getTime());
        }
        stmt.bindLong(11, entity.getPriority());
 
        List steps = entity.getSteps();
        if (steps != null) {
            stmt.bindString(12, stepsConverter.convertToDatabaseValue(steps));
        }
        stmt.bindLong(13, entity.getIsCompleted() ? 1L: 0L);
        stmt.bindLong(14, entity.getRepeatSelected());
 
        List tags = entity.getTags();
        if (tags != null) {
            stmt.bindString(15, tagsConverter.convertToDatabaseValue(tags));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ToDoItem readEntity(Cursor cursor, int offset) {
        ToDoItem entity = new ToDoItem();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ToDoItem entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setHasReminder(cursor.getShort(offset + 1) != 0);
        entity.setHasDate(cursor.getShort(offset + 2) != 0);
        entity.setHasQuantity(cursor.getShort(offset + 3) != 0);
        entity.setTitle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setNote(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setRepeat(cursor.isNull(offset + 6) ? null : repeatConverter.convertToEntityProperty(cursor.getInt(offset + 6)));
        entity.setQuantityNumber(cursor.getInt(offset + 7));
        entity.setQuantityTextPosition(cursor.getInt(offset + 8));
        entity.setCalendar(cursor.isNull(offset + 9) ? null : calendarConverter.convertToEntityProperty(new java.util.Date(cursor.getLong(offset + 9))));
        entity.setPriority(cursor.getInt(offset + 10));
        entity.setSteps(cursor.isNull(offset + 11) ? null : stepsConverter.convertToEntityProperty(cursor.getString(offset + 11)));
        entity.setIsCompleted(cursor.getShort(offset + 12) != 0);
        entity.setRepeatSelected(cursor.getInt(offset + 13));
        entity.setTags(cursor.isNull(offset + 14) ? null : tagsConverter.convertToEntityProperty(cursor.getString(offset + 14)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ToDoItem entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ToDoItem entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ToDoItem entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
