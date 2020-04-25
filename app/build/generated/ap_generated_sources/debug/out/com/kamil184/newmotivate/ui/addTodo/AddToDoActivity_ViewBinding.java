// Generated code from Butter Knife. Do not modify!
package com.kamil184.newmotivate.ui.addTodo;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
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
    target.noteEditText = Utils.findRequiredViewAsType(source, R.id.note_edit_text, "field 'noteEditText'", EditText.class);
    target.reminderImageView = Utils.findRequiredViewAsType(source, R.id.reminder_image_view, "field 'reminderImageView'", ImageView.class);
    target.reminderDelete = Utils.findRequiredViewAsType(source, R.id.reminder_delete, "field 'reminderDelete'", ImageButton.class);
    target.reminderTextView = Utils.findRequiredViewAsType(source, R.id.reminder_text_view, "field 'reminderTextView'", TextView.class);
    target.reminderLayout = Utils.findRequiredViewAsType(source, R.id.reminder_layout, "field 'reminderLayout'", LinearLayout.class);
    target.dateImageView = Utils.findRequiredViewAsType(source, R.id.date_image_view, "field 'dateImageView'", ImageView.class);
    target.dateDelete = Utils.findRequiredViewAsType(source, R.id.date_delete, "field 'dateDelete'", ImageButton.class);
    target.dateTextView = Utils.findRequiredViewAsType(source, R.id.date_text_view, "field 'dateTextView'", TextView.class);
    target.dateLayout = Utils.findRequiredViewAsType(source, R.id.date_layout, "field 'dateLayout'", LinearLayout.class);
    target.repeatImageView = Utils.findRequiredViewAsType(source, R.id.repeat_image_view, "field 'repeatImageView'", ImageView.class);
    target.repeatDelete = Utils.findRequiredViewAsType(source, R.id.repeat_delete, "field 'repeatDelete'", ImageButton.class);
    target.repeatTextView = Utils.findRequiredViewAsType(source, R.id.repeat_text_view, "field 'repeatTextView'", TextView.class);
    target.repeatSecondTextView = Utils.findRequiredViewAsType(source, R.id.repeat_second_text_view, "field 'repeatSecondTextView'", TextView.class);
    target.repeatLayout = Utils.findRequiredViewAsType(source, R.id.repeat_layout, "field 'repeatLayout'", LinearLayout.class);
    target.durationImageView = Utils.findRequiredViewAsType(source, R.id.duration_image_view, "field 'durationImageView'", ImageView.class);
    target.durationDelete = Utils.findRequiredViewAsType(source, R.id.duration_delete, "field 'durationDelete'", ImageButton.class);
    target.durationTextView = Utils.findRequiredViewAsType(source, R.id.duration_text_view, "field 'durationTextView'", TextView.class);
    target.durationLayout = Utils.findRequiredViewAsType(source, R.id.duration_layout, "field 'durationLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddToDoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.todoTitle = null;
    target.noteEditText = null;
    target.reminderImageView = null;
    target.reminderDelete = null;
    target.reminderTextView = null;
    target.reminderLayout = null;
    target.dateImageView = null;
    target.dateDelete = null;
    target.dateTextView = null;
    target.dateLayout = null;
    target.repeatImageView = null;
    target.repeatDelete = null;
    target.repeatTextView = null;
    target.repeatSecondTextView = null;
    target.repeatLayout = null;
    target.durationImageView = null;
    target.durationDelete = null;
    target.durationTextView = null;
    target.durationLayout = null;
  }
}