package com.ai.customer.domain.message.repository;

import com.ai.customer.domain.message.entity.Message;
import java.util.List;

/**
 * 消息仓储接口
 */
public interface MessageRepository {

    /**
     * 保存消息
     */
    Message save(Message message);

    /**
     * 根据ID查询消息
     */
    Message findById(Long id);

    /**
     * 根据会话ID查询消息列表
     */
    List<Message> findBySessionId(Long sessionId);

    /**
     * 根据会话ID查询消息列表（分页）
     */
    List<Message> findBySessionId(Long sessionId, int page, int size);

    /**
     * 查询会话的最后一条消息
     */
    Message findLastMessageBySessionId(Long sessionId);

    /**
     * 更新消息
     */
    Message update(Message message);

    /**
     * 删除消息
     */
    void deleteById(Long id);

    /**
     * 统计会话消息数量
     */
    long countBySessionId(Long sessionId);
}
