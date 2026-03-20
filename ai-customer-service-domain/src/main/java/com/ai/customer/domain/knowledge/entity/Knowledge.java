package com.ai.customer.domain.knowledge.entity;

import com.ai.customer.domain.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 知识实体
 * 知识库中的知识条目
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_knowledge")
public class Knowledge extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 知识标题
     */
    private String title;

    /**
     * 知识内容
     */
    private String content;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 知识类型
     * FAQ, DOCUMENT, POLICY等
     */
    private String type;

    /**
     * 关键词（逗号分隔）
     */
    private String keywords;

    /**
     * 知识状态
     * 0-草稿，1-已发布，2-已下架
     */
    private Integer status;

    /**
     * 访问次数
     */
    private Integer viewCount;

    /**
     * 有用次数
     */
    private Integer usefulCount;

    /**
     * 向量ID（用于向量检索）
     */
    private String vectorId;

    /**
     * 来源
     */
    private String source;

    /**
     * 扩展信息（JSON格式）
     */
    private String extra;
}