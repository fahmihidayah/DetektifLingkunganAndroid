// Generated code from Butter Knife. Do not modify!
package com.detektiflingkuganandroid;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class DetailLaporanFragment$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.detektiflingkuganandroid.DetailLaporanFragment.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427349, "method 'onClickBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickBack(p0);
        }
      });
  }

  public static void reset(com.detektiflingkuganandroid.DetailLaporanFragment.ViewHolder target) {
  }
}
