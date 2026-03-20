package com.ai.customer.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 会话视图对象
 */
@Data
@Builder
@Schema(description = "会话信息")
public class SessionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "会话ID")
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "会话标题")
    private String title;

    @Schema(description = "会话状态：0-进行中，1-已关闭，2-已过期")
    private Integer status;

    @Schema(description = "渠道来源")
    private String channel;

    @Schema(description = "最后消息时间")
    private LocalDateTime lastMessageTime;

    @Schema(description = "会话开始时间")
    private LocalDateTime startTime;

    @Schema(description = "会话结束时间")
    private LocalDateTime endTime;

    @Schema(description = "客服ID")
    private Long agentId;

    @Schema(description = "消息数量")
    private Long messageCount;
}
