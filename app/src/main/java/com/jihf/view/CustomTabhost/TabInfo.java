package com.jihf.view.CustomTabhost;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Func：
 * User：jihf
 * Data：2016-08-19
 * Time: 16:36
 * Mail：jihaifeng@raiyi.com
 */
public final class TabInfo {
  public final String tag;
  public final Class<?> clss;
  public final Bundle args;
  public Fragment fragment;

  TabInfo(String _tag, Class<?> _class, Bundle _args) {
    tag = _tag;
    clss = _class;
    args = _args;
  }
}