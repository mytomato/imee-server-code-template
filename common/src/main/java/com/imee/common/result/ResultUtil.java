package com.imee.common.result;

/**
 * 结果处理工具类
 */
public class ResultUtil {
    
    /**
     * 返回成功结果
     * @param data 数据
     * @param <T> 数据类型
     * @return 结果
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 返回失败结果
     * @param message 错误信息
     * @param <T> 数据类型
     * @return 结果
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 返回自定义结果
     * @param code 状态码
     * @param message 信息
     * @param data 数据
     * @param <T> 数据类型
     * @return 结果
     */
    public static <T> Result<T> custom(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }
}