package com.wangjing.easyphotopicker.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<T> {
    /**
     * View接口类型的弱引用
     */
    protected Reference<T> mViewRef;

    /**
     * 建立关联
     *
     * @param view view
     */
    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    /**
     * 获取View
     *
     * @return T
     */
    protected T getView() {
        return mViewRef.get();
    }

    /**
     * 判断是否与View建议了关联
     *
     * @return boolean
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 解除关联
     */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
