package com.jihf.view.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import com.jihf.view.R;
import com.jihf.view.svg.SvgView;

/**
 * Func：
 * Desc:
 * Author：jihf
 * Data：2017-01-19 17:31
 * Mail：jihaifeng@raiyi.com
 */
public class SvgActivity extends AppCompatActivity {
  SvgView svgView;
  Button show;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_svg);
    svgView = (SvgView) findViewById(R.id.svgView);
    show = (Button) findViewById(R.id.show);

    show.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        show();
      }
    });
  }

  private void show() {
    svgView.getPathAnimator().
        //svgView.getSequentialPathAnimator().
            delay(100).
        duration(3000).
        interpolator(new AccelerateDecelerateInterpolator()).
        start();
  }
}
