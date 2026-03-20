package com.ai.customer.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 聊天响应DTO
 */
@Data
@Builder
@Schema(description = "聊天响应")
public class ChatResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "会话ID")
    private Long sessionId;

    @Schema(description = "消息ID")
    private Long messageId;

    @Schema(description = "AI回复内容")
    private String content;

    @Schema(description = "消息类型")
    private String messageType;

    @Schema(description = "置信度")
    private Double confidence;

    @Schema(description = "情绪等级（1-4）")
    private Integer emotionLevel;

    @Schema(description = "问题分类")
    private String classification;

    @Schema(description = "是否需要人工介入")
    private Boolean needAgent;

    @Schema(description = "知识来源")
    private String knowledgeSource;

    @Schema(description = "发送时间")
    private LocalDateTime sendTime;
}
