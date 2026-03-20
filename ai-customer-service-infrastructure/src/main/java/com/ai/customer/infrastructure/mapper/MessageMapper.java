package com.ai.customer.infrastructure.mapper;

import com.ai.customer.domain.message.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息 Mapper 接口
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}