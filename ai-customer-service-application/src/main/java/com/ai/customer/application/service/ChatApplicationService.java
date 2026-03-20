package com.ai.customer.application.service;

import com.ai.customer.api.dto.request.ChatRequest;
import com.ai.customer.api.dto.response.ChatResponse;
import com.ai.customer.domain.classification.entity.Classification;
import com.ai.customer.domain.classification.entity.MessageClassification;
import com.ai.customer.domain.classification.repository.ClassificationRepository;
import com.ai.customer.domain.classification.repository.MessageClassificationRepository;
import com.ai.customer.domain.emotion.entity.EmotionRecord;
import com.ai.customer.domain.emotion.repository.EmotionRecordRepository;
import com.ai.customer.domain.knowledge.entity.Knowledge;
import com.ai.customer.domain.knowledge.repository.KnowledgeRepository;
import com.ai.customer.domain.message.entity.Message;
import com.ai.customer.domain.message.repository.MessageRepository;
import com.ai.customer.domain.session.entity.Session;
import com.ai.customer.domain.session.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 聊天应用服务
 * 负责编排会话、消息、情绪分析、分类等业务流程
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatApplicationService {

    private final SessionRepository sessionRepository;
    private final MessageRepository messageRepository;
    private final KnowledgeRepository knowledgeRepository;
    private final EmotionRecordRepository emotionRecordRepository;
    private final ClassificationRepository classificationRepository;
    private final MessageClassificationRepository messageClassificationRepository;

    /**
     * 处理聊天请求
     */
    @Transactional
    public ChatResponse chat(ChatRequest request) {
        // 1. 获取或创建会话
        Session session = getOrCreateSession(request);

        // 2. 保存用户消息
        Message userMessage = saveUserMessage(session, request);

        // 3. 情绪分析（简化版）
        EmotionRecord emotionRecord = analyzeEmotion(userMessage, session);

        // 4. 消息分类（简化版）
        MessageClassification classification = classifyMessage(userMessage, session);

        // 5. 检索知识库并生成回复
        Message aiMessage = generateResponse(session, userMessage, emotionRecord, classification);

        // 6. 更新会话
        updateSession(session, aiMessage);

        // 7. 构建响应
        return buildChatResponse(session, aiMessage, emotionRecord, classification);
    }

    /**
     * 获取或创建会话
     */
    private Session getOrCreateSession(ChatRequest request) {
        if (request.getSessionId() != null) {
            Optional<Session> sessionOpt = sessionRepository.findById(request.getSessionId());
            if (sessionOpt.isPresent()) {
                return sessionOpt.get();
            }
        }

        // 创建新会话
        Session session = new Session();
        session.setUserId(request.getUserId());
        session.setStatus(0); // 进行中
        session.setChannel(request.getChannel() != null ? request.getChannel() : "WEB");
        session.setStartTime(LocalDateTime.now());
        session.setLastMessageTime(LocalDateTime.now());
        return sessionRepository.save(session);
    }

    /**
     * 保存用户消息
     */
    private Message saveUserMessage(Session session, ChatRequest request) {
        Message message = new Message();
        message.setSessionId(session.getId());
        message.setMessageType("USER");
        message.setContent(request.getContent());
        message.setSenderId(request.getUserId());
        message.setSendTime(LocalDateTime.now());
        message.setStatus(1); // 已发送
        message.setExtra(request.getExtra());
        return messageRepository.save(message);
    }

    /**
     * 情绪分析（简化版实现）
     */
    private EmotionRecord analyzeEmotion(Message message, Session session) {
        EmotionRecord record = new EmotionRecord();
        record.setSessionId(session.getId());
        record.setMessageId(message.getId());
        record.setUserId(session.getUserId());

        // 简单的关键词情绪分析
        String content = message.getContent().toLowerCase();
        String emotionType = "NEUTRAL";
        int score = 50;
        boolean needAgent = false;

        if (containsNegativeWords(content)) {
            emotionType = "NEGATIVE";
            score = 30;
        } else if (containsPositiveWords(content)) {
            emotionType = "POSITIVE";
            score = 80;
        }

        if (containsAngryWords(content)) {
            emotionType = "ANGRY";
            score = 20;
            needAgent = true;
        }

        record.setEmotionType(emotionType);
        record.setEmotionScore(score);
        record.setIntensity(score);
        record.setNeedAgent(needAgent);

        return emotionRecordRepository.save(record);
    }

    /**
     * 消息分类（简化版实现）
     */
    private MessageClassification classifyMessage(Message message, Session session) {
        String content = message.getContent().toLowerCase();
        Long classificationId = 1L; // 默认其他问题
        int confidence = 60;

        // 简单的关键词分类
        if (content.contains("订单") || content.contains("发货") || content.contains("物流")) {
            classificationId = 1L; // 订单问题
            confidence = 85;
        } else if (content.contains("支付") || content.contains("付款")) {
            classificationId = 2L; // 支付问题
            confidence = 85;
        } else if (content.contains("退款") || content.contains("退货")) {
            classificationId = 3L; // 退款问题
            confidence = 85;
        } else if (content.contains("商品") || content.contains("产品")) {
            classificationId = 5L; // 商品咨询
            confidence = 80;
        }

        MessageClassification mc = new MessageClassification();
        mc.setMessageId(message.getId());
        mc.setSessionId(session.getId());
        mc.setClassificationId(classificationId);
        mc.setConfidence(confidence);
        mc.setIsPrimary(true);

        return messageClassificationRepository.save(mc);
    }

    /**
     * 生成AI回复
     */
    private Message generateResponse(Session session, Message userMessage,
                                     EmotionRecord emotionRecord,
                                     MessageClassification classification) {
        // 检索知识库
        List<Knowledge> knowledgeList = knowledgeRepository.findByKeywords(userMessage.getContent());

        String responseContent;
        Long knowledgeId = null;
        Double confidence = 0.7;

        if (!knowledgeList.isEmpty()) {
            Knowledge knowledge = knowledgeList.get(0);
            responseContent = knowledge.getContent();
            knowledgeId = knowledge.getId();
            confidence = 0.85;
            knowledgeRepository.incrementViewCount(knowledge.getId());
        } else {
            // 没有匹配到知识，使用默认回复
            responseContent = generateDefaultResponse(emotionRecord, classification);
        }

        // 如果情绪极端，添加人工客服提示
        if (emotionRecord.getNeedAgent()) {
            responseContent += "\n\n检测到您可能需要更多帮助，如需人工客服，请回复'转人工'。";
        }

        Message aiMessage = new Message();
        aiMessage.setSessionId(session.getId());
        aiMessage.setMessageType("AI");
        aiMessage.setContent(responseContent);
        aiMessage.setSendTime(LocalDateTime.now());
        aiMessage.setStatus(1);
        aiMessage.setKnowledgeId(knowledgeId);
        aiMessage.setConfidence(confidence);

        return messageRepository.save(aiMessage);
    }

    /**
     * 生成默认回复
     */
    private String generateDefaultResponse(EmotionRecord emotionRecord,
                                           MessageClassification classification) {
        StringBuilder sb = new StringBuilder();
        sb.append("感谢您的咨询！\n\n");

        if ("NEGATIVE".equals(emotionRecord.getEmotionType())) {
            sb.append("很抱歉给您带来不便，");
        }

        sb.append("关于您的问题，我已记录并会尽快为您处理。\n");
        sb.append("如需更多帮助，您可以：\n");
        sb.append("1. 详细描述您的问题\n");
        sb.append("2. 回复'转人工'联系客服人员\n");
        sb.append("3. 查看常见问题解答");

        return sb.toString();
    }

    /**
     * 更新会话
     */
    private void updateSession(Session session, Message aiMessage) {
        session.setLastMessageTime(aiMessage.getSendTime());
        sessionRepository.update(session);
    }

    /**
     * 构建响应
     */
    private ChatResponse buildChatResponse(Session session, Message aiMessage,
                                           EmotionRecord emotionRecord,
                                           MessageClassification classification) {
        // 获取分类名称
        String classificationName = "其他问题";
        Optional<Classification> classOpt = classificationRepository.findById(classification.getClassificationId());
        if (classOpt.isPresent()) {
            classificationName = classOpt.get().getName();
        }

        // 计算情绪等级 1-4
        int emotionLevel = calculateEmotionLevel(emotionRecord.getEmotionScore());

        return ChatResponse.builder()
                .sessionId(session.getId())
                .messageId(aiMessage.getId())
                .content(aiMessage.getContent())
                .messageType(aiMessage.getMessageType())
                .confidence(aiMessage.getConfidence())
                .emotionLevel(emotionLevel)
                .classification(classificationName)
                .needAgent(emotionRecord.getNeedAgent())
                .sendTime(aiMessage.getSendTime())
                .build();
    }

    /**
     * 计算情绪等级
     */
    private int calculateEmotionLevel(Integer score) {
        if (score >= 80) return 1; // 积极
        if (score >= 60) return 2; // 中性
        if (score >= 40) return 3; // 消极
        return 4; // 负面
    }

    /**
     * 检查是否包含负面词汇
     */
    private boolean containsNegativeWords(String content) {
        String[] negativeWords = {"不好", "差", "失望", "不满", "投诉", "问题", "坏了", "无法"};
        for (String word : negativeWords) {
            if (content.contains(word)) return true;
        }
        return false;
    }

    /**
     * 检查是否包含正面词汇
     */
    private boolean containsPositiveWords(String content) {
        String[] positiveWords = {"好", "棒", "满意", "喜欢", "感谢", "谢谢", "不错"};
        for (String word : positiveWords) {
            if (content.contains(word)) return true;
        }
        return false;
    }

    /**
     * 检查是否包含愤怒词汇
     */
    private boolean containsAngryWords(String content) {
        String[] angryWords = {"愤怒", "气死", "投诉", "垃圾", "骗子", "退钱", "起诉"};
        for (String word : angryWords) {
            if (content.contains(word)) return true;
        }
        return false;
    }
}
