package com.imee.common.result;

/**
 * 封装API的错误码
 */
public interface IErrorCode {
    /**
     * 获取错误码
     * @return 错误码
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return 错误信息
     */
    String getMessage();
}