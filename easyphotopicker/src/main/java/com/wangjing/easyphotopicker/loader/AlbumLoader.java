package com.wangjing.easyphotopicker.loader;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;

import com.wangjing.easyphotopicker.configuration.SelectionConfig;
import com.wangjing.easyphotopicker.entity.Album;

public class AlbumLoader extends CursorLoader {
    private static final String COLUMN_COUNT = "count";

    /**
     * 按照日期排序
     */
    private static final String BUCKET_ORDER_BY = "datetaken DESC";
    /**
     * 数据源
     */
    private static final Uri QUERY_URI = MediaStore.Files.getContentUri("external");

    /**
     * 查询的ID，名称等等
     */
    private static final String[] PROJECTION = {
            MediaStore.Files.FileColumns._ID,
            "bucket_id",
            "bucket_display_name",
            MediaStore.MediaColumns.DATA,
            "COUNT(*) AS " + COLUMN_COUNT};

    private static final String[] COLUMNS = {
            MediaStore.Files.FileColumns._ID,
            "bucket_id",
            "bucket_display_name",
            MediaStore.MediaColumns.DATA,
            COLUMN_COUNT};


    // === params for showSingleMediaType: true ===
    private static final String SELECTION_FOR_SINGLE_MEDIA_TYPE =
            MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                    + " AND " + MediaStore.MediaColumns.SIZE + ">0"
                    + ") GROUP BY (bucket_id";

    // === params for showSingleMediaType: false ===
    private static final String SELECTION =
            "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                    + " OR "
                    + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?)"
                    + " AND " + MediaStore.MediaColumns.SIZE + ">0"
                    + ") GROUP BY (bucket_id";
    private static final String[] SELECTION_ARGS = {
            String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE),
            String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO),
    };

    public AlbumLoader(@NonNull Context context) {
        super(context);
    }

    /**
     *
     * @param context Context
     * @param selection 一种用于声明要返回哪些行的过滤器，采用 SQL WHERE 子句格式（WHERE 本身除外）。传递 null 时，将为指定的 URI 返回所有行
     * @param selectionArgs 您可以在 selection 中包含 ?s，它将按照在 selection 中显示的顺序替换为 selectionArgs 中的值。该值将绑定为字串符
     */
    public AlbumLoader(@NonNull Context context, @Nullable String selection, @Nullable String[] selectionArgs) {
        super(context, QUERY_URI, PROJECTION, selection, selectionArgs, BUCKET_ORDER_BY);
    }

    /**
     *
     * @param context Context
     * @param uri 用于检索内容的 URI
     * @param projection 要返回的列的列表。传递 null 时，将返回所有列，这样会导致效率低下
     * @param selection 一种用于声明要返回哪些行的过滤器，采用 SQL WHERE 子句格式（WHERE 本身除外）。传递 null 时，将为指定的 URI 返回所有行
     * @param selectionArgs 您可以在 selection 中包含 ?s，它将按照在 selection 中显示的顺序替换为 selectionArgs 中的值。该值将绑定为字串符
     * @param sortOrder 行的排序依据，采用 SQL ORDER BY 子句格式（ORDER BY 自身除外）。传递 null 时，将使用默认排序顺序（可能并未排序）
     */
    public AlbumLoader(@NonNull Context context, @NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
    }

    public static CursorLoader newInstance(Context context) {
        String selection;
        String[] selectionArgs;
        //只显示图片
        if (SelectionConfig.getInstance().onlyShowImages()) {
            selection = SELECTION_FOR_SINGLE_MEDIA_TYPE;
            selectionArgs = getSelectionArgsForSingleMediaType(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE);
        } else if (SelectionConfig.getInstance().onlyShowVideos()) {
            //只显示视频
            selection = SELECTION_FOR_SINGLE_MEDIA_TYPE;
            selectionArgs = getSelectionArgsForSingleMediaType(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO);
        } else {
            //默认都显示
            selection = SELECTION;
            selectionArgs = SELECTION_ARGS;
        }
        return new AlbumLoader(context, selection, selectionArgs);
    }


    private static String[] getSelectionArgsForSingleMediaType(int mediaType) {
        return new String[]{String.valueOf(mediaType)};
    }


    @Override
    public Cursor loadInBackground() {
        Cursor cursor = super.loadInBackground();
        MatrixCursor allAlbum = new MatrixCursor(COLUMNS);
        int totalCount = 0;
        String allAlbumCoverPath = "";
        if (cursor != null) {
            while (cursor.moveToNext()) {
                totalCount += cursor.getInt(cursor.getColumnIndex(COLUMN_COUNT));
            }
            if (cursor.moveToFirst()) {
                allAlbumCoverPath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            }
        }
        allAlbum.addRow(new String[]{Album.ALBUM_ID_ALL, Album.ALBUM_ID_ALL, Album.ALBUM_NAME_ALL, allAlbumCoverPath,
                String.valueOf(totalCount)});
        return new MergeCursor(new Cursor[]{allAlbum, cursor});
    }
}
