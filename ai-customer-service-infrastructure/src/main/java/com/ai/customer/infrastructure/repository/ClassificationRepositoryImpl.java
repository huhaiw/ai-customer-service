package com.ai.customer.infrastructure.repository;

import com.ai.customer.domain.classification.entity.Classification;
import com.ai.customer.domain.classification.repository.ClassificationRepository;
import com.ai.customer.infrastructure.mapper.ClassificationMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 分类仓储实现
 */
@Repository
@RequiredArgsConstructor
public class ClassificationRepositoryImpl implements ClassificationRepository {

    private final ClassificationMapper classificationMapper;

    @Override
    public Classification save(Classification classification) {
        classificationMapper.insert(classification);
        return classification;
    }

    @Override
    public Optional<Classification> findById(Long id) {
        return Optional.ofNullable(classificationMapper.selectById(id));
    }

    @Override
    public Optional<Classification> findByCode(String code) {
        return Optional.ofNullable(classificationMapper.selectOne(
            new LambdaQueryWrapper<Classification>()
                .eq(Classification::getCode, code)
        ));
    }

    @Override
    public List<Classification> findAllEnabled() {
        return classificationMapper.selectList(
            new LambdaQueryWrapper<Classification>()
                .eq(Classification::getStatus, 1)
                .orderByAsc(Classification::getSort)
        );
    }

    @Override
    public List<Classification> findByParentId(Long parentId) {
        return classificationMapper.selectList(
            new LambdaQueryWrapper<Classification>()
                .eq(Classification::getParentId, parentId)
                .eq(Classification::getStatus, 1)
                .orderByAsc(Classification::getSort)
        );
    }

    @Override
    public List<Classification> findTopCategories() {
        return classificationMapper.selectList(
            new LambdaQueryWrapper<Classification>()
                .eq(Classification::getParentId, 0)
                .eq(Classification::getStatus, 1)
                .orderByAsc(Classification::getSort)
        );
    }

    @Override
    public Classification update(Classification classification) {
        classificationMapper.updateById(classification);
        return classification;
    }

    @Override
    public void deleteById(Long id) {
        classificationMapper.deleteById(id);
    }
}
