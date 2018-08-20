package com.wangjing.easyphotopicker.model;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.wangjing.easyphotopicker.loader.AlbumLoader;

import java.lang.ref.WeakReference;

/**
 * 相册集合
 * LoaderManager介绍
 * https://developer.android.com/guide/components/loaders
 */
public class AlbumCollection implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOADER_ID = 1;

    private boolean mLoadFinished;
    private LoaderManager mLoaderManager;

    public interface AlbumCallbacks {
        void onAlbumLoad(Cursor cursor);

        void onAlbumReset();
    }


    private AlbumCallbacks mCallbacks;
    private WeakReference<Context> mContext;

    public void onCreate(FragmentActivity activity, AlbumCallbacks albumCallbacks) {
        mContext = new WeakReference<Context>(activity);
        mLoaderManager = activity.getSupportLoaderManager();
        this.mCallbacks = albumCallbacks;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        Context context = mContext.get();
        if (context == null) {
            return null;
        }
        mLoadFinished = false;
        return AlbumLoader.newInstance(context);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        Context context = mContext.get();
        if (context == null) {
            return;
        }

        if (!mLoadFinished) {
            mLoadFinished = true;
            if (mCallbacks != null) {
                mCallbacks.onAlbumLoad(cursor);
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        Context context = mContext.get();
        if (context == null) {
            return;
        }
        if (mCallbacks != null) {
            mCallbacks.onAlbumReset();
        }
    }


    public void loadAlbums() {
        mLoaderManager.initLoader(LOADER_ID, null, this);
    }
}
