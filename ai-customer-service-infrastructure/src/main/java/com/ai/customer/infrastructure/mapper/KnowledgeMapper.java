package com.ai.customer.infrastructure.mapper;

import com.ai.customer.domain.knowledge.entity.Knowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 知识 Mapper 接口
 */
@Mapper
public interface KnowledgeMapper extends BaseMapper<Knowledge> {
}