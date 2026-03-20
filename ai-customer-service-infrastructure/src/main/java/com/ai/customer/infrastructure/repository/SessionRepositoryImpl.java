package com.ai.customer.infrastructure.repository;

import com.ai.customer.domain.session.entity.Session;
import com.ai.customer.domain.session.repository.SessionRepository;
import com.ai.customer.infrastructure.mapper.SessionMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 会话仓储实现
 */
@Repository
@RequiredArgsConstructor
public class SessionRepositoryImpl implements SessionRepository {

    private final SessionMapper sessionMapper;

    @Override
    public Session save(Session session) {
        sessionMapper.insert(session);
        return session;
    }

    @Override
    public Session update(Session session) {
        sessionMapper.updateById(session);
        return session;
    }

    @Override
    public Optional<Session> findById(Long id) {
        return Optional.ofNullable(sessionMapper.selectById(id));
    }

    @Override
    public List<Session> findByUserId(Long userId) {
        return sessionMapper.selectList(
            new LambdaQueryWrapper<Session>()
                .eq(Session::getUserId, userId)
                .orderByDesc(Session::getUpdatedAt)
        );
    }

    @Override
    public List<Session> findByUserIdAndStatus(Long userId, Integer status) {
        return sessionMapper.selectList(
            new LambdaQueryWrapper<Session>()
                .eq(Session::getUserId, userId)
                .eq(Session::getStatus, status)
                .orderByDesc(Session::getUpdatedAt)
        );
    }

    @Override
    public Optional<Session> findActiveSessionByUserId(Long userId) {
        return Optional.ofNullable(sessionMapper.selectOne(
            new LambdaQueryWrapper<Session>()
                .eq(Session::getUserId, userId)
                .eq(Session::getStatus, 0)
                .orderByDesc(Session::getLastMessageTime)
                .last("LIMIT 1")
        ));
    }

    @Override
    public void deleteById(Long id) {
        sessionMapper.deleteById(id);
    }
}