package com.jihf.view.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jihf.view.R;

/**
 * Func：
 * Desc:
 * Author：jihf
 * Data：2017-02-13 17:24
 * Mail：jihaifeng@raiyi.com
 */
public class VideoActivity extends AppCompatActivity {
  @BindView (R.id.btn_start) Button btnStart;
  @BindView (R.id.vv_show) VideoView vvShow;
  String videoUrl = "http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4";

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_video);
    ButterKnife.bind(this);
  }

  @OnClick ({ R.id.btn_start, R.id.vv_show }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_start:
        vvShow.setU
        vvShow.start();
        break;
      case R.id.vv_show:
        break;
    }
  }
}
