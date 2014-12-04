// Generated code from Butter Knife. Do not modify!
package com.detektiflingkuganandroid;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ListFollowerFragment$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.detektiflingkuganandroid.ListFollowerFragment.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427450, "field 'textViewNameUser'");
    target.textViewNameUser = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131427451, "field 'imageViewProfileUser'");
    target.imageViewProfileUser = (android.widget.ImageView) view;
  }

  public static void reset(com.detektiflingkuganandroid.ListFollowerFragment.ViewHolder target) {
    target.textViewNameUser = null;
    target.imageViewProfileUser = null;
  }
}
