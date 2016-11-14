package com.jihf.view.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.jihf.view.CustomSlideButton.OnToggleStateChangedListener;
import com.jihf.view.CustomSlideButton.SlideButton;
import com.jihf.view.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  private Button verticalTextView;
  private Button couponView;
  private Button scrollGridView;
  private Button location;

  private SlideButton slideButton;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();

  }

  private void getLocation() {
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      // 缺少权限
      ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, 100);
    } else {
      //已有权限
      getMyLocation();
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case 100:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          //用户允许
         getMyLocation();
        } else {
          //用户拒绝
          Toast.makeText(this, "对不起，缺少权限", Toast.LENGTH_SHORT).show();
        }
        break;
    }
  }

  private void getMyLocation() {
    LocationManager  locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    double lat = location.getLatitude();
    double lng = location.getLongitude();
    Toast.makeText(this, lat + "\n" + lng, Toast.LENGTH_SHORT).show();
  }

  private void initView() {
    verticalTextView = (Button) findViewById(R.id.btn_verticalTextView);
    verticalTextView.setOnClickListener(this);
    couponView = (Button) findViewById(R.id.btn_couponView);
    couponView.setOnClickListener(this);
    scrollGridView = (Button) findViewById(R.id.btn_scrollGridView);
    scrollGridView.setOnClickListener(this);
    slideButton = (SlideButton) findViewById(R.id.slideButton);
    location = (Button) findViewById(R.id.btn_location);
    location.setOnClickListener(this);
    slideButton.setToggleState(true);
    slideButton.setOnToggleStateChangedListener(new OnToggleStateChangedListener() {
      @Override public void onToggleStateChanged(boolean state) {
        if (state) {
          Toast.makeText(MainActivity.this, "true", Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(MainActivity.this, "false", Toast.LENGTH_SHORT).show();
        }
      }
    });
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
      case R.id.btn_location:
        //定位
        getLocation();
        break;
    }
  }

  private void jumpTo(Class<?> activity) {
    Intent intent = new Intent(MainActivity.this, activity);
    startActivity(intent);
  }
}
