package com.jihf.view.CityPick;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jihf.view.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Func：
 * User：jihf
 * Data：2016-12-08
 * Time: 16:08
 * Mail：jihaifeng@raiyi.com
 */
public class RyCityContentAdapter extends RecyclerView.Adapter<RyCityContentAdapter.RyCityItemViewHolder> {
  private Context mContext;
  private List<CityListBean> mList = new ArrayList<>();

  public void setData(List<CityListBean> list){
    this.mList.clear();
    this.mList.addAll(list);
    notifyDataSetChanged();
  }
  public RyCityContentAdapter(Context context) {
    this.mContext = context;
  }

  @Override public RyCityItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    RyCityItemViewHolder holder = new RyCityItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.ry_city_content_item, parent, false));
    return holder;
  }

  @Override public void onBindViewHolder(RyCityItemViewHolder holder, int position) {
    //Log.i("onBindViewHolder", "onBindViewHolder: " + mList.get(position).sName);
    //holder.tvCitySName.setText(mList.get(position).sName);
  }

  @Override public int getItemCount() {
    return mList.size();
  }

  public class RyCityItemViewHolder extends RecyclerView.ViewHolder {
    private TextView tvCitySName;

    public RyCityItemViewHolder(View itemView) {
      super(itemView);
      tvCitySName = (TextView) itemView.findViewById(R.id.tv_city_content);
    }
  }
}
