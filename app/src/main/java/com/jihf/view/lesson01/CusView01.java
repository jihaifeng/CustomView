package com.jihf.view.lesson01;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Func：
 * Desc:
 * Author：jihf
 * Date：2017-05-02 9:47
 * Mail：jihaifeng@raiyi.com
 */
public class CusView01 extends View {
  public CusView01(Context context) {
    this(context,null);
    // 一般我们这样使用时会被调用，View view = new View(context)
  }

  public CusView01(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs,0);
    // 当我们在xml布局文件中使用View时，会在inflate布局时被调用，
    // <CusView01 layout_width="match_parent" layout_height="match_parent"/>
  }

  public CusView01(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    // 跟第二种类似，但是增加style属性设置，这时inflater布局时会调用第三个构造方法
    // <View style="@styles/MyCustomStyle" layout_width="match_parent" layout_height="match_parent"/>
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    // View先做一次测量，算出自己需要占用多大的面积
    setMeasuredDimension(100,100);
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    // onLayout方法中，我们需要将所有子View的大小宽高设置好
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    // Draw过程，就是在canvas上画出我们需要的View样式
  }

  @Override public void requestLayout() {
    // View重新调用一次layout过程。
    super.requestLayout();
  }

  @Override public void invalidate() {
    super.invalidate();
    // View重新调用一次draw过程
  }

  @Override public void forceLayout() {
    super.forceLayout();
    // 标识View在下一次重绘，需要重新调用layout过程
  }
}
