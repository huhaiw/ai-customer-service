-- 会话表
CREATE TABLE IF NOT EXISTS t_session (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    status INT DEFAULT 0 COMMENT '会话状态：0-进行中，1-已结束，2-转人工',
    channel VARCHAR(50) COMMENT '渠道来源',
    last_message_time DATETIME COMMENT '最后消息时间',
    last_message VARCHAR(500) COMMENT '最后一条消息',
    message_count INT DEFAULT 0 COMMENT '消息总数',
    agent_id BIGINT COMMENT '客服ID',
    rating INT COMMENT '用户评分',
    feedback VARCHAR(500) COMMENT '用户反馈',
    extra TEXT COMMENT '扩展信息',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 消息表
CREATE TABLE IF NOT EXISTS t_message (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    session_id BIGINT NOT NULL COMMENT '会话ID',
    message_type VARCHAR(20) NOT NULL COMMENT '消息类型',
    content TEXT NOT NULL COMMENT '消息内容',
    status INT DEFAULT 1 COMMENT '消息状态',
    sender_id BIGINT COMMENT '发送者ID',
    send_time DATETIME COMMENT '发送时间',
    knowledge_id BIGINT COMMENT '知识来源ID',
    confidence DECIMAL(5,2) COMMENT '置信度',
    extra TEXT COMMENT '扩展信息',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 知识表
CREATE TABLE IF NOT EXISTS t_knowledge (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    title VARCHAR(200) NOT NULL COMMENT '知识标题',
    content TEXT NOT NULL COMMENT '知识内容',
    category_id BIGINT COMMENT '分类ID',
    type VARCHAR(50) COMMENT '知识类型',
    keywords VARCHAR(500) COMMENT '关键词',
    status INT DEFAULT 1 COMMENT '状态',
    view_count INT DEFAULT 0 COMMENT '访问次数',
    useful_count INT DEFAULT 0 COMMENT '有用次数',
    vector_id VARCHAR(100) COMMENT '向量ID',
    source VARCHAR(100) COMMENT '来源',
    extra TEXT COMMENT '扩展信息',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 情绪记录表
CREATE TABLE IF NOT EXISTS t_emotion_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    session_id BIGINT NOT NULL COMMENT '会话ID',
    message_id BIGINT NOT NULL COMMENT '消息ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    emotion_type VARCHAR(20) COMMENT '情绪类型',
    emotion_score INT COMMENT '情绪得分',
    intensity INT COMMENT '情绪强度',
    need_agent TINYINT DEFAULT 0 COMMENT '是否需要人工介入',
    analysis_detail TEXT COMMENT '分析详情',
    extra TEXT COMMENT '扩展信息',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 分类表
CREATE TABLE IF NOT EXISTS t_classification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    code VARCHAR(50) NOT NULL COMMENT '分类编码',
    name VARCHAR(100) NOT NULL COMMENT '分类名称',
    parent_id BIGINT DEFAULT 0 COMMENT '父分类ID',
    level INT DEFAULT 1 COMMENT '分类层级',
    sort INT DEFAULT 0 COMMENT '排序',
    description VARCHAR(500) COMMENT '描述',
    icon VARCHAR(100) COMMENT '图标',
    status INT DEFAULT 1 COMMENT '状态',
    extra TEXT COMMENT '扩展信息',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 消息分类表
CREATE TABLE IF NOT EXISTS t_message_classification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    message_id BIGINT NOT NULL COMMENT '消息ID',
    session_id BIGINT NOT NULL COMMENT '会话ID',
    classification_id BIGINT NOT NULL COMMENT '分类ID',
    confidence INT COMMENT '置信度',
    is_primary TINYINT DEFAULT 0 COMMENT '是否主要分类',
    detail TEXT COMMENT '分类详情',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 创建索引
CREATE INDEX idx_session_user ON t_session(user_id);
CREATE INDEX idx_session_status ON t_session(status);
CREATE INDEX idx_message_session ON t_message(session_id);
CREATE INDEX idx_message_type ON t_message(message_type);
CREATE INDEX idx_knowledge_category ON t_knowledge(category_id);
CREATE INDEX idx_knowledge_status ON t_knowledge(status);
CREATE INDEX idx_emotion_session ON t_emotion_record(session_id);
CREATE INDEX idx_emotion_message ON t_emotion_record(message_id);
CREATE INDEX idx_classification_parent ON t_classification(parent_id);
CREATE INDEX idx_msg_class_message ON t_message_classification(message_id);
