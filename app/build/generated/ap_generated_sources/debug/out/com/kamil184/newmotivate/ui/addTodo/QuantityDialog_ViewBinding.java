// Generated code from Butter Knife. Do not modify!
package com.kamil184.newmotivate.ui.addTodo;

import android.view.View;
import android.widget.NumberPicker;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kamil184.newmotivate.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class QuantityDialog_ViewBinding implements Unbinder {
  private QuantityDialog target;

  @UiThread
  public QuantityDialog_ViewBinding(QuantityDialog target, View source) {
    this.target = target;

    target.numberPicker = Utils.findRequiredViewAsType(source, R.id.quantity_number_picker, "field 'numberPicker'", NumberPicker.class);
    target.textPicker = Utils.findRequiredViewAsType(source, R.id.quantity_text_picker, "field 'textPicker'", NumberPicker.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    QuantityDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.numberPicker = null;
    target.textPicker = null;
  }
}
