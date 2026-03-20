package com.ai.customer.infrastructure.mapper;

import com.ai.customer.domain.emotion.entity.EmotionRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 情绪记录 Mapper 接口
 */
@Mapper
public interface EmotionRecordMapper extends BaseMapper<EmotionRecord> {
}