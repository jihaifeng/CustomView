package com.jihf.view.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.lang.reflect.Field;

public class ScreenUtil {
  private static ScreenUtil instance;
  private static float screenWidth = -1;
  private static float screenHeight = -1;
  private static int stateBarHeight = -1;
  private static float density = 1;
  private static float scale;

  private ScreenUtil(Context mContext) {
    DisplayMetrics dm = new DisplayMetrics();
    ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
    screenWidth = dm.widthPixels;
    screenHeight = dm.heightPixels;
    // 屏幕密度（0.75 / 1.0 / 1.5）
    density = dm.density;
    stateBarHeight = getStateBarHeight(mContext);
    scale = mContext.getResources().getDisplayMetrics().density;
  }

  public static ScreenUtil getInstance(Context mContext) {
    if (instance == null) {
      synchronized (ScreenUtil.class) {
        if (null == instance) {
          instance = new ScreenUtil(mContext);
        }
      }
    }
    return instance;
  }

  public float getScreenWidth() {
    return screenWidth;
  }

  public float getScreenHeight() {
    return screenHeight;
  }

  public float getDensity() {
    return density;
  }

  public int getStateBarHeight() {
    return stateBarHeight;
  }

  /**
   * 获取状态栏高度
   */
  public int getStateBarHeight(Context mContext) {
    Class<?> c = null;
    Object obj = null;
    Field field = null;
    int x = 0, sbar = 0;
    try {
      c = Class.forName("com.android.internal.R$dimen");
      obj = c.newInstance();
      field = c.getField("status_bar_height");
      x = Integer.parseInt(field.get(obj).toString());
      sbar = mContext.getResources().getDimensionPixelSize(x);
      return sbar;
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    return sbar;
  }

  public int dp2px(float dipValue) {
    return (int) (dipValue * scale + 0.5f);
  }

  public int px2dp(float pxValue) {
    return (int) (pxValue / scale + 0.5f);
  }
}
