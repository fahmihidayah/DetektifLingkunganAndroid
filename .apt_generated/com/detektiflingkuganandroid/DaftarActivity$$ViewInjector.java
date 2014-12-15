// Generated code from Butter Knife. Do not modify!
package com.detektiflingkuganandroid;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class DaftarActivity$$ViewInjector {
  public static void inject(Finder finder, final com.detektiflingkuganandroid.DaftarActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427366, "field 'editTextPassword'");
    target.editTextPassword = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427364, "field 'editTextName'");
    target.editTextName = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427367, "field 'editTextEmail'");
    target.editTextEmail = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427365, "field 'editTextUserName'");
    target.editTextUserName = (android.widget.EditText) view;
  }

  public static void reset(com.detektiflingkuganandroid.DaftarActivity target) {
    target.editTextPassword = null;
    target.editTextName = null;
    target.editTextEmail = null;
    target.editTextUserName = null;
  }
}
