package com.wangjing.easyphotopicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.wangjing.easyphotopicker.adapter.EasyPhotoActivity_RecyclerView_Adapter;
import com.wangjing.easyphotopicker.base.BaseMvpActivity;
import com.wangjing.easyphotopicker.myinterface.EasyPhotoActivityInterface;
import com.wangjing.easyphotopicker.presenter.EasyPhotoActivityPresenter;

import java.util.List;

public class EasyPhotoActivity extends BaseMvpActivity<EasyPhotoActivityInterface, EasyPhotoActivityPresenter> implements EasyPhotoActivityInterface {


    private RecyclerView recyclerview;
    private EasyPhotoActivity_RecyclerView_Adapter easyPhotoActivity_recyclerView_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easyphoto);
        recyclerview = findViewById(R.id.recyclerview);
        easyPhotoActivity_recyclerView_adapter = new EasyPhotoActivity_RecyclerView_Adapter(this);
        mPresenter.getData(this);
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
    public void showData(List<String> allAlbums) {

    }


}
