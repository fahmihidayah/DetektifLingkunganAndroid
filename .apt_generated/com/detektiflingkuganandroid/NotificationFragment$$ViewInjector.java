// Generated code from Butter Knife. Do not modify!
package com.detektiflingkuganandroid;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class NotificationFragment$$ViewInjector {
  public static void inject(Finder finder, final com.detektiflingkuganandroid.NotificationFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427439, "field 'listViewNotifications' and method 'onItemClickListViewNotification'");
    target.listViewNotifications = (android.widget.ListView) view;
    ((android.widget.AdapterView<?>) view).setOnItemClickListener(
      new android.widget.AdapterView.OnItemClickListener() {
        @Override public void onItemClick(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onItemClickListViewNotification(p2);
        }
      });
  }

  public static void reset(com.detektiflingkuganandroid.NotificationFragment target) {
    target.listViewNotifications = null;
  }
}
