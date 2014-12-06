// Generated code from Butter Knife. Do not modify!
package com.detektiflingkuganandroid;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class PrivateMessageUserFragment$$ViewInjector {
  public static void inject(Finder finder, final com.detektiflingkuganandroid.PrivateMessageUserFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427439, "field 'listViewChatMessage'");
    target.listViewChatMessage = (android.widget.ListView) view;
    view = finder.findRequiredView(source, 2131427441, "field 'editTextMessage'");
    target.editTextMessage = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427442, "field 'buttonSend' and method 'onClick'");
    target.buttonSend = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  public static void reset(com.detektiflingkuganandroid.PrivateMessageUserFragment target) {
    target.listViewChatMessage = null;
    target.editTextMessage = null;
    target.buttonSend = null;
  }
}
