// Generated code from Butter Knife. Do not modify!
package com.kamil184.newmotivate.ui.tasks;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kamil184.newmotivate.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TasksFragment_ViewBinding implements Unbinder {
  private TasksFragment target;

  @UiThread
  public TasksFragment_ViewBinding(TasksFragment target, View source) {
    this.target = target;

    target.addTodoFab = Utils.findRequiredViewAsType(source, R.id.tasks_add_todo_fab, "field 'addTodoFab'", FloatingActionButton.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.tasks_recycler, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TasksFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.addTodoFab = null;
    target.recyclerView = null;
  }
}
