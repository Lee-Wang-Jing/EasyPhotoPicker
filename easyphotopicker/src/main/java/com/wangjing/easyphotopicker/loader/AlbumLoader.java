package com.wangjing.easyphotopicker.loader;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;

public class AlbumLoader extends CursorLoader {

    public AlbumLoader(@NonNull Context context) {
        super(context);
    }

    public AlbumLoader(@NonNull Context context, @NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
    }

    public static CursorLoader newInstance(Context context){
        String selection;
        String[] selectionArgs;
        if (SelectionS)
    }
}
