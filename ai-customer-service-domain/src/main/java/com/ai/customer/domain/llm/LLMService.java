package com.ai.customer.domain.llm;

/**
 * LLM服务接口
 * 尚书省 - 技术研发
 */
public interface LLMService {
    
    /**
     * 生成回复
     * @param context 上下文
     * @param userMessage 用户消息
     * @return AI回复
     */
    String generateResponse(String context, String userMessage);
    
    /**
     * 情绪分析
     * @param message 消息内容
     * @return 情绪等级 1-4
     */
    int analyzeEmotion(String message);
    
    /**
     * 问题分类
     * @param message 消息内容
     * @return 分类名称
     */
    String classifyQuestion(String message);
}
