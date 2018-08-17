package com.wangjing.easyphotopicker.configuration;

/**
 * 搜索查询配置
 */
public class SelectionConfig {
    private static final SelectionConfig INSTANCE = new SelectionConfig();




    public static SelectionConfig getInstance() {
        return INSTANCE;
    }

    public boolean onlyShowImages(){

    }

}
