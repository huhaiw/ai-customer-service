package com.ai.customer.api.common;

import java.io.Serializable;

/**
 * 错误码接口
 */
public interface ErrorCode extends Serializable {

    /**
     * 获取错误码
     */
    Integer getCode();

    /**
     * 获取错误消息
     */
    String getMessage();
}