package com.ai.customer.domain.classification.entity;

import com.ai.customer.domain.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分类实体
 * 问题分类信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_classification")
public class Classification extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 分类编码
     */
    private String code;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父分类ID
     */
    private Long parentId;

    /**
     * 分类层级
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 状态
     * 0-禁用，1-启用
     */
    private Integer status;

    /**
     * 扩展信息
     */
    private String extra;
}