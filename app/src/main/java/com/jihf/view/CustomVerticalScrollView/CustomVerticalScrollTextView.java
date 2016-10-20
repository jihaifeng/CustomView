package com.jihf.view.CustomVerticalScrollView;

import android.content.Context;
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
    setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
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
      viewFlipper.addView(view);
    }
    findViewById(R.id.ll_more).setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        if (listener != null) {
          listener.onMoreClick();
        }
      }
    });
    //进入动画
    viewFlipper.setInAnimation(getContext(), R.anim.vertical_scroll_view_in);
    //退出动画
    viewFlipper.setOutAnimation(getContext(), R.anim.vertical_scroll_view_out);
    //动画间隔
    viewFlipper.setFlipInterval(2000);
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
    for (final ScrollTextDataBean bean : list) {
      final ScrollTextDataBean textDataBean = bean;
      final View view = LayoutInflater.from(mContext).inflate(R.layout.vertical_scroll_view_item, viewFlipper, false);
      final TextView tv_title = (TextView) view.findViewById(R.id.tv_content_title);
      final TextView tv_content = (TextView) view.findViewById(R.id.tv_content_desc);
      //设置内容左边文字
      if (TextUtils.isEmpty(bean.contentTitle)) {
        tv_title.setVisibility(View.GONE);
      } else {
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(bean.contentTitle);
      }
      //设置内容文字
      tv_content.setText(bean.contentDesc);
      view.setOnClickListener(new OnClickListener() {
        @Override public void onClick(View v) {
          if (listener != null) {
            listener.onContentClick(textDataBean);
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
   * 设置左边图片是否显示
   */
  public void setLeftImageVisibility(int visibility) {
    iv_left.setVisibility(visibility);
  }

  /**
   * 设置右边更多是否显示
   */
  public void setRightMoreVisibility(int visibility) {
    ll_more.setVisibility(visibility);
  }
}
