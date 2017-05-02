package com.jihf.view.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jihf.view.CityPick.CityBean;
import com.jihf.view.CityPick.CityListBean;
import com.jihf.view.CityPick.CitySortBean;
import com.jihf.view.CityPick.ReadAssets;
import com.jihf.view.CityPick.RyCityContentAdapter;
import com.jihf.view.CityPick.RyCityTitleAdapter;
import com.jihf.view.R;
import java.util.ArrayList;
import java.util.List;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;
import me.yokeyword.indexablerv.SimpleHeaderAdapter;

/**
 * Func：
 * User：jihf
 * Data：2016-12-08
 * Time: 13:38
 * Mail：jihaifeng@raiyi.com
 */
public class CityPickActivity extends AppCompatActivity {
  public static final String TAG = CityPickActivity.class.getSimpleName().trim();
  //城市数据列表
  RecyclerView ryCity;
  //数据异常提示
  TextView tvNoCityResult;
  //数据加载进度条
  ProgressBar pbCity;
  //索引
  IndexableLayout cityRoot;

  private List<CityListBean> hotCityBean = new ArrayList<>();
  private List<CityListBean> allCityBean = new ArrayList<>();
  private RyCityTitleAdapter ryCityTitleAdapter;
  private RyCityContentAdapter ryCityContentAdapter;

  @Override

  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_city_pick);
    //ButterKnife.bind(CityPickActivity.this);
    initView();
    initAdapter();
    getCityData();
  }

  private void initView() {
    ryCity = (RecyclerView) findViewById(R.id.ry_city);
    tvNoCityResult = (TextView) findViewById(R.id.tv_noCityResult);
    pbCity = (ProgressBar) findViewById(R.id.pb_city);
    cityRoot = (IndexableLayout) findViewById(R.id.city_root);
  }

  private void initAdapter() {
    ryCityTitleAdapter = new RyCityTitleAdapter(CityPickActivity.this);
    cityRoot.setAdapter(ryCityTitleAdapter);
    ryCityContentAdapter = new RyCityContentAdapter(CityPickActivity.this);
    ryCity.setLayoutManager(new LinearLayoutManager(CityPickActivity.this));
    ryCity.setHasFixedSize(true);
    ryCity.setAdapter(ryCityContentAdapter);
  }

  private void getCityData() {
    String cityData = new ReadAssets().readAssetsDataV2(this, "super_city");
    if (cityData != null && cityData.startsWith("\ufeff")) {
      cityData = cityData.substring(1);
    }
    try {
      CityBean cityBean = new Gson().fromJson(cityData, CityBean.class);
      if (null != cityBean && cityBean.status.equals("1")) {
        updateViewStatu(true, null);
        updateCityData(cityBean);
      } else {
        Toast.makeText(this, "数据出错", Toast.LENGTH_SHORT).show();
        updateViewStatu(false, "获取数据失败");
      }
    } catch (JsonSyntaxException e) {
      updateViewStatu(false, "数据异常");
      e.printStackTrace();
    }
  }

  private void updateViewStatu(boolean flag, String msg) {
    pbCity.setVisibility(View.GONE);
    if (flag) {
      //正常数据
      ryCity.setVisibility(View.VISIBLE);
      tvNoCityResult.setVisibility(View.GONE);
    } else {
      //数据异常
      ryCity.setVisibility(View.GONE);
      tvNoCityResult.setVisibility(View.VISIBLE);
      tvNoCityResult.setText(TextUtils.isEmpty(msg) ? "没有匹配到相应的数据" : msg);
    }
  }

  private void updateCityData(CityBean cityBean) {
    if (null == cityBean || null == cityBean.citySort || cityBean.citySort.size() == 0) {
      return;
    }
    List<CitySortBean> citySortBeans = cityBean.citySort;
    for (CitySortBean citySortBean : citySortBeans) {
      if (null == citySortBean) {
        return;
      }
      if (citySortBean.key.equals("热点")) {
        hotCityBean.addAll(citySortBean.cityList);
      } else {
        allCityBean.addAll(citySortBean.cityList);
      }
    }
    updateRyView();
  }

  private void updateRyView() {
    // 快速排序。  排序规则设置为：只按首字母  （默认全拼音排序）  效率很高，是默认的10倍左右。  按需开启～
    //        indexableLayout.setFastCompare(true);
    ryCityTitleAdapter.setDatas(allCityBean, new IndexableAdapter.IndexCallback<CityListBean>() {
          @Override public void onFinished(List<CityListBean> datas) {
            // 数据处理完成后回调
            Log.i(TAG, "onFinished: " + datas.get(0));
            Log.i(TAG, "onFinished: " + allCityBean.get(0));
            ryCityContentAdapter.setData(datas);
          }
        });
        // 热门城市
        cityRoot.addHeaderAdapter(new SimpleHeaderAdapter<>(ryCityTitleAdapter, "热", "热门城市", hotCityBean));
  }
}
