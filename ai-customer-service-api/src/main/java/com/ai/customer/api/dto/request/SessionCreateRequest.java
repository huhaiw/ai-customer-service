package com.ai.customer.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * 创建会话请求DTO
 */
@Data
@Schema(description = "创建会话请求")
public class SessionCreateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    @Schema(description = "会话标题")
    private String title;

    @Schema(description = "渠道来源：WEB, APP, WECHAT")
    private String channel;

    @Schema(description = "扩展信息")
    private String extra;
}
