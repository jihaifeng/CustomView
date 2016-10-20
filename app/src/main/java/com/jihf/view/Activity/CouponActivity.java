package com.jihf.view.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.jihf.view.R;
import java.util.Arrays;

/**
 * Func：
 * User：jihf
 * Data：2016-10-20
 * Time: 10:33
 * Mail：jihaifeng@raiyi.com
 */
public class CouponActivity extends AppCompatActivity {
  private ListView list_coupon;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_coupon);
    initView();
    setData();
  }

  private void initView() {
    list_coupon = (ListView) findViewById(R.id.list_coupon);
  }

  private void setData() {
    BaseAdapter arrayAdapter = new ArrayAdapter(this, R.layout.coupon_list_item, R.id.name, Arrays.asList("美食劵", "活动劵", "优惠劵", "团购劵", "外卖劵"));
    list_coupon.setAdapter(arrayAdapter);
  }
}
