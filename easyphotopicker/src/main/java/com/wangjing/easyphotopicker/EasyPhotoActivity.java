package com.wangjing.easyphotopicker;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.wangjing.easyphotopicker.base.BaseMvpActivity;
import com.wangjing.easyphotopicker.myinterface.EasyPhotoActivityInterface;
import com.wangjing.easyphotopicker.presenter.EasyPhotoActivityPresenter;

public class EasyPhotoActivity extends BaseMvpActivity<EasyPhotoActivityInterface, EasyPhotoActivityPresenter> implements EasyPhotoActivityInterface {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easyphoto);
    }

    @Override
    protected EasyPhotoActivityPresenter createPresenter() {
        return new EasyPhotoActivityPresenter(this);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hidLoading() {

    }

    @Override
    public void showData() {

    }
}
