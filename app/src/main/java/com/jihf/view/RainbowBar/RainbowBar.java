package com.jihf.view.RainbowBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.jihf.view.R;
import com.jihf.view.utils.ScreenUtil;

/**
 * Func：
 * Desc:
 * Author：jihf
 * Date：2017-05-02 10:03
 * Mail：jihaifeng@raiyi.com
 */
public class RainbowBar extends View {
  public static final String TAG = RainbowBar.class.getSimpleName().trim();
  private int rainbowBar_color_1 = Color.parseColor("#FF0000FF");// default color blue
  private int rainbowBar_color_2 = Color.parseColor("#FFFF0000");// default color red
  private int rainbowBar_color_3 = Color.parseColor("#FF00FF00");// default color green
  private int rainbowBar_width = ScreenUtil.getInstance(getContext()).dp2px(80);
  private int rainbowBar_height = ScreenUtil.getInstance(getContext()).dp2px(4);
  private int rainbowBar_space = ScreenUtil.getInstance(getContext()).dp2px(10);
  private Paint paint;
  private float delta = 5f;
  private float startX = 0;
  private int i = 0;

  public RainbowBar(Context context) {
    this(context, null);
  }

  public RainbowBar(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RainbowBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.RainbowBar);
    rainbowBar_color_1 = t.getColor(R.styleable.RainbowBar_rainbowBar_color_1, rainbowBar_color_1);
    rainbowBar_color_2 = t.getColor(R.styleable.RainbowBar_rainbowBar_color_2, rainbowBar_color_2);
    rainbowBar_color_3 = t.getColor(R.styleable.RainbowBar_rainbowBar_color_3, rainbowBar_color_2);
    rainbowBar_height = t.getDimensionPixelOffset(R.styleable.RainbowBar_rainbowBar_height, rainbowBar_height);
    rainbowBar_width = t.getDimensionPixelOffset(R.styleable.RainbowBar_rainbowBar_width, rainbowBar_width);
    t.recycle();
    startPaint();
  }

  private void startPaint() {
    paint = new Paint();
    //paint.setColor(rainbowBar_color_1);
    // 设置抗锯齿，如果不设置，加载位图的时候可能会出现锯齿状的边界，如果设置，边界就会变的稍微有点模糊，锯齿就看不到了
    paint.setAntiAlias(true);
    // 画笔样式为空心时，设置空心画笔的宽度
    paint.setStrokeWidth(rainbowBar_height);
    // 填充内部和描边
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    // 获取屏幕宽度
    int screenWidth = getMeasuredWidth();
    // 一个循环的宽度
    int width = rainbowBar_width + rainbowBar_space;
    // 一屏几个循环
    int num = screenWidth % width;
    if (startX >= screenWidth + width - num) {
      startX = 0;
    } else {
      startX += delta;
    }
    // 画笔起点
    float start = startX;
    // draw latter parse
    while (start < screenWidth) {
      canvas.drawLine(start, 5, start + rainbowBar_width, 5, paint);
      start += width;
      i++;
      setPaintColor(i);
    }

    start = startX - width;
    // draw front parse
    while (start >= -rainbowBar_width) {
      canvas.drawLine(start, 5, start + rainbowBar_width, 5, paint);
      start -= (rainbowBar_width + rainbowBar_space);
      i++;
      setPaintColor(i);
    }
    //if (index >= 700000) {
    //  index = 0;
    //}

    invalidate();
  }

  private void setPaintColor(int i) {
    Log.i(TAG, "setPaintColor: " + i);
    if (i % 3 == 2) {
      paint.setColor(rainbowBar_color_1);
    } else if (i % 3 == 1) {
      paint.setColor(rainbowBar_color_2);
    } else {
      paint.setColor(rainbowBar_color_3);
    }
  }
}
