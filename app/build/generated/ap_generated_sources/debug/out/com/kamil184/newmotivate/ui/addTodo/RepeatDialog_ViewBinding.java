// Generated code from Butter Knife. Do not modify!
package com.kamil184.newmotivate.ui.addTodo;

import android.view.View;
import android.widget.RadioGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kamil184.newmotivate.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RepeatDialog_ViewBinding implements Unbinder {
  private RepeatDialog target;

  @UiThread
  public RepeatDialog_ViewBinding(RepeatDialog target, View source) {
    this.target = target;

    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.repeat_radio_group, "field 'radioGroup'", RadioGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RepeatDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.radioGroup = null;
  }
}
