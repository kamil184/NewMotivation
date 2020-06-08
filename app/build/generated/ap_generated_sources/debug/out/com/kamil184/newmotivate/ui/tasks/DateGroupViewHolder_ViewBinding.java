// Generated code from Butter Knife. Do not modify!
package com.kamil184.newmotivate.ui.tasks;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.chip.Chip;
import com.kamil184.newmotivate.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DateGroupViewHolder_ViewBinding implements Unbinder {
  private DateGroupViewHolder target;

  @UiThread
  public DateGroupViewHolder_ViewBinding(DateGroupViewHolder target, View source) {
    this.target = target;

    target.title = Utils.findRequiredViewAsType(source, R.id.date_group_title, "field 'title'", TextView.class);
    target.add = Utils.findRequiredViewAsType(source, R.id.date_group_add, "field 'add'", ImageView.class);
    target.count = Utils.findRequiredViewAsType(source, R.id.date_group_count, "field 'count'", Chip.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DateGroupViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title = null;
    target.add = null;
    target.count = null;
  }
}
