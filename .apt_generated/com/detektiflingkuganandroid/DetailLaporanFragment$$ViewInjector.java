// Generated code from Butter Knife. Do not modify!
package com.detektiflingkuganandroid;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class DetailLaporanFragment$$ViewInjector {
  public static void inject(Finder finder, final com.detektiflingkuganandroid.DetailLaporanFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427373, "field 'textViewUserName'");
    target.textViewUserName = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131427379, "field 'textViewJudulLaporan'");
    target.textViewJudulLaporan = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131427374, "field 'textViewTime'");
    target.textViewTime = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131427370, "field 'editTextComment'");
    target.editTextComment = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427377, "field 'buttonComment' and method 'onClickComment'");
    target.buttonComment = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickComment((android.widget.Button) p0);
        }
      });
    view = finder.findRequiredView(source, 2131427375, "field 'imageViewLaporan'");
    target.imageViewLaporan = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131427372, "field 'imageViewProfile'");
    target.imageViewProfile = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131427380, "field 'textViewDataLaporan'");
    target.textViewDataLaporan = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131427369, "field 'listViewKomentar'");
    target.listViewKomentar = (android.widget.ListView) view;
    view = finder.findRequiredView(source, 2131427378, "field 'buttonPantau' and method 'onClickPantau'");
    target.buttonPantau = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickPantau((android.widget.Button) p0);
        }
      });
  }

  public static void reset(com.detektiflingkuganandroid.DetailLaporanFragment target) {
    target.textViewUserName = null;
    target.textViewJudulLaporan = null;
    target.textViewTime = null;
    target.editTextComment = null;
    target.buttonComment = null;
    target.imageViewLaporan = null;
    target.imageViewProfile = null;
    target.textViewDataLaporan = null;
    target.listViewKomentar = null;
    target.buttonPantau = null;
  }
}
