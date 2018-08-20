package com.wangjing.easyphotopicker.model;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContentResolverCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 相册数据查询处理
 * SQL GROUP BY 介绍 https://www.cnblogs.com/jingfengling/p/5962182.html
 * ContentResolver.query 参数详解 https://www.cnblogs.com/android-for-dh/p/4449524.html
 * ContentResolver.query方法的使用 https://blog.csdn.net/supluo/article/details/43954129
 */
public class AlbumModel {
    /**
     * 数据源
     */
    private static final Uri QUERY_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    private static final String ORDER_BY = MediaStore.Images.Media.DATE_TAKEN + " DESC";

    private static final String[] PROJECTION = {
            MediaStore.Files.FileColumns._ID,
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.SIZE};

    // === params for album ALL && showSingleMediaType: false ===
    private static final String SELECTION_ALL =
            "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                    + " OR "
                    + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?)"
                    + " AND " + MediaStore.MediaColumns.SIZE + ">0";
    private static final String[] SELECTION_ALL_ARGS = {
            String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE),
            String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO),
    };
    // ===========================================================


//    private WeakReference<Context> mContext;


    public interface AlbumCallbacks {
        void onAlbumSuc(List<String> allAlbums);
    }


    private AlbumCallbacks mCallbacks;


    public static void getAlbums(FragmentActivity fragmentActivity, AlbumCallbacks albumCallbacks) {
//        mContext = new WeakReference<Context>(fragmentActivity);
        List<String> allAlbums = new ArrayList<>();

        //TODO ContentResolverCompat 和 ContentResolver   https://developer.android.com/reference/android/support/v4/content/ContentResolverCompat
        // TODO ContentResolverCompat https://blog.csdn.net/Mr_Gintoki/article/details/50601094
        ContentResolver cr = fragmentActivity.getContentResolver();
        Cursor cursor = null;
//        cursor = cr.query(QUERY_URI, PROJECTION, SELECTION_ALL, SELECTION_ALL_ARGS, ORDER_BY);
        cursor = cr.query(QUERY_URI, PROJECTION, null, null, ORDER_BY);
        if (cursor == null) {
            //TODO null
            albumCallbacks.onAlbumSuc(allAlbums);
        }
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
            allAlbums.add("" + path);
        }
        cursor.close();
        if (albumCallbacks!=null){
            albumCallbacks.onAlbumSuc(allAlbums);
        }

    }

}
