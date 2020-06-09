// Generated code from Butter Knife. Do not modify!
package com.kamil184.newmotivate.ui.tasks;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kamil184.newmotivate.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskViewHolder_ViewBinding implements Unbinder {
  private TaskViewHolder target;

  @UiThread
  public TaskViewHolder_ViewBinding(TaskViewHolder target, View source) {
    this.target = target;

    target.title = Utils.findRequiredViewAsType(source, R.id.task_item_title, "field 'title'", TextView.class);
    target.note = Utils.findRequiredViewAsType(source, R.id.task_item_note, "field 'note'", TextView.class);
    target.date = Utils.findRequiredViewAsType(source, R.id.task_item_date, "field 'date'", TextView.class);
    target.checkBox = Utils.findRequiredViewAsType(source, R.id.task_item_checkbox, "field 'checkBox'", CheckBox.class);
    target.repeat = Utils.findRequiredViewAsType(source, R.id.task_item_repeat, "field 'repeat'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title = null;
    target.note = null;
    target.date = null;
    target.checkBox = null;
    target.repeat = null;
  }
}
