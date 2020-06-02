// Generated code from Butter Knife. Do not modify!
package com.kamil184.newmotivate.ui.addTodo;

import android.view.View;
import android.widget.DatePicker;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kamil184.newmotivate.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DatePickerDialog_ViewBinding implements Unbinder {
  private DatePickerDialog target;

  @UiThread
  public DatePickerDialog_ViewBinding(DatePickerDialog target, View source) {
    this.target = target;

    target.datePicker = Utils.findRequiredViewAsType(source, R.id.date_picker, "field 'datePicker'", DatePicker.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DatePickerDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.datePicker = null;
  }
}
