package com.ai.customer.domain.session.entity;

import com.ai.customer.domain.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 会话实体
 * 管理用户与AI的对话会话
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_session")
public class Session extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 会话标题
     */
    private String title;

    /**
     * 会话状态
     * 0-进行中，1-已关闭，2-已过期
     */
    private Integer status;

    /**
     * 渠道来源
     * WEB, APP, WECHAT等
     */
    private String channel;

    /**
     * 最后消息时间
     */
    private LocalDateTime lastMessageTime;

    /**
     * 会话开始时间
     */
    private LocalDateTime startTime;

    /**
     * 会话结束时间
     */
    private LocalDateTime endTime;

    /**
     * 客服ID（人工介入时）
     */
    private Long agentId;

    /**
     * 会话扩展信息（JSON格式）
     */
    private String extra;
}