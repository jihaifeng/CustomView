package com.jihf.view.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

/**
 * Func：
 * Desc:
 * Author：jihf
 * Data：2017-04-06 09:46
 * Mail：jihaifeng@raiyi.com
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
  private Map<Integer, View> mViewMap;

  public BaseViewHolder(View itemView) {
    super(itemView);
    mViewMap = new HashMap<>();
  }

  /**
   * 获取设置的view
   *
   * @param id
   *
   * @return
   */
  public View getView(int id) {
    View view = mViewMap.get(id);
    if (view == null) {
      view = itemView.findViewById(id);
      mViewMap.put(id, view);
    }
    return view;
  }
}
