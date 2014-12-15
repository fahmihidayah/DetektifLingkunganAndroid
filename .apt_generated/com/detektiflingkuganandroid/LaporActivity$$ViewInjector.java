// Generated code from Butter Knife. Do not modify!
package com.detektiflingkuganandroid;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class LaporActivity$$ViewInjector {
  public static void inject(Finder finder, final com.detektiflingkuganandroid.LaporActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427415, "field 'editTextJudulLaporan'");
    target.editTextJudulLaporan = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427404, "field 'editTextDataLaporan'");
    target.editTextDataLaporan = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427413, "field 'gridViewImageLaporan' and method 'onClickLaporan'");
    target.gridViewImageLaporan = (android.widget.GridView) view;
    ((android.widget.AdapterView<?>) view).setOnItemClickListener(
      new android.widget.AdapterView.OnItemClickListener() {
        @Override public void onItemClick(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onClickLaporan(p2);
        }
      });
    view = finder.findRequiredView(source, 2131427406, "field 'spinnerKategori'");
    target.spinnerKategori = (android.widget.Spinner) view;
  }

  public static void reset(com.detektiflingkuganandroid.LaporActivity target) {
    target.editTextJudulLaporan = null;
    target.editTextDataLaporan = null;
    target.gridViewImageLaporan = null;
    target.spinnerKategori = null;
  }
}
