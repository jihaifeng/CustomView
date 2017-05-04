package com.jihf.view.SnowFalling;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.jihf.view.R;

/**
 * Func：
 * Desc:
 * Author：jihf
 * Date：2017-05-04 10:02
 * Mail：jihaifeng@raiyi.com
 */
public class FallingView extends RelativeLayout {
  // 碎片的大小，数值越大，碎片越小，默认值是3
  private final int DEFAULT_SCALE = 3;
  private int fallingScale = DEFAULT_SCALE;

  // 密度，数值越大，碎片越密集,默认值是80
  private final int DEFAULT_DENSITY = 80;
  private int fallingDensity = DEFAULT_DENSITY;

  // 碎片飘落的速度，数值越大，飘落的越慢，默认值是10
  private final int DEFAULT_DELAY = 10;
  private int fallingDelay = DEFAULT_DELAY;

  // 碎片的图片,默认的图片是雪花
  private int DEFAULT_img = R.drawable.snow_falling;
  private int fallingImg = DEFAULT_img;

  // 自动飘落，true 是 false 否
  private boolean isAutoFalling = false;

  // 画笔
  private Paint mPaint;

  //
  private int mWidth;
  private int mHeight;
  private int mRawWidth;

  //
  private Flake mFlakes[];
  private Bitmap mFlakeBitmap;

  public FallingView(Context context) {
    this(context, null);
  }

  public FallingView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public FallingView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    setBackgroundColor(Color.TRANSPARENT);
    if (null != attrs) {
      TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.FallingView);
      fallingImg = t.getResourceId(R.styleable.FallingView_fallingSrc, DEFAULT_img);
      fallingScale = t.getInteger(R.styleable.FallingView_fallingScale, DEFAULT_SCALE);
      fallingDelay = t.getInt(R.styleable.FallingView_fallingDelay, DEFAULT_DELAY);
      fallingDensity = t.getInt(R.styleable.FallingView_fallingDensity, DEFAULT_DENSITY);
      isAutoFalling = t.getBoolean(R.styleable.FallingView_isAutoFalling, false);
      t.recycle();
    }
    // ANTI_ALIAS_FLAG 抗锯齿 或者     paint.setAntiAlias(true);
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setColor(Color.WHITE);
    // 实心
    // Style.FILL：实心。
    // Style.FILL_AND_STROKE：同时实心和空心，该参数在某些场合会带来不可预期的显示效果。
    // Style.STROKE：空心。
    mPaint.setStyle(Paint.Style.FILL);
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    if (w != oldw || h != oldh) {
      mWidth = w;
      mHeight = h;
      mRawWidth = initScale(fallingScale);
      initDenstity(w, h, mRawWidth);
    }
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    if (isAutoFalling) {
      for (Flake flake : mFlakes) {
        flake.draw(canvas, mFlakeBitmap);
      }
      getHandler().postDelayed(new MyRunnable(), fallingDelay);
    }
  }

  public void start() {
    if (isAutoFalling) {
      Toast.makeText(getContext(), "正在飘落", Toast.LENGTH_SHORT).show();
      return;
    }
    isAutoFalling = true;
    requestLayout();
  }

  public void cancel() {
    if (!isAutoFalling) {
      Toast.makeText(getContext(), "已经停止", Toast.LENGTH_SHORT).show();
      return;
    }
    isAutoFalling = false;
    requestLayout();
  }

  public boolean isFalling() {
    return isAutoFalling;
  }

  private void initDenstity(int w, int h, int rawWidth) {
    mFlakes = new Flake[fallingDensity];
    for (int i = 0; i < fallingDensity; i++) {
      mFlakes[i] = Flake.create(w, h, mPaint, rawWidth / fallingScale);
    }
  }

  private int initScale(int scale) {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(getResources(), fallingImg, options);
    int rawWidth = options.outWidth;
    mRawWidth = rawWidth;
    options.inSampleSize = scale;
    options.inJustDecodeBounds = false;
    mFlakeBitmap = BitmapFactory.decodeResource(getResources(), fallingImg, options);
    return rawWidth;
  }

  private class MyRunnable implements Runnable {

    @Override public void run() {
      invalidate();
    }
  }

  public void setImageResource(int imgId) {
    this.fallingImg = imgId;
    initScale(fallingScale);
  }

  public void setScale(int scale) {
    initScale(scale);
  }

  public void setDensity(int density) {
    this.fallingDensity = density;
    initDenstity(mWidth, mHeight, mRawWidth);
  }

  public void setDelay(int delay) {
    this.fallingDelay = delay;
  }
}
