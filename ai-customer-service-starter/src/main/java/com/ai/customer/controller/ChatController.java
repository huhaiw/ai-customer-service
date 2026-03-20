package com.ai.customer.controller;

import com.ai.customer.api.common.Result;
import com.ai.customer.api.dto.request.ChatRequest;
import com.ai.customer.api.dto.response.ChatResponse;
import com.ai.customer.application.service.ChatApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 聊天控制器
 * 提供AI客服聊天接口
 */
@Slf4j
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@Tag(name = "聊天接口", description = "AI客服聊天相关接口")
public class ChatController {

    private final ChatApplicationService chatApplicationService;

    @PostMapping
    @Operation(summary = "发送消息", description = "用户发送消息给AI客服，获取AI回复")
    public Result<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        log.info("收到聊天请求: userId={}, sessionId={}, content={}",
                request.getUserId(), request.getSessionId(),
                request.getContent().length() > 50 ? request.getContent().substring(0, 50) + "..." : request.getContent());

        ChatResponse response = chatApplicationService.chat(request);

        log.info("聊天响应: sessionId={}, messageId={}", response.getSessionId(), response.getMessageId());
        return Result.success(response);
    }

    @GetMapping("/health")
    @Operation(summary = "健康检查", description = "检查服务是否正常运行")
    public Result<String> health() {
        return Result.success("OK", "服务正常");
    }

    @GetMapping("/session/{sessionId}")
    @Operation(summary = "获取会话信息", description = "根据会话ID获取会话详情")
    public Result<ChatResponse> getSession(
            @Parameter(description = "会话ID") @PathVariable Long sessionId) {
        // TODO: 实现获取会话详情
        return Result.success(null);
    }
}
