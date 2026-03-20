package com.ai.customer.infrastructure.mapper;

import com.ai.customer.domain.session.entity.Session;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会话 Mapper 接口
 */
@Mapper
public interface SessionMapper extends BaseMapper<Session> {
}