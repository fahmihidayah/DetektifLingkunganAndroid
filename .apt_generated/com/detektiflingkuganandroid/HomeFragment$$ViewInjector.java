// Generated code from Butter Knife. Do not modify!
package com.detektiflingkuganandroid;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class HomeFragment$$ViewInjector {
  public static void inject(Finder finder, final com.detektiflingkuganandroid.HomeFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427398, "field 'buttonConversation' and method 'onClickDiscover'");
    target.buttonConversation = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickDiscover((android.widget.Button) p0);
        }
      });
    view = finder.findRequiredView(source, 2131427396, "field 'buttonLaporan' and method 'onClickHome'");
    target.buttonLaporan = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickHome((android.widget.Button) p0);
        }
      });
    view = finder.findRequiredView(source, 2131427397, "field 'buttonMap' and method 'onClickMap'");
    target.buttonMap = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickMap((android.widget.Button) p0);
        }
      });
  }

  public static void reset(com.detektiflingkuganandroid.HomeFragment target) {
    target.buttonConversation = null;
    target.buttonLaporan = null;
    target.buttonMap = null;
  }
}
