// Generated code from Butter Knife. Do not modify!
package com.kamil184.newmotivate.activities;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.kamil184.newmotivate.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddToDoActivity_ViewBinding implements Unbinder {
  private AddToDoActivity target;

  @UiThread
  public AddToDoActivity_ViewBinding(AddToDoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddToDoActivity_ViewBinding(AddToDoActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.todoTitle = Utils.findRequiredViewAsType(source, R.id.todo_title_edit_text, "field 'todoTitle'", EditText.class);
    target.reminderImageView = Utils.findRequiredViewAsType(source, R.id.reminder_image_view, "field 'reminderImageView'", ImageView.class);
    target.reminderTextView = Utils.findRequiredViewAsType(source, R.id.reminder_text_view, "field 'reminderTextView'", TextView.class);
    target.reminderSwitch = Utils.findRequiredViewAsType(source, R.id.reminder_switch_material, "field 'reminderSwitch'", SwitchMaterial.class);
    target.reminderLayout = Utils.findRequiredViewAsType(source, R.id.reminder_layout, "field 'reminderLayout'", LinearLayout.class);
    target.reminderDateEditText = Utils.findRequiredViewAsType(source, R.id.reminder_date_edit_text, "field 'reminderDateEditText'", EditText.class);
    target.reminderTimeEditText = Utils.findRequiredViewAsType(source, R.id.reminder_time_edit_text, "field 'reminderTimeEditText'", EditText.class);
    target.disappearingReminderLayout = Utils.findRequiredViewAsType(source, R.id.disappearing_reminder_layout, "field 'disappearingReminderLayout'", LinearLayout.class);
    target.repeatImageView = Utils.findRequiredViewAsType(source, R.id.repeat_image_view, "field 'repeatImageView'", ImageView.class);
    target.repeatTextView = Utils.findRequiredViewAsType(source, R.id.repeat_text_view, "field 'repeatTextView'", TextView.class);
    target.repeatSwitch = Utils.findRequiredViewAsType(source, R.id.repeat_switch_material, "field 'repeatSwitch'", SwitchMaterial.class);
    target.repeatLayout = Utils.findRequiredViewAsType(source, R.id.repeat_layout, "field 'repeatLayout'", LinearLayout.class);
    target.repeatEditText = Utils.findRequiredViewAsType(source, R.id.repeat_edit_text, "field 'repeatEditText'", EditText.class);
    target.disappearingRepeatLayout = Utils.findRequiredViewAsType(source, R.id.disappearing_repeat_layout, "field 'disappearingRepeatLayout'", LinearLayout.class);
    target.durationImageView = Utils.findRequiredViewAsType(source, R.id.duration_image_view, "field 'durationImageView'", ImageView.class);
    target.durationTextView = Utils.findRequiredViewAsType(source, R.id.duration_text_view, "field 'durationTextView'", TextView.class);
    target.durationSwitch = Utils.findRequiredViewAsType(source, R.id.duration_switch_material, "field 'durationSwitch'", SwitchMaterial.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddToDoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.todoTitle = null;
    target.reminderImageView = null;
    target.reminderTextView = null;
    target.reminderSwitch = null;
    target.reminderLayout = null;
    target.reminderDateEditText = null;
    target.reminderTimeEditText = null;
    target.disappearingReminderLayout = null;
    target.repeatImageView = null;
    target.repeatTextView = null;
    target.repeatSwitch = null;
    target.repeatLayout = null;
    target.repeatEditText = null;
    target.disappearingRepeatLayout = null;
    target.durationImageView = null;
    target.durationTextView = null;
    target.durationSwitch = null;
  }
}
