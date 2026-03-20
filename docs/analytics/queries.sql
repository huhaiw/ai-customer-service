-- 户部 - 数据分析SQL

-- 日活跃会话数
SELECT COUNT(DISTINCT id) as daily_sessions
FROM t_session 
WHERE DATE(created_at) = CURRENT_DATE
AND delete_flag = 0;

-- 解决率统计
SELECT 
    COUNT(CASE WHEN status = 1 THEN 1 END) * 100.0 / COUNT(*) as resolution_rate
FROM t_session
WHERE DATE(created_at) = CURRENT_DATE
AND delete_flag = 0;

-- 问题分类分布
SELECT 
    c.name as classification,
    COUNT(*) as count
FROM t_message_classification mc
JOIN t_classification c ON mc.classification_id = c.id
WHERE DATE(mc.created_at) = CURRENT_DATE
GROUP BY c.name
ORDER BY count DESC;

-- 情绪分布
SELECT 
    emotion_type,
    COUNT(*) as count
FROM t_emotion_record
WHERE DATE(created_at) = CURRENT_DATE
GROUP BY emotion_type
ORDER BY count DESC;
