package com.ai.customer.api.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用错误码枚举
 */
@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    // ==================== 系统级错误 ====================
    SUCCESS(200, "操作成功"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "无权限访问"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),
    CONFLICT(409, "资源冲突"),
    INTERNAL_ERROR(500, "系统内部错误"),
    SERVICE_UNAVAILABLE(503, "服务暂不可用"),

    // ==================== 参数校验错误 1000-1999 ====================
    PARAM_ERROR(1001, "参数错误"),
    PARAM_MISSING(1002, "缺少必要参数"),
    PARAM_FORMAT_ERROR(1003, "参数格式错误"),
    PARAM_RANGE_ERROR(1004, "参数范围错误"),

    // ==================== 会话域错误 2000-2999 ====================
    SESSION_NOT_FOUND(2001, "会话不存在"),
    SESSION_CLOSED(2002, "会话已关闭"),
    SESSION_EXPIRED(2003, "会话已过期"),
    SESSION_LIMIT_EXCEEDED(2004, "会话数量超过限制"),

    // ==================== 消息域错误 3000-3999 ====================
    MESSAGE_NOT_FOUND(3001, "消息不存在"),
    MESSAGE_SEND_FAILED(3002, "消息发送失败"),
    MESSAGE_CONTENT_EMPTY(3003, "消息内容为空"),
    MESSAGE_TYPE_ERROR(3004, "消息类型错误"),

    // ==================== 知识库域错误 4000-4999 ====================
    KNOWLEDGE_NOT_FOUND(4001, "知识不存在"),
    KNOWLEDGE_SEARCH_FAILED(4002, "知识检索失败"),
    KNOWLEDGE_ADD_FAILED(4003, "知识添加失败"),
    KNOWLEDGE_UPDATE_FAILED(4004, "知识更新失败"),
    KNOWLEDGE_DELETE_FAILED(4005, "知识删除失败"),

    // ==================== 情绪域错误 5000-5999 ====================
    EMOTION_DETECT_FAILED(5001, "情绪检测失败"),
    EMOTION_TYPE_INVALID(5002, "情绪类型无效"),

    // ==================== 分类域错误 6000-6999 ====================
    CLASSIFICATION_FAILED(6001, "问题分类失败"),
    CATEGORY_NOT_FOUND(6002, "分类不存在");

    private final Integer code;
    private final String message;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}