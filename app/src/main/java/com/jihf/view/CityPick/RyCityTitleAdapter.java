package com.jihf.view.CityPick;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jihf.view.R;
import me.yokeyword.indexablerv.IndexableAdapter;

/**
 * Func：
 * User：jihf
 * Data：2016-12-08
 * Time: 15:25
 * Mail：jihaifeng@raiyi.com
 */
public class RyCityTitleAdapter extends IndexableAdapter<CityListBean> {
  private Context mContext;

  public RyCityTitleAdapter(Context context) {
    this.mContext = context;
  }

  @Override public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
    // 创建 TitleItem 布局
    return new RyCityTitleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.ry_city_title_item, parent, false));
  }

  @Override public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
    // 创建 内容Item 布局
    return new RyCityContentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.ry_city_content_item, parent, false));
  }

  @Override public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle) {
    // 填充 TitleItem 布局
    RyCityTitleViewHolder titleViewHolder = (RyCityTitleViewHolder) holder;
    titleViewHolder.tvCityTitle.setText(indexTitle);
  }

  @Override public void onBindContentViewHolder(RecyclerView.ViewHolder holder, CityListBean cityListBean) {
    // 填充 内容Item 布局
    RyCityContentViewHolder contentViewHolder = (RyCityContentViewHolder) holder;
    contentViewHolder.tvCityContent.setText(cityListBean.sName);
  }

  class RyCityTitleViewHolder extends RecyclerView.ViewHolder {
    TextView tvCityTitle;

    public RyCityTitleViewHolder(View itemView) {
      super(itemView);
      tvCityTitle = (TextView) itemView.findViewById(R.id.tv_city_title);
    }
  }

  class RyCityContentViewHolder extends RecyclerView.ViewHolder {
    TextView tvCityContent;

    public RyCityContentViewHolder(View itemView) {
      super(itemView);
      tvCityContent = (TextView) itemView.findViewById(R.id.tv_city_content);
    }
  }
}
