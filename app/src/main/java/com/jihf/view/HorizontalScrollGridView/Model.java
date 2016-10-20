package com.jihf.view.HorizontalScrollGridView;

/**
 * Func：
 * User：jihf
 * Data：2016-10-20
 * Time: 10:33
 * Mail：jihaifeng@raiyi.com
 */
public class Model {
    public String name;
    public int iconRes;

    public Model(String name, int iconRes) {
        this.name = name;
        this.iconRes = iconRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }
}