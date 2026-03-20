package com.ai.customer.infrastructure.repository;

import com.ai.customer.domain.knowledge.entity.Knowledge;
import com.ai.customer.domain.knowledge.repository.KnowledgeRepository;
import com.ai.customer.infrastructure.mapper.KnowledgeMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 知识仓储实现
 */
@Repository
@RequiredArgsConstructor
public class KnowledgeRepositoryImpl implements KnowledgeRepository {

    private final KnowledgeMapper knowledgeMapper;

    @Override
    public Knowledge save(Knowledge knowledge) {
        knowledgeMapper.insert(knowledge);
        return knowledge;
    }

    @Override
    public Optional<Knowledge> findById(Long id) {
        return Optional.ofNullable(knowledgeMapper.selectById(id));
    }

    @Override
    public List<Knowledge> findByCategoryId(Long categoryId) {
        return knowledgeMapper.selectList(
            new LambdaQueryWrapper<Knowledge>()
                .eq(Knowledge::getCategoryId, categoryId)
                .orderByDesc(Knowledge::getViewCount)
        );
    }

    @Override
    public List<Knowledge> findByKeywords(String keyword) {
        return knowledgeMapper.selectList(
            new LambdaQueryWrapper<Knowledge>()
                .like(Knowledge::getKeywords, keyword)
                .or()
                .like(Knowledge::getTitle, keyword)
                .or()
                .like(Knowledge::getContent, keyword)
                .eq(Knowledge::getStatus, 1)
                .orderByDesc(Knowledge::getUsefulCount)
        );
    }

    @Override
    public List<Knowledge> findByStatus(Integer status) {
        return knowledgeMapper.selectList(
            new LambdaQueryWrapper<Knowledge>()
                .eq(Knowledge::getStatus, status)
                .orderByDesc(Knowledge::getUpdatedAt)
        );
    }

    @Override
    public Optional<Knowledge> findByVectorId(String vectorId) {
        return Optional.ofNullable(knowledgeMapper.selectOne(
            new LambdaQueryWrapper<Knowledge>()
                .eq(Knowledge::getVectorId, vectorId)
        ));
    }

    @Override
    public Knowledge update(Knowledge knowledge) {
        knowledgeMapper.updateById(knowledge);
        return knowledge;
    }

    @Override
    public void deleteById(Long id) {
        knowledgeMapper.deleteById(id);
    }

    @Override
    public void incrementViewCount(Long id) {
        knowledgeMapper.update(null,
            new LambdaUpdateWrapper<Knowledge>()
                .eq(Knowledge::getId, id)
                .setSql("view_count = view_count + 1")
        );
    }

    @Override
    public void incrementUsefulCount(Long id) {
        knowledgeMapper.update(null,
            new LambdaUpdateWrapper<Knowledge>()
                .eq(Knowledge::getId, id)
                .setSql("useful_count = useful_count + 1")
        );
    }
}
