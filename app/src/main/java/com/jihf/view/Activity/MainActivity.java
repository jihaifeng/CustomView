package com.jihf.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.jihf.view.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  private Button verticalTextView;
  private Button couponView;
  private Button scrollGridView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
  }

  private void initView() {
    verticalTextView = (Button) findViewById(R.id.btn_verticalTextView);
    verticalTextView.setOnClickListener(this);
    couponView = (Button) findViewById(R.id.btn_couponView);
    couponView.setOnClickListener(this);
     scrollGridView = (Button) findViewById(R.id.btn_scrollGridView);
     scrollGridView.setOnClickListener(this);
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
    }
  }

  private void jumpTo(Class<?> activity) {
    Intent intent = new Intent(MainActivity.this, activity);
    startActivity(intent);
  }
}
