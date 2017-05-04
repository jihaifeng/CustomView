package com.jihf.view.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import com.jihf.view.R;
import com.jihf.view.SnowFalling.FallingView;

/**
 * Func：
 * Desc:
 * Author：jihf
 * Date：2017-05-04 9:46
 * Mail：jihaifeng@raiyi.com
 */
public class SnowFallingActivity extends AppCompatActivity {
  private FallingView fallingView;
  private Button start;
  private Button cancel;
  private SeekBar size;
  private SeekBar speed;
  private SeekBar density;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_snowfalling);
    initView();
  }

  private void initView() {
    fallingView = (FallingView) findViewById(R.id.falling);
    start = (Button) findViewById(R.id.start);
    start.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        fallingView.start();
      }
    });
    cancel = (Button) findViewById(R.id.cancel);
    cancel.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        fallingView.cancel();
      }
    });
    size = (SeekBar) findViewById(R.id.size);
    size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        fallingView.setScale(progress);
      }

      @Override public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
    speed = (SeekBar) findViewById(R.id.speed);
    speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        fallingView.setDelay(progress);
      }

      @Override public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
    density = (SeekBar) findViewById(R.id.density);
    density.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        fallingView.setDensity(progress);
      }

      @Override public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
    //fallingView.setImageResource(R.drawable.img1);//设置碎片的图片,默认的图片是雪花
    //fallingView.setDensity(progress);//设置密度，数值越大，碎片越密集,默认值是80
    //fallingView.setScale(progress);//设置碎片的大小，数值越大，碎片越小，默认值是3
    //fallingView.setDelay(progress);//设置碎片飘落的速度，数值越大，飘落的越慢，默认值是10
  }
}
