package com.ai.customer.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * 聊天请求DTO
 */
@Data
@Schema(description = "聊天请求")
public class ChatRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "会话ID，首次对话可为空")
    private Long sessionId;

    @NotBlank(message = "消息内容不能为空")
    @Schema(description = "用户消息内容", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    @Schema(description = "渠道来源：WEB, APP, WECHAT")
    private String channel;

    @Schema(description = "扩展信息")
    private String extra;
}
