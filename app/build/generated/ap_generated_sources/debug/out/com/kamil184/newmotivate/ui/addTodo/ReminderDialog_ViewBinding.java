// Generated code from Butter Knife. Do not modify!
package com.kamil184.newmotivate.ui.addTodo;

import android.view.View;
import android.widget.TimePicker;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kamil184.newmotivate.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReminderDialog_ViewBinding implements Unbinder {
  private ReminderDialog target;

  @UiThread
  public ReminderDialog_ViewBinding(ReminderDialog target, View source) {
    this.target = target;

    target.timePicker = Utils.findRequiredViewAsType(source, R.id.time_picker, "field 'timePicker'", TimePicker.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReminderDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.timePicker = null;
  }
}
