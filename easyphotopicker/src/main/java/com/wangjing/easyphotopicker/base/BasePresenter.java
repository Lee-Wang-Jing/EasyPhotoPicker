package com.wangjing.easyphotopicker.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<T> {
    /**
     * View接口类型的弱引用
     */
    protected Reference<T> mViewRef;

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

}
