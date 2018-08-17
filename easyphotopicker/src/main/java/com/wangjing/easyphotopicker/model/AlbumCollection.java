package com.wangjing.easyphotopicker.model;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.lang.ref.WeakReference;

/**
 * 相册集合
 */
public class AlbumCollection implements LoaderManager.LoaderCallbacks<Cursor> {

    interface AlbumCallbacks {
        void onAlbumLoad(Cursor cursor);
    }


    private AlbumCallbacks mCallbacks;
    private WeakReference<Context> mContext;

    public void onCreate(FragmentActivity activity, AlbumCallbacks albumCallbacks) {
        mContext = new WeakReference<Context>(activity);
        this.mCallbacks = albumCallbacks;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        Context context = mContext.get();
        if (context == null) {
            return null;
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
