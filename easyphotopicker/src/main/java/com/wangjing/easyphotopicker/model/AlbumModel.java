package com.wangjing.easyphotopicker.model;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;

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
    private static final String PROJECTION = MediaStore.Files.FileColumns.MEDIA_TYPE;

    private String selection = MediaStore.Images.Media.MIME_TYPE + "=? or "
            + MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?";

    // === params for showSingleMediaType: false ===
    private static final String SELECTION =
            "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                    + " OR "
                    + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?)"
                    + " AND " + MediaStore.MediaColumns.SIZE + ">0"
                    + ") GROUP BY (bucket_id";

//    private WeakReference<Context> mContext;


    public interface AlbumCallbacks {
        void onAlbumSuc();
    }


    private AlbumCallbacks mCallbacks;


    public void getAlbums(FragmentActivity fragmentActivity, AlbumCallbacks albumCallbacks) {
//        mContext = new WeakReference<Context>(fragmentActivity);

        //TODO ContentResolverCompat 和 ContentResolver   https://developer.android.com/reference/android/support/v4/content/ContentResolverCompat
        ContentResolver cr = fragmentActivity.getContentResolver();
        Cursor cursor = null;

//        cursor = cr.query(QUERY_URI, PROJECTION, )
    }

}
