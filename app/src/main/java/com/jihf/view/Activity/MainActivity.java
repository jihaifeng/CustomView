package com.jihf.view.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.jihf.view.R;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback {
  private Button verticalTextView;
  private Button couponView;
  private Button scrollGridView;
  private Button city;
  private Button netType;
  private Button svg;
  private Button rainbow;
  private Button snowfall;
  private TextView tvNetType;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //初始化log日志类，并自定义log Tag
    //Logger.init();
    initView();
  }

  private void initView() {
    verticalTextView = (Button) findViewById(R.id.btn_verticalTextView);
    verticalTextView.setOnClickListener(this);
    couponView = (Button) findViewById(R.id.btn_couponView);
    couponView.setOnClickListener(this);
    scrollGridView = (Button) findViewById(R.id.btn_scrollGridView);
    scrollGridView.setOnClickListener(this);
    city = (Button) findViewById(R.id.btn_city);
    city.setOnClickListener(this);
    netType = (Button) findViewById(R.id.btn_netType);
    netType.setOnClickListener(this);
    svg = (Button) findViewById(R.id.btn_svg);
    svg.setOnClickListener(this);
    rainbow = (Button) findViewById(R.id.btn_rainbow);
    rainbow.setOnClickListener(this);
    snowfall = (Button) findViewById(R.id.btn_snowfall);
    snowfall.setOnClickListener(this);

    tvNetType = (TextView) findViewById(R.id.tv_netType);
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_verticalTextView:
        //仿京东滚动新闻条
        jumpTo(VerticalScrollViewActivity.class);
        break;
      case R.id.btn_couponView:
        //自定义优惠券
        jumpTo(CouponActivity.class);
        break;
      case R.id.btn_scrollGridView:
        //自定义优惠券
        jumpTo(HorizontalScrollGridViewActivity.class);
        break;
      case R.id.btn_city:
        //城市选择
        jumpTo(CityPickActivity.class);
        break;
      case R.id.btn_netType:
        //网络类型
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
            == PackageManager.PERMISSION_DENIED) {
          ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_NETWORK_STATE }, 100);
        } else {
          try {
            tvNetType.setText(URLEncoder.encode(getNetType(), "UTF-8"));
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          }
        }
        break;
      case R.id.btn_svg:
        //svgView
        jumpTo(SvgActivity.class);
        break;
      case R.id.btn_rainbow:
        jumpTo(RainbowBarActivity.class);
        break;
      case R.id.btn_snowfall:
        jumpTo(SnowFallingActivity.class);
        break;
    }
  }

  private String getNetType() {
    ConnectivityManager connectMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo info = connectMgr.getActiveNetworkInfo();
    if (info != null) {
      if (info.getType() == 1) {
        return TextUtils.isEmpty(info.getTypeName()) ? "未知" : info.getTypeName();
      } else {
        return TextUtils.isEmpty(info.getSubtypeName()) ? "未知" : info.getSubtypeName();
      }
    }
    return "未知";
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if (requestCode == 100) {
      if (grantResults.length > 0) {
        getNetType();
      } else {
        try {
          tvNetType.setText(URLEncoder.encode(getNetType(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private void jumpTo(Class<?> activity) {
    Intent intent = new Intent(MainActivity.this, activity);
    startActivity(intent);
  }
}
