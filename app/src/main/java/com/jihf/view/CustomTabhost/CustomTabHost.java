package com.jihf.view.CustomTabhost;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import java.util.ArrayList;

/**
 * Func：重写FragmentTabHost实现状态保存 ,切换Fragment的方式detach和attach改为hide和show
 * User：jihf
 * Data：2016-08-19
 * Time: 16:36
 * Mail：jihaifeng@raiyi.com
 */
public class CustomTabHost extends TabHost implements TabHost.OnTabChangeListener {
  private Context mContext;
  private FragmentManager mFragmentManager;
  private OnTabChangeListener mOnTabChangeListener;
  private FrameLayout mRealTabContent;// fragment内容显示容器布局
  private int mContainerId;// fragment内容显示容器的 id
  private boolean mAttached;
  private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
  private TabInfo mLastTab;

  public CustomTabHost(Context context) {
    this(context, null);
  }

  public CustomTabHost(Context context, AttributeSet attrs) {
    super(context, attrs);
    initFragmentTabHost(context, attrs);
  }

  private void initFragmentTabHost(Context context, AttributeSet attrs) {
    /**
     * TypedArray是一个用于存放恢复<br>
     * obtainStyledAttributes(AttributeSet, int[], int, int)<br>
     * 或 obtainAttributes(AttributeSet, int[]) 值的一个数组容器，
     */
    // 使用 TypedArray 来获取 XML layout 中的属性值
    TypedArray a = context.obtainStyledAttributes(attrs, new int[] { android.R.attr.inflatedId }, 0, 0);
    // 调用 recyle() 方法将 TypedArray 回收
    this.mContainerId = a.getResourceId(0, 0);
    a.recycle();
    super.setOnTabChangedListener(this);
  }

  private void ensureHierarchy(Context context) {
    // If owner hasn't made its own view hierarchy, then as a convenience
    // we will construct a standard one here.
    if (findViewById(android.R.id.tabs) == null) {
      LinearLayout ll = new LinearLayout(context);
      ll.setOrientation(LinearLayout.VERTICAL);
      addView(ll, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

      TabWidget tw = new TabWidget(context);
      tw.setId(android.R.id.tabs);
      tw.setOrientation(TabWidget.HORIZONTAL);
      ll.addView(tw, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0));

      FrameLayout fl = new FrameLayout(context);
      fl.setId(android.R.id.tabcontent);
      ll.addView(fl, new LinearLayout.LayoutParams(0, 0, 0));

      mRealTabContent = fl = new FrameLayout(context);
      mRealTabContent.setId(mContainerId);
      ll.addView(fl, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
    }
  }

  /**
   * @deprecated Don't call the original TabHost setup, you must instead
   * call {@link #setup(Context, FragmentManager)} or
   * {@link #setup(Context, FragmentManager, int)}.
   */
  @Override @Deprecated public void setup() {
    throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
  }

  public void setup(Context context, FragmentManager manager) {
    ensureHierarchy(context);  // Ensure views required by super.setup()
    super.setup();
    mContext = context;
    mFragmentManager = manager;
    ensureContent();
  }

  /**
   * 解决下面 TabSpec.setContent 时出现空指针异常
   *
   * @param context 上下文
   * @param manager FragmentManager
   * @param containerId fragment内容显示容器的 id
   */
  public void setup(Context context, FragmentManager manager, int containerId) {
    ensureHierarchy(context);
    super.setup();
    this.mContext = context;
    this.mFragmentManager = manager;
    this.mContainerId = containerId;
    ensureContent();// 设置fragment内容显示的容器布局
    this.mRealTabContent.setId(containerId);// 设置fragment内容显示的容器布局的id

    if (getId() == -1) {
      setId(containerId);
    }
  }

  /**
   * 设置fragment内容显示的容器布局
   */
  private void ensureContent() {
    if (mRealTabContent == null) {
      mRealTabContent = (FrameLayout) findViewById(mContainerId);
      if (mRealTabContent == null) {
        throw new IllegalStateException("No tab content FrameLayout found for id " + mContainerId);
      }
    }
  }

  @Override public void setOnTabChangedListener(OnTabChangeListener l) {
    this.mOnTabChangeListener = l;
  }

  public void addTab(TabSpec tabSpec, Class<?> clss, Bundle args) {
    tabSpec.setContent(new DummyTabFactory(mContext));
    String tag = tabSpec.getTag();

    TabInfo info = new TabInfo(tag, clss, args);

    if (mAttached) {
      // If we are already attached to the window, then check to make
      // sure this tab's fragment is inactive if it exists.  This shouldn't
      // normally happen.
      info.fragment = mFragmentManager.findFragmentByTag(tag);
      if (info.fragment != null && !info.fragment.isDetached()) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        //ft.detach(info.fragment);
        ft.hide(info.fragment);
        ft.commit();
      }
    }

    mTabs.add(info);
    addTab(tabSpec);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();

    String currentTab = getCurrentTabTag();

    // Go through all tabs and make sure their fragments match
    // the correct state.
    FragmentTransaction ft = null;
    for (int i = 0; i < mTabs.size(); i++) {
      TabInfo tab = mTabs.get(i);
      tab.fragment = mFragmentManager.findFragmentByTag(tab.tag);
      if (tab.fragment != null && !tab.fragment.isDetached()) {
        if (tab.tag.equals(currentTab)) {
          // The fragment for this tab is already there and
          // active, and it is what we really want to have
          // as the current tab.  Nothing to do.
          mLastTab = tab;
        } else {
          // This fragment was restored in the active state,
          // but is not the current tab.  Deactivate it.
          if (ft == null) {
            ft = mFragmentManager.beginTransaction();
          }
          //ft.detach(tab.fragment);
          ft.hide(tab.fragment);
        }
      }
      // We are now ready to go.  Make sure we are switched to the
      // correct tab.
      mAttached = true;
      ft = doTabChanged(currentTab, ft);
      if (ft != null) {
        ft.commit();
        mFragmentManager.executePendingTransactions();
      }
    }
  }

