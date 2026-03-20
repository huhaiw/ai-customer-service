package com.ai.customer.domain.emotion.repository;

import com.ai.customer.domain.emotion.entity.EmotionRecord;
import java.util.List;
import java.util.Optional;

/**
 * 情绪记录仓储接口
 */
public interface EmotionRecordRepository {

    /**
     * 保存情绪记录
     */
    EmotionRecord save(EmotionRecord emotionRecord);

    /**
     * 根据ID查询情绪记录
     */
    Optional<EmotionRecord> findById(Long id);

    /**
     * 根据消息ID查询情绪记录
     */
    Optional<EmotionRecord> findByMessageId(Long messageId);

    /**
     * 根据会话ID查询情绪记录列表
     */
    List<EmotionRecord> findBySessionId(Long sessionId);

    /**
     * 根据用户ID查询情绪记录列表
     */
    List<EmotionRecord> findByUserId(Long userId);

    /**
     * 查询需要人工介入的情绪记录
     */
    List<EmotionRecord> findNeedAgentRecords(Long sessionId);

    /**
     * 更新情绪记录
     */
    EmotionRecord update(EmotionRecord emotionRecord);

    /**
     * 删除情绪记录
     */
    void deleteById(Long id);
}
