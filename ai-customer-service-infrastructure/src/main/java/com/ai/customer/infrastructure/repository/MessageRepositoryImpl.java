package com.ai.customer.infrastructure.repository;

import com.ai.customer.domain.message.entity.Message;
import com.ai.customer.domain.message.repository.MessageRepository;
import com.ai.customer.infrastructure.mapper.MessageMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 消息仓储实现
 */
@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

    private final MessageMapper messageMapper;

    @Override
    public Message save(Message message) {
        messageMapper.insert(message);
        return message;
    }

    @Override
    public Message findById(Long id) {
        return messageMapper.selectById(id);
    }

    @Override
    public List<Message> findBySessionId(Long sessionId) {
        return messageMapper.selectList(
            new LambdaQueryWrapper<Message>()
                .eq(Message::getSessionId, sessionId)
                .orderByAsc(Message::getSendTime)
        );
    }

    @Override
    public List<Message> findBySessionId(Long sessionId, int page, int size) {
        return messageMapper.selectList(
            new LambdaQueryWrapper<Message>()
                .eq(Message::getSessionId, sessionId)
                .orderByDesc(Message::getSendTime)
                .last("LIMIT " + size + " OFFSET " + (page - 1) * size)
        );
    }

    @Override
    public Message findLastMessageBySessionId(Long sessionId) {
        return messageMapper.selectOne(
            new LambdaQueryWrapper<Message>()
                .eq(Message::getSessionId, sessionId)
                .orderByDesc(Message::getSendTime)
                .last("LIMIT 1")
        );
    }

    @Override
    public Message update(Message message) {
        messageMapper.updateById(message);
        return message;
    }

    @Override
    public void deleteById(Long id) {
        messageMapper.deleteById(id);
    }

    @Override
    public long countBySessionId(Long sessionId) {
        return messageMapper.selectCount(
            new LambdaQueryWrapper<Message>()
                .eq(Message::getSessionId, sessionId)
        );
    }
}
