package com.wangjing.easyphotopicker.presenter;

import com.wangjing.easyphotopicker.base.BasePresenter;
import com.wangjing.easyphotopicker.myinterface.EasyPhotoActivityInterface;

public class EasyPhotoActivityPresenter extends BasePresenter<EasyPhotoActivityInterface> {
    //EasyPhotoActivityInterface接口，代表了View接口角色
    private EasyPhotoActivityInterface mView;

    public EasyPhotoActivityPresenter(EasyPhotoActivityInterface easyPhotoActivityInterface) {
        mView = easyPhotoActivityInterface;
    }

    public void getData(){
        mView.showLoading();


    }
}
