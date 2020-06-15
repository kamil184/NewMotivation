// Generated code from Butter Knife. Do not modify!
package com.kamil184.newmotivate.ui.addTodo;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kamil184.newmotivate.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TagsDialog_ViewBinding implements Unbinder {
  private TagsDialog target;

  @UiThread
  public TagsDialog_ViewBinding(TagsDialog target, View source) {
    this.target = target;

    target.editText = Utils.findRequiredViewAsType(source, R.id.tag_edit_text, "field 'editText'", EditText.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.tag_recycler, "field 'recyclerView'", RecyclerView.class);
    target.addTag = Utils.findRequiredViewAsType(source, R.id.tag_add_tag, "field 'addTag'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TagsDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editText = null;
    target.recyclerView = null;
    target.addTag = null;
  }
}