  private FragmentTransaction doTabChanged(String tabId, FragmentTransaction ft) {
    TabInfo newTab = null;
    for (int i = 0; i < mTabs.size(); i++) {
      TabInfo tab = mTabs.get(i);
      if (tab.tag.equals(tabId)) {
        newTab = tab;
      }
    }
    if (newTab == null) {
      throw new IllegalStateException("No tab known for tag " + tabId);
    }
    if (mLastTab != newTab) {
      if (ft == null) {
        ft = mFragmentManager.beginTransaction();
      }
      if (mLastTab != null) {
        if (mLastTab.fragment != null) {
          //ft.detach(mLastTab.fragment);
          ft.hide(mLastTab.fragment);
        }
      }
      if (newTab != null) {
        if (newTab.fragment == null) {
          newTab.fragment = Fragment.instantiate(mContext, newTab.clss.getName(), newTab.args);
          ft.add(mContainerId, newTab.fragment, newTab.tag);
        } else {
          //ft.attach(newTab.fragment);
          ft.show(newTab.fragment);
        }
      }

      mLastTab = newTab;
    }
    return ft;
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    this.mAttached = false;
  }

  @Override protected Parcelable onSaveInstanceState() {
    Parcelable superState = super.onSaveInstanceState();
    SavedState ss = new SavedState(superState);
    ss.curTab = getCurrentTabTag();
    return ss;
  }

  @Override protected void onRestoreInstanceState(Parcelable state) {
    SavedState ss = (SavedState) state;
    super.onRestoreInstanceState(ss.getSuperState());
    setCurrentTabByTag(ss.curTab);
  }

  @Override public void onTabChanged(String tabId) {
    if (this.mAttached) {
      FragmentTransaction ft = doTabChanged(tabId, null);
      if (ft != null) {
        ft.commitAllowingStateLoss();
      }
    }
    if (this.mOnTabChangeListener != null) {
      this.mOnTabChangeListener.onTabChanged(tabId);
    }
  }
}
