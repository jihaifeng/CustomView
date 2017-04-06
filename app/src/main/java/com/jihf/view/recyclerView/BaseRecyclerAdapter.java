package com.jihf.view.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Func：
 * Desc:
 * Author：jihf
 * Data：2017-04-06 09:53
 * Mail：jihaifeng@raiyi.com
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
  private int itemCount = -1;
  private List<T> mList = new ArrayList<>();

  @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(initLayoutId(), parent, false));
  }

  @Override public void onBindViewHolder(BaseViewHolder holder, int position) {
    onBind(holder, position, mList.get(position));
  }

  @Override public int getItemCount() {
    if (itemCount < 0 || itemCount > mList.size()) {
      itemCount = mList.size();
    }
    return itemCount;
  }

  public void setItemCount(int itemCount) {
    this.itemCount = itemCount > 0 ? itemCount : -1;
  }

  /**
   * 增加数据
   *
   * @param datas
   */
  public void addData(List<T> datas) {
    mList.addAll(datas);
    notifyDataSetChanged();
  }

  /**
   * 刷新数据
   *
   * @param datas
   */
  public void refreshData(List<T> datas) {
    mList.clear();
    mList.addAll(datas);
    notifyDataSetChanged();
  }

  /**
   * 获取数据
   *
   * @return mList
   */
  public List<T> getDatas() {
    return mList;
  }

  protected abstract int initLayoutId();

  protected abstract void onBind(BaseViewHolder holder, int position, T data);
}
