package com.ai.customer.domain.knowledge.repository;

import com.ai.customer.domain.knowledge.entity.Knowledge;
import java.util.List;
import java.util.Optional;

/**
 * 知识仓储接口
 */
public interface KnowledgeRepository {

    /**
     * 保存知识
     */
    Knowledge save(Knowledge knowledge);

    /**
     * 根据ID查询知识
     */
    Optional<Knowledge> findById(Long id);

    /**
     * 根据分类ID查询知识列表
     */
    List<Knowledge> findByCategoryId(Long categoryId);

    /**
     * 根据关键词模糊查询
     */
    List<Knowledge> findByKeywords(String keyword);

    /**
     * 根据状态查询知识列表
     */
    List<Knowledge> findByStatus(Integer status);

    /**
     * 根据向量ID查询知识
     */
    Optional<Knowledge> findByVectorId(String vectorId);

    /**
     * 更新知识
     */
    Knowledge update(Knowledge knowledge);

    /**
     * 删除知识
     */
    void deleteById(Long id);

    /**
     * 增加访问次数
     */
    void incrementViewCount(Long id);

    /**
     * 增加有用次数
     */
    void incrementUsefulCount(Long id);
}
