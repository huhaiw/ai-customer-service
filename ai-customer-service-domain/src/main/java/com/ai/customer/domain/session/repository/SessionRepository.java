package com.ai.customer.domain.session.repository;

import com.ai.customer.domain.session.entity.Session;
import java.util.List;
import java.util.Optional;

/**
 * 会话仓储接口
 */
public interface SessionRepository {

    /**
     * 保存会话
     */
    Session save(Session session);

    /**
     * 根据ID查询会话
     */
    Optional<Session> findById(Long id);

    /**
     * 根据用户ID查询会话列表
     */
    List<Session> findByUserId(Long userId);

    /**
     * 根据用户ID和状态查询会话
     */
    List<Session> findByUserIdAndStatus(Long userId, Integer status);

    /**
     * 查询用户进行中的会话
     */
    Optional<Session> findActiveSessionByUserId(Long userId);

    /**
     * 更新会话
     */
    Session update(Session session);

    /**
     * 删除会话
     */
    void deleteById(Long id);
}