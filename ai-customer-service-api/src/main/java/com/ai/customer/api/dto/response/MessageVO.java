package com.ai.customer.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 消息视图对象
 */
@Data
@Builder
@Schema(description = "消息信息")
public class MessageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "消息ID")
    private Long id;

    @Schema(description = "会话ID")
    private Long sessionId;

    @Schema(description = "消息类型：USER-用户消息，AI-AI回复，SYSTEM-系统消息，AGENT-客服消息")
    private String messageType;

    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "消息状态：0-发送中，1-已发送，2-已读，3-发送失败")
    private Integer status;

    @Schema(description = "发送者ID")
    private Long senderId;

    @Schema(description = "发送时间")
    private LocalDateTime sendTime;

    @Schema(description = "置信度")
    private Double confidence;

    @Schema(description = "情绪类型")
    private String emotionType;

    @Schema(description = "情绪等级")
    private Integer emotionLevel;
}
