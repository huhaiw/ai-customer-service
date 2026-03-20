package com.ai.customer.domain.vector;

import java.util.List;

/**
 * 向量检索服务接口
 * 用于RAG知识检索
 */
public interface VectorSearchService {
    
    /**
     * 检索相关知识
     * @param query 查询文本
     * @param topK 返回数量
     * @return 知识列表
     */
    List<String> search(String query, int topK);
    
    /**
     * 添加知识到向量库
     * @param knowledge 知识内容
     */
    void addKnowledge(String knowledge);
    
    /**
     * 批量添加知识
     * @param knowledgeList 知识列表
     */
    void batchAddKnowledge(List<String> knowledgeList);
}
