package com.ai.customer.domain.emotion.entity;

import com.ai.customer.domain.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 情绪记录实体
 * 记录用户消息的情绪分析结果
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_emotion_record")
public class EmotionRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 会话ID
     */
    private Long sessionId;

    /**
     * 消息ID
     */
    private Long messageId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 情绪类型
     * POSITIVE-积极，NEGATIVE-消极，NEUTRAL-中性，ANGRY-愤怒，ANXIOUS-焦虑
     */
    private String emotionType;

    /**
     * 情绪得分（0-100）
     */
    private Integer emotionScore;

    /**
     * 情绪强度（0-100）
     */
    private Integer intensity;

    /**
     * 是否需要人工介入
     */
    private Boolean needAgent;

    /**
     * 分析结果详情（JSON格式）
     */
    private String analysisDetail;

    /**
     * 扩展信息
     */
    private String extra;
}