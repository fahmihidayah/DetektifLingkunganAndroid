// Generated code from Butter Knife. Do not modify!
package com.detektiflingkuganandroid;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class HomeFragment$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.detektiflingkuganandroid.HomeFragment.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427353, "method 'onClickImageButtonNotif'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickImageButtonNotif((android.widget.ImageButton) p0);
        }
      });
    view = finder.findRequiredView(source, 2131427355, "method 'onClickImageButtonPrivateMessage'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickImageButtonPrivateMessage((android.widget.ImageButton) p0);
        }
      });
    view = finder.findRequiredView(source, 2131427356, "method 'onClickImageButtonSearch'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickImageButtonSearch((android.widget.ImageButton) p0);
        }
      });
    view = finder.findRequiredView(source, 2131427352, "method 'onClickImageButtonProfileMenu'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickImageButtonProfileMenu((android.widget.ImageButton) p0);
        }
      });
    view = finder.findRequiredView(source, 2131427354, "method 'onClickImageButtonShot'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickImageButtonShot((android.widget.ImageButton) p0);
        }
      });
  }

  public static void reset(com.detektiflingkuganandroid.HomeFragment.ViewHolder target) {
  }
}
