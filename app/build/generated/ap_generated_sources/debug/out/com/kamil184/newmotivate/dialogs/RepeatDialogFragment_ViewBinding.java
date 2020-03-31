// Generated code from Butter Knife. Do not modify!
package com.kamil184.newmotivate.dialogs;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kamil184.newmotivate.CircularMaterialButton;
import com.kamil184.newmotivate.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RepeatDialogFragment_ViewBinding implements Unbinder {
  private RepeatDialogFragment target;

  @UiThread
  public RepeatDialogFragment_ViewBinding(RepeatDialogFragment target, View source) {
    this.target = target;

    target.editText = Utils.findRequiredViewAsType(source, R.id.repeat_dialog_edit_text, "field 'editText'", EditText.class);
    target.spinner = Utils.findRequiredViewAsType(source, R.id.repeat_dialog_spinner, "field 'spinner'", Spinner.class);
    target.daysOfTheWeek = Utils.listFilteringNull(
        Utils.findRequiredViewAsType(source, R.id.monday, "field 'daysOfTheWeek'", CircularMaterialButton.class), 
        Utils.findRequiredViewAsType(source, R.id.tuesday, "field 'daysOfTheWeek'", CircularMaterialButton.class), 
        Utils.findRequiredViewAsType(source, R.id.wednesday, "field 'daysOfTheWeek'", CircularMaterialButton.class), 
        Utils.findRequiredViewAsType(source, R.id.thursday, "field 'daysOfTheWeek'", CircularMaterialButton.class), 
        Utils.findRequiredViewAsType(source, R.id.friday, "field 'daysOfTheWeek'", CircularMaterialButton.class), 
        Utils.findRequiredViewAsType(source, R.id.saturday, "field 'daysOfTheWeek'", CircularMaterialButton.class), 
        Utils.findRequiredViewAsType(source, R.id.sunday, "field 'daysOfTheWeek'", CircularMaterialButton.class));
  }

  @Override
  @CallSuper
  public void unbind() {
    RepeatDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editText = null;
    target.spinner = null;
    target.daysOfTheWeek = null;
  }
}
