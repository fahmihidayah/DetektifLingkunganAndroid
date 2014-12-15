// Generated code from Butter Knife. Do not modify!
package com.detektiflingkuganandroid;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class LoginActivity$$ViewInjector {
  public static void inject(Finder finder, final com.detektiflingkuganandroid.LoginActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427366, "field 'editTextPassword'");
    target.editTextPassword = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427365, "field 'editTextUserName'");
    target.editTextUserName = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427433, "method 'onClickLogin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickLogin(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427434, "method 'onClickDaftar'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickDaftar(p0);
        }
      });
  }

  public static void reset(com.detektiflingkuganandroid.LoginActivity target) {
    target.editTextPassword = null;
    target.editTextUserName = null;
  }
}
