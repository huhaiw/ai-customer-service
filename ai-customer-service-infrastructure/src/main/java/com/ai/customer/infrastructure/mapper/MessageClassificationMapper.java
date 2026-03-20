package com.ai.customer.infrastructure.mapper;

import com.ai.customer.domain.classification.entity.MessageClassification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息分类 Mapper 接口
 */
@Mapper
public interface MessageClassificationMapper extends BaseMapper<MessageClassification> {
}
