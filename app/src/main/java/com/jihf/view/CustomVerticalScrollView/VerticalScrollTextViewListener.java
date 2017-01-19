package com.jihf.view.CustomVerticalScrollView;

/**
 * Func：
 * User：jihf
 * Data：2016-10-20
 * Time: 09:55
 * Mail：jihaifeng@raiyi.com
 */
public interface VerticalScrollTextViewListener {
  void onContentClick(ScrollTextDataBean bean, int position);

  void onRightTextClick();

  void onLeftImageClick();
}
