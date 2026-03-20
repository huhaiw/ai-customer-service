#!/bin/bash
# 兵部 - 部署脚本

echo "=== 开始部署 AI 客服系统 ==="

# 1. 编译打包
mvn clean package -DskipTests

# 2. 构建镜像
docker-compose -f deploy/docker-compose.yml build

# 3. 启动服务
docker-compose -f deploy/docker-compose.yml up -d

# 4. 健康检查
sleep 10
curl -s http://localhost:8080/api/chat/health

echo "=== 部署完成 ==="
