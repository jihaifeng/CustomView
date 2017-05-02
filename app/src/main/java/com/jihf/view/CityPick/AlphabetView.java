package com.jihf.view.CityPick;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Func：
 * Desc:
 * Author：jihf
 * Date：2017-04-19 10:18
 * Mail：jihaifeng@raiyi.com
 */
public class AlphabetView extends View {
  private int choose = -1;
  private Paint paint = new Paint();
  public static String[] alphabetList = new String[] {};
  private int mDefaultColor = Color.parseColor("#ACACAC");
  private int mSelectColor = Color.parseColor("#ACACAC");

  /**
   * 初始化字母列表
   *
   * @param alphaList
   */
  public static void initList(String[] alphaList) {
    alphabetList = alphaList;
  }

  public AlphabetView(Context context) {
    super(context);
  }

  public AlphabetView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public AlphabetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    final int height = getHeight();
    final int width = getWidth();
    int singleHeight = height / alphabetList.length;
    final int size = alphabetList.length;

    int textSize = (int) (height / alphabetList.length * 0.8);

    for (int i = 0; i < size; i++) {
      paint.setColor(mDefaultColor);
      paint.setAntiAlias(true);
      paint.setTextSize(textSize == 0 ? 18 : textSize);

      if (i == choose) {
        paint.setColor(mSelectColor);
        paint.setFakeBoldText(true);
      }
      float xPos = width / 2 - paint.measureText(alphabetList[i]) / 2;
      float yPos = singleHeight * i + singleHeight;
      canvas.drawText(alphabetList[i], xPos, yPos, paint);
      paint.reset();
    }
  }
  /**
   * 设置字母列表默认颜色
   *
   * @param color
   */
  protected void setDefaultColor(int color) {
    mDefaultColor = color;
  }

  /**
   * 设置字母列表选中颜色
   *
   * @param color
   */
  protected void setSelectColor(int color) {
    mSelectColor = color;
  }
}
