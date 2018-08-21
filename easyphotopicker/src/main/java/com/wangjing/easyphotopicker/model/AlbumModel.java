package com.wangjing.easyphotopicker.model;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
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
            MediaStore.MediaColumns._ID,
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.DATA,
    };
    private static final String SELECTION_IMAGE =
            "(" +
                    MediaStore.Images.Media.MIME_TYPE + "=?"
                    + " OR " +
                    MediaStore.Images.Media.MIME_TYPE + "=?)"
                    + " AND "
                    + MediaStore.MediaColumns.SIZE + ">0";


    // === params for album ALL && showSingleMediaType: false ===
    private static final String SELECTION_ALL2 =

            "(" + MediaStore.MediaColumns.MIME_TYPE + "=?"
                    + " OR "
                    + MediaStore.MediaColumns.MIME_TYPE + "=?)"
                    + " AND " + MediaStore.MediaColumns.SIZE + ">0";

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


    /**
     * 获取查询图片的Selection
     *
     * @param isShowCommenImage 是否查询普通图片
     * @param isShowGif         是否查询Gif图片
     * @return Selection String
     */
    private String getSelection_Image(boolean isShowCommenImage, boolean isShowGif) {
        int type = 0;
        if (isShowCommenImage && !isShowGif) {//只查询普通图片(jpg、png)
            type = 1;
        } else if (!isShowCommenImage && isShowGif) {//只查询Gif
            type = 2;
        } else if (isShowCommenImage && isShowGif) {//查询普通图片和Gif
            type = 3;
        } else {//都不查询
            type = 4;
        }
        String selectiom_image = null;
        switch (type) {
            case 1:
                selectiom_image = "(" +
                        MediaStore.MediaColumns.MIME_TYPE + "=?"
                        + " OR " +
                        MediaStore.MediaColumns.MIME_TYPE + "=?)"
                        + " AND "
                        + MediaStore.MediaColumns.SIZE + ">0";
                break;
            case 2:
                selectiom_image = "(" +
                        MediaStore.MediaColumns.MIME_TYPE + "=?)"
                        + " AND "
                        + MediaStore.MediaColumns.SIZE + ">0";
                break;
            case 3:
                selectiom_image = "(" +
                        MediaStore.MediaColumns.MIME_TYPE + "=?"
                        + " OR " +
                        MediaStore.MediaColumns.MIME_TYPE + "=?"
                        + " OR " +
                        MediaStore.MediaColumns.MIME_TYPE + "=?)"
                        + " AND "
                        + MediaStore.MediaColumns.SIZE + ">0";
                break;
            case 4:
                selectiom_image = null;
                break;
            default:
                selectiom_image = null;
                break;
        }

        return selectiom_image;
    }


    public static void getAlbums(FragmentActivity fragmentActivity, AlbumCallbacks albumCallbacks) {
//        mContext = new WeakReference<Context>(fragmentActivity);
        List<String> allAlbums = new ArrayList<>();

        // TODO https://blog.csdn.net/zhanwei0102/article/details/53009506?locationNum=4&fps=1
        // TODO 理解PROJECTION  SELECTION  SELECTION_ARGS
        ContentResolver cr = fragmentActivity.getContentResolver();
        Cursor cursor = null;
//        cursor = cr.query(QUERY_URI, PROJECTION, SELECTION_ALL, SELECTION_ALL_ARGS, ORDER_BY);
        cursor = cr.query(QUERY_URI, PROJECTION, null, null, ORDER_BY);
        if (cursor == null) {
            //TODO null
            albumCallbacks.onAlbumSuc(allAlbums);
        }
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            String mime_type = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE));
            String display_name = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME));
            Log.e("mime_type", "" + mime_type);
            Log.e("display_name", "" + display_name);
            allAlbums.add("" + path);
        }
        cursor.close();
        if (albumCallbacks != null) {
            albumCallbacks.onAlbumSuc(allAlbums);
        }

    }

}
