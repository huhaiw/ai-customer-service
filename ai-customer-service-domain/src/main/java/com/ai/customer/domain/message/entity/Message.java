package com.ai.customer.domain.message.entity;

import com.ai.customer.domain.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 消息实体
 * 管理会话中的消息
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_message")
public class Message extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 会话ID
     */
    private Long sessionId;

    /**
     * 消息类型
     * USER-用户消息，AI-AI回复，SYSTEM-系统消息，AGENT-客服消息
     */
    private String messageType;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息状态
     * 0-发送中，1-已发送，2-已读，3-发送失败
     */
    private Integer status;

    /**
     * 发送者ID
     */
    private Long senderId;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;

    /**
     * 知识来源（AI消息关联的知识ID）
     */
    private Long knowledgeId;

    /**
     * 置信度（AI消息的置信度）
     */
    private Double confidence;

    /**
     * 消息扩展信息（JSON格式）
     */
    private String extra;
}