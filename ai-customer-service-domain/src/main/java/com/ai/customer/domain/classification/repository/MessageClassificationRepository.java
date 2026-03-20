package com.ai.customer.domain.classification.repository;

import com.ai.customer.domain.classification.entity.MessageClassification;
import java.util.List;

/**
 * 消息分类仓储接口
 */
public interface MessageClassificationRepository {

    /**
     * 保存消息分类
     */
    MessageClassification save(MessageClassification messageClassification);

    /**
     * 根据ID查询消息分类
     */
    MessageClassification findById(Long id);

    /**
     * 根据消息ID查询分类列表
     */
    List<MessageClassification> findByMessageId(Long messageId);

    /**
     * 根据会话ID查询分类列表
     */
    List<MessageClassification> findBySessionId(Long sessionId);

    /**
     * 查询消息的主要分类
     */
    MessageClassification findPrimaryByMessageId(Long messageId);

    /**
     * 更新消息分类
     */
    MessageClassification update(MessageClassification messageClassification);

    /**
     * 删除消息分类
     */
    void deleteById(Long id);
}
