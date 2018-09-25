package com.wangjing.easyphotopicker.presenter;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.wangjing.easyphotopicker.base.BasePresenter;
import com.wangjing.easyphotopicker.model.AlbumModel;
import com.wangjing.easyphotopicker.myinterface.EasyPhotoActivityInterface;

import java.util.List;

public class EasyPhotoActivityPresenter extends BasePresenter<EasyPhotoActivityInterface> implements AlbumModel.AlbumCallbacks {
    //EasyPhotoActivityInterface接口，代表了View接口角色
    private EasyPhotoActivityInterface mView;


    public EasyPhotoActivityPresenter(EasyPhotoActivityInterface easyPhotoActivityInterface) {
        mView = easyPhotoActivityInterface;
        this.attachView(easyPhotoActivityInterface);
    }

    public void getData(FragmentActivity fragmentActivity) {
        mView.showLoading();
        AlbumModel.getAlbums(fragmentActivity, this);
    }


    @Override
    public void onAlbumSuc(List<String> allAlbums) {
        if (allAlbums != null && !allAlbums.isEmpty()) {
            for (int i = 0; i < allAlbums.size(); i++) {
                Log.e("path", "" + allAlbums.get(i));
            }
        }
        mView.showData(allAlbums);
    }
}
