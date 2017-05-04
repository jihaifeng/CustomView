package com.jihf.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Func：
 * Desc:
 * Author：jihf
 * Date：2017-05-03 14:27
 * Mail：jihaifeng@raiyi.com
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
  public static final String TAG = MyBroadcastReceiver.class.getSimpleName();
  @Override public void onReceive(Context context, Intent intent) {
    Log.i(TAG, "onReceive: " + intent);
  }
}
