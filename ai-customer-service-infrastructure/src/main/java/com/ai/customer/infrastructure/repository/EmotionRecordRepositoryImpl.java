package com.ai.customer.infrastructure.repository;

import com.ai.customer.domain.emotion.entity.EmotionRecord;
import com.ai.customer.domain.emotion.repository.EmotionRecordRepository;
import com.ai.customer.infrastructure.mapper.EmotionRecordMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 情绪记录仓储实现
 */
@Repository
@RequiredArgsConstructor
public class EmotionRecordRepositoryImpl implements EmotionRecordRepository {

    private final EmotionRecordMapper emotionRecordMapper;

    @Override
    public EmotionRecord save(EmotionRecord emotionRecord) {
        emotionRecordMapper.insert(emotionRecord);
        return emotionRecord;
    }

    @Override
    public Optional<EmotionRecord> findById(Long id) {
        return Optional.ofNullable(emotionRecordMapper.selectById(id));
    }

    @Override
    public Optional<EmotionRecord> findByMessageId(Long messageId) {
        return Optional.ofNullable(emotionRecordMapper.selectOne(
            new LambdaQueryWrapper<EmotionRecord>()
                .eq(EmotionRecord::getMessageId, messageId)
        ));
    }

    @Override
    public List<EmotionRecord> findBySessionId(Long sessionId) {
        return emotionRecordMapper.selectList(
            new LambdaQueryWrapper<EmotionRecord>()
                .eq(EmotionRecord::getSessionId, sessionId)
                .orderByDesc(EmotionRecord::getCreatedAt)
        );
    }

    @Override
    public List<EmotionRecord> findByUserId(Long userId) {
        return emotionRecordMapper.selectList(
            new LambdaQueryWrapper<EmotionRecord>()
                .eq(EmotionRecord::getUserId, userId)
                .orderByDesc(EmotionRecord::getCreatedAt)
        );
    }

    @Override
    public List<EmotionRecord> findNeedAgentRecords(Long sessionId) {
        return emotionRecordMapper.selectList(
            new LambdaQueryWrapper<EmotionRecord>()
                .eq(EmotionRecord::getSessionId, sessionId)
                .eq(EmotionRecord::getNeedAgent, true)
                .orderByDesc(EmotionRecord::getIntensity)
        );
    }

    @Override
    public EmotionRecord update(EmotionRecord emotionRecord) {
        emotionRecordMapper.updateById(emotionRecord);
        return emotionRecord;
    }

    @Override
    public void deleteById(Long id) {
        emotionRecordMapper.deleteById(id);
    }
}
