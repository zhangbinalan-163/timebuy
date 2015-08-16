package com.alan.app.timebuy.entity;

/**
 * 客户端类型枚举
 * Created by zhangbinalan on 15/8/16.
 */
public enum ClientType {
    IPHONE_APP(10),
    ANDROID_APP(20);

    private int id;

    ClientType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ClientType getInstance(int id) {
        if (id == 10) {
            return IPHONE_APP;
        }
        if (id == 20) {
            return ANDROID_APP;
        }
        return null;
    }
}
