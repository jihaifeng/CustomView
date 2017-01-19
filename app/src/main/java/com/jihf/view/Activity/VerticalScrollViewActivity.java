package com.jihf.view.Activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.jihf.view.CustomVerticalScrollView.CustomVerticalScrollTextView;
import com.jihf.view.CustomVerticalScrollView.ScrollTextDataBean;
import com.jihf.view.CustomVerticalScrollView.VerticalScrollTextViewListener;
import com.jihf.view.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Func：
 * User：jihf
 * Data：2016-10-20
 * Time: 10:16
 * Mail：jihaifeng@raiyi.com
 */
public class VerticalScrollViewActivity extends AppCompatActivity {
  private CustomVerticalScrollTextView scroll_text_view;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_vertical_scroll_view);
    List<ScrollTextDataBean> data = new ArrayList<>();
    data.add(new ScrollTextDataBean("热门", "袜子裤子只要998～只要998～"));
    data.add(new ScrollTextDataBean(null, "秋冬上心，韩流服饰，一折起"));
    data.add(new ScrollTextDataBean("好货", "品牌二手车"));
    data.add(new ScrollTextDataBean("", "MadCatz MMO7 游戏鼠标键盘套装"));

    scroll_text_view = (CustomVerticalScrollTextView) findViewById(R.id.scroll_text_view);
    scroll_text_view.setData(data);
    scroll_text_view.setLeftImage(ContextCompat.getDrawable(this, R.drawable.jd_toutiao));
    scroll_text_view.setRightText("查阅");
    scroll_text_view.setViewClickListener(new VerticalScrollTextViewListener() {

      @Override public void onContentClick(ScrollTextDataBean bean, int position) {
        Toast.makeText(VerticalScrollViewActivity.this, position + "  " + bean.contentTitle + ":" + bean.contentDesc,
            Toast.LENGTH_SHORT).show();
      }

      @Override public void onRightTextClick() {
        Toast.makeText(VerticalScrollViewActivity.this, "更多", Toast.LENGTH_SHORT).show();
      }

      @Override public void onLeftImageClick() {
        Toast.makeText(VerticalScrollViewActivity.this, "Image", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
