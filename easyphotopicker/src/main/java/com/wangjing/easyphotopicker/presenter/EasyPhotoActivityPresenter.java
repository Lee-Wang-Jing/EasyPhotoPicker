package com.wangjing.easyphotopicker.presenter;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;

import com.wangjing.easyphotopicker.base.BasePresenter;
import com.wangjing.easyphotopicker.model.AlbumCollection;
import com.wangjing.easyphotopicker.myinterface.EasyPhotoActivityInterface;

public class EasyPhotoActivityPresenter extends BasePresenter<EasyPhotoActivityInterface> implements AlbumCollection.AlbumCallbacks {
    //EasyPhotoActivityInterface接口，代表了View接口角色
    private EasyPhotoActivityInterface mView;
    private AlbumCollection mAlbumCollection;


    public EasyPhotoActivityPresenter(EasyPhotoActivityInterface easyPhotoActivityInterface) {
        mView = easyPhotoActivityInterface;
    }

    public void getData() {
        if (mAlbumCollection == null) {
            mAlbumCollection = new AlbumCollection();
        }
        mView.showLoading();
    }

    @Override
    public void onAlbumLoad(Cursor cursor) {

    }

    @Override
    public void onAlbumReset() {

    }
}
