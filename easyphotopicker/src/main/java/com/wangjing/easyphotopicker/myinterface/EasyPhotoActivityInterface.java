package com.wangjing.easyphotopicker.myinterface;

import java.util.List;

public interface EasyPhotoActivityInterface {
    void showLoading();

    void hidLoading();

    void showData(List<String> allAlbums);
}
