package com.ai.customer.infrastructure.mapper;

import com.ai.customer.domain.classification.entity.Classification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类 Mapper 接口
 */
@Mapper
public interface ClassificationMapper extends BaseMapper<Classification> {
}