// Generated code from Butter Knife. Do not modify!
package com.kamil184.newmotivate.ui.addTodo;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kamil184.newmotivate.R;
import com.kamil184.newmotivate.util.StateCircularMaterialButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RepeatDialog_ViewBinding implements Unbinder {
  private RepeatDialog target;

  @UiThread
  public RepeatDialog_ViewBinding(RepeatDialog target, View source) {
    this.target = target;

    target.editText = Utils.findRequiredViewAsType(source, R.id.repeat_dialog_edit_text, "field 'editText'", EditText.class);
    target.spinner = Utils.findRequiredViewAsType(source, R.id.repeat_dialog_spinner, "field 'spinner'", Spinner.class);
    target.daysOfWeek = Utils.listFilteringNull(
        Utils.findRequiredViewAsType(source, R.id.monday, "field 'daysOfWeek'", StateCircularMaterialButton.class), 
        Utils.findRequiredViewAsType(source, R.id.tuesday, "field 'daysOfWeek'", StateCircularMaterialButton.class), 
        Utils.findRequiredViewAsType(source, R.id.wednesday, "field 'daysOfWeek'", StateCircularMaterialButton.class), 
        Utils.findRequiredViewAsType(source, R.id.thursday, "field 'daysOfWeek'", StateCircularMaterialButton.class), 
        Utils.findRequiredViewAsType(source, R.id.friday, "field 'daysOfWeek'", StateCircularMaterialButton.class), 
        Utils.findRequiredViewAsType(source, R.id.saturday, "field 'daysOfWeek'", StateCircularMaterialButton.class), 
        Utils.findRequiredViewAsType(source, R.id.sunday, "field 'daysOfWeek'", StateCircularMaterialButton.class));
  }

  @Override
  @CallSuper
  public void unbind() {
    RepeatDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editText = null;
    target.spinner = null;
    target.daysOfWeek = null;
  }
}
