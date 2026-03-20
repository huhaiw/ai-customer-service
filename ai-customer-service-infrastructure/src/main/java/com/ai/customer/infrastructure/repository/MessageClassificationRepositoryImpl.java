package com.ai.customer.infrastructure.repository;

import com.ai.customer.domain.classification.entity.MessageClassification;
import com.ai.customer.domain.classification.repository.MessageClassificationRepository;
import com.ai.customer.infrastructure.mapper.MessageClassificationMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 消息分类仓储实现
 */
@Repository
@RequiredArgsConstructor
public class MessageClassificationRepositoryImpl implements MessageClassificationRepository {

    private final MessageClassificationMapper messageClassificationMapper;

    @Override
    public MessageClassification save(MessageClassification messageClassification) {
        messageClassificationMapper.insert(messageClassification);
        return messageClassification;
    }

    @Override
    public MessageClassification findById(Long id) {
        return messageClassificationMapper.selectById(id);
    }

    @Override
    public List<MessageClassification> findByMessageId(Long messageId) {
        return messageClassificationMapper.selectList(
            new LambdaQueryWrapper<MessageClassification>()
                .eq(MessageClassification::getMessageId, messageId)
                .orderByDesc(MessageClassification::getConfidence)
        );
    }

    @Override
    public List<MessageClassification> findBySessionId(Long sessionId) {
        return messageClassificationMapper.selectList(
            new LambdaQueryWrapper<MessageClassification>()
                .eq(MessageClassification::getSessionId, sessionId)
                .orderByDesc(MessageClassification::getCreatedAt)
        );
    }

    @Override
    public MessageClassification findPrimaryByMessageId(Long messageId) {
        return messageClassificationMapper.selectOne(
            new LambdaQueryWrapper<MessageClassification>()
                .eq(MessageClassification::getMessageId, messageId)
                .eq(MessageClassification::getIsPrimary, true)
                .last("LIMIT 1")
        );
    }

    @Override
    public MessageClassification update(MessageClassification messageClassification) {
        messageClassificationMapper.updateById(messageClassification);
        return messageClassification;
    }

    @Override
    public void deleteById(Long id) {
        messageClassificationMapper.deleteById(id);
    }
}
