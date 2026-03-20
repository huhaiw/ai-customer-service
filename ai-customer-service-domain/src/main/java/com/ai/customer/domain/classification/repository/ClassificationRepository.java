package com.ai.customer.domain.classification.repository;

import com.ai.customer.domain.classification.entity.Classification;
import java.util.List;
import java.util.Optional;

/**
 * 分类仓储接口
 */
public interface ClassificationRepository {

    /**
     * 保存分类
     */
    Classification save(Classification classification);

    /**
     * 根据ID查询分类
     */
    Optional<Classification> findById(Long id);

    /**
     * 根据编码查询分类
     */
    Optional<Classification> findByCode(String code);

    /**
     * 查询所有启用的分类
     */
    List<Classification> findAllEnabled();

    /**
     * 根据父ID查询子分类
     */
    List<Classification> findByParentId(Long parentId);

    /**
     * 查询顶级分类
     */
    List<Classification> findTopCategories();

    /**
     * 更新分类
     */
    Classification update(Classification classification);

    /**
     * 删除分类
     */
    void deleteById(Long id);
}
