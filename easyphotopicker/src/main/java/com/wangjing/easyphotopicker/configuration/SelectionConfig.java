package com.wangjing.easyphotopicker.configuration;

import com.wangjing.easyphotopicker.MimeType;

import java.util.Set;

/**
 * 搜索查询配置
 */
public class SelectionConfig {
    private static final SelectionConfig INSTANCE = new SelectionConfig();

    public Set<MimeType> mimeTypeSet;
    /**
     * 是否只显示单一的MediaType，比如Image or Video
     */
    public boolean showSingleMediaType;

    public static SelectionConfig getInstance() {
        return INSTANCE;
    }

    /**
     * 是否只显示图片
     *
     * @return onlyShowImages
     */
    public boolean onlyShowImages() {
        return showSingleMediaType && MimeType.ofImage().containsAll(mimeTypeSet);
    }

    /**
     * 是否只显示视频
     *
     * @return onlyShowVideos
     */
    public boolean onlyShowVideos() {
        return showSingleMediaType && MimeType.ofVideo().containsAll(mimeTypeSet);
    }

    /**
     * 重置参数
     */
    private void reset() {
        mimeTypeSet = null;
        showSingleMediaType = false;
    }

}
