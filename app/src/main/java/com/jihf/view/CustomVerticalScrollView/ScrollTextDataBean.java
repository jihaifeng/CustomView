package com.jihf.view.CustomVerticalScrollView;

/**
 * Func：
 * User：jihf
 * Data：2016-10-19
 * Time: 18:21
 * Mail：jihaifeng@raiyi.com
 */
public class ScrollTextDataBean {
  public String imageUrl;
  public String contentTitle;
  public String contentDesc;
  public String moreDesc;

  public ScrollTextDataBean(String contentTitle, String contentDesc) {
    this(null, contentTitle, contentDesc);
  }

  public ScrollTextDataBean(String imageUrl, String contentTitle, String contentDesc) {
    this(imageUrl, contentTitle, contentDesc, "更多");
  }

  public ScrollTextDataBean(String imageUrl, String contentTitle, String contentDesc, String moreDesc) {
    this.imageUrl = imageUrl;
    this.contentTitle = contentTitle;
    this.contentDesc = contentDesc;
    this.moreDesc = moreDesc;
  }
}
