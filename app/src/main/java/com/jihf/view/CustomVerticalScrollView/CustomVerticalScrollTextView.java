package com.jihf.view.CustomVerticalScrollView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.jihf.view.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Func：仿京东头条上下滚动通知
 * User：jihf
 * Data：2016-10-19
 * Time: 17:40
 * Mail：jihaifeng@raiyi.com
 */
public class CustomVerticalScrollTextView extends RelativeLayout {
  private VerticalScrollTextViewListener listener;
  private ViewFlipper viewFlipper;
  private List<View> data = new ArrayList<>();
  private Context mContext;
  private ImageView iv_left;
  private LinearLayout ll_more;
  private TextView tv_more;

  public CustomVerticalScrollTextView(Context context) {
    this(context, null);
  }

  public CustomVerticalScrollTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    mContext = context;
    setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
  }

  private void initView() {
    View rootView = LayoutInflater.from(getContext()).inflate(R.layout.vertical_scroll_view_layout, this, true);
    viewFlipper = (ViewFlipper) rootView.findViewById(R.id.vf_content);
    ll_more = (LinearLayout) rootView.findViewById(R.id.ll_more);
    tv_more = (TextView) rootView.findViewById(R.id.tv_more);
    iv_left = (ImageView) findViewById(R.id.iv_left);
    //默认隐藏左右两边
    ll_more.setVisibility(View.GONE);
    iv_left.setVisibility(View.GONE);
    for (View view : data) {
      if (null != view.getParent()) {
        return;
        //((ViewGroup) view.getParent()).removeView(view);
      }
      viewFlipper.addView(view);
    }
    ll_more.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        if (listener != null) {
          listener.onRightTextClick();
        }
      }
    });
    iv_left.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        if (listener != null) {
          listener.onLeftImageClick();
        }
      }
    });
    //进入动画
    viewFlipper.setInAnimation(getContext(), R.anim.vertical_scroll_view_in);
    //退出动画
    viewFlipper.setOutAnimation(getContext(), R.anim.vertical_scroll_view_out);
    //动画间隔
    viewFlipper.setFlipInterval(5000);
    viewFlipper.startFlipping();
  }

  //配置滚动的数据
  public void setData(List<ScrollTextDataBean> data) {
    if (null == data) {
      throw new NullPointerException("data is null");
    }
    convertData(data);
    initView();
  }

  //将HeadlineBean数据转换成View数据
  private void convertData(final List<ScrollTextDataBean> list) {
    for (int i = 0; i < list.size(); i++) {
      final int k = i;
      final ScrollTextDataBean textDataBean = list.get(i);
      final View view = LayoutInflater.from(mContext).inflate(R.layout.vertical_scroll_view_item, viewFlipper, false);
      final TextView tv_title = (TextView) view.findViewById(R.id.tv_content_title);
      final TextView tv_content = (TextView) view.findViewById(R.id.tv_content_desc);
      //设置内容左边文字
      if (TextUtils.isEmpty(textDataBean.contentTitle)) {
        tv_title.setVisibility(View.GONE);
      } else {
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(textDataBean.contentTitle);
      }
      //设置内容文字
      tv_content.setText(textDataBean.contentDesc);
      view.setOnClickListener(new OnClickListener() {
        @Override public void onClick(View v) {
          if (listener != null) {
            listener.onContentClick(textDataBean, k);
          }
        }
      });
      data.add(view);
    }
  }

  public void setViewClickListener(VerticalScrollTextViewListener listener) {
    this.listener = listener;
  }

  /**
   * 设置左边显示的图片
   */
  public void setLeftImage(Drawable imageDrawable) {
    if (null != iv_left && null != imageDrawable) {
      iv_left.setImageDrawable(imageDrawable);
      iv_left.setVisibility(View.VISIBLE);
    }
  }

  /**
   * 设置右边显示的文字
   */
  public void setRightText(String text) {
    if (null != ll_more && null != tv_more) {
      ll_more.setVisibility(View.VISIBLE);
      tv_more.setText(TextUtils.isEmpty(text) ? "更多" : text);
    }
  }

  /**
   * 设置显示时间
   */
  public void setDuration(int duration) {
    if (null != viewFlipper) {
      viewFlipper.setFlipInterval(duration < 3 ? 3 : duration);
    }
  }
}
