// Generated code from Butter Knife. Do not modify!
package com.detektiflingkuganandroid;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SearchFragment$$ViewInjector {
  public static void inject(Finder finder, final com.detektiflingkuganandroid.SearchFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427450, "field 'buttonLaporan' and method 'onClickLaporan'");
    target.buttonLaporan = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickLaporan(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427451, "field 'buttonUser' and method 'onClickUser'");
    target.buttonUser = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickUser(p0);
        }
      });
  }

  public static void reset(com.detektiflingkuganandroid.SearchFragment target) {
    target.buttonLaporan = null;
    target.buttonUser = null;
  }
}
