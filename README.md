# AI客服系统

> 企业级AI客服系统 - 自主研发，持续学习

## 项目概述

本项目是一个企业级AI客服系统，具备以下核心能力：
- 智能对话理解
- 情绪检测与回应
- 问题自动分类
- 知识库检索
- 每日自动学习
- 效果评估

## 技术架构

```
用户层 → 接入层 → AI核心层 → 知识库层 → 评估层
```

## 快速开始

```bash
# 克隆项目
git clone <repo>

# 安装依赖
pip install -r requirements.txt

# 运行
python src/main.py
```

## 文档

- [Git企业级规范](./docs/Git企业级规范.md)
- [架构设计](./docs/architecture/)
- [API文档](./docs/api/)
- [部署指南](./docs/guide/)

## 分支管理

- `main` - 正式环境
- `develop` - 预发布环境
- `feature/*` - 功能开发
- `fix/*` - Bug修复
- `hotfix/*` - 紧急修复

## 贡献指南

1. Fork项目
2. 创建功能分支 (`git checkout -b feature/xxx`)
3. 提交更改 (`git commit -m 'feat: 添加功能'`)
4. 推送分支 (`git push origin feature/xxx`)
5. 创建Pull Request

## License

MIT
