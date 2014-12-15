// Generated code from Butter Knife. Do not modify!
package com.detektiflingkuganandroid;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ProfileFragment$$ViewInjector {
  public static void inject(Finder finder, final com.detektiflingkuganandroid.ProfileFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427390, "field 'buttonFollowing' and method 'onClickFollowing'");
    target.buttonFollowing = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickFollowing(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427372, "field 'imageViewProfile' and method 'onClickImageViewProfile'");
    target.imageViewProfile = (android.widget.ImageView) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickImageViewProfile(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427391, "field 'gridViewLaporan'");
    target.gridViewLaporan = (android.widget.GridView) view;
    view = finder.findRequiredView(source, 2131427383, "field 'textViewStatus'");
    target.textViewStatus = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131427386, "field 'buttonFollow' and method 'follow'");
    target.buttonFollow = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.follow();
        }
      });
    view = finder.findRequiredView(source, 2131427373, "field 'textViewName'");
    target.textViewName = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131427388, "field 'imageButtonEditStatus' and method 'onClickEditStatus'");
    target.imageButtonEditStatus = (android.widget.ImageButton) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickEditStatus(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427389, "field 'buttonFollower' and method 'onClickFollower'");
    target.buttonFollower = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickFollower(p0);
        }
      });
  }

  public static void reset(com.detektiflingkuganandroid.ProfileFragment target) {
    target.buttonFollowing = null;
    target.imageViewProfile = null;
    target.gridViewLaporan = null;
    target.textViewStatus = null;
    target.buttonFollow = null;
    target.textViewName = null;
    target.imageButtonEditStatus = null;
    target.buttonFollower = null;
  }
}
