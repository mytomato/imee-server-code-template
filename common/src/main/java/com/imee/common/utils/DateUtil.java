package com.imee.common.utils;

import java.sql.Timestamp;

/**
 * @ClassName DateUtil
 * @Description TODO
 * @Author 张炜
 * @Date 2025/9/24 18:23
 **/
public class DateUtil {

    /**
     * 获取当前时间
     * @return 当前时间
     */
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }
}
