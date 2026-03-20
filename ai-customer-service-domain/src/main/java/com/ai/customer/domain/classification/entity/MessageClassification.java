package com.ai.customer.domain.classification.entity;

import com.ai.customer.domain.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息分类记录
 * 记录消息的分类结果
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_message_classification")
public class MessageClassification extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    private Long messageId;

    /**
     * 会话ID
     */
    private Long sessionId;

    /**
     * 分类ID
     */
    private Long classificationId;

    /**
     * 分类置信度（0-100）
     */
    private Integer confidence;

    /**
     * 是否主要分类
     */
    private Boolean isPrimary;

    /**
     * 分类详情（JSON格式）
     */
    private String detail;
}