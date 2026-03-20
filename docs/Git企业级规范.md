# Git企业级规范

> 版本：1.0
> 日期：2026-03-20
> 维护者：太子

---

## 一、Git工作流

### 1.1 分支模型

```
┌─────────────────────────────────────────────────────────┐
│                   Git Flow                           │
├─────────────────────────────────────────────────────────┤
│                                                         │
│   main (正式环境) ◄─────────────────────────── merge   │
│     ▲                                               │   │
│     │ merge                                         │   │
│   develop (预发布环境) ◄────────────────── merge    │   │
│     ▲                                            │   │
│     │ merge                                      │   │
│   feature/* (功能开发) ◄───────────────── PR      │   │
│                                                         │
│   fix/* (Bug修复) ◄───────────────────── PR        │   │
│                                                         │
│   hotfix/* (紧急修复) ◄──────────────── PR        │   │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

### 1.2 分支命名规范

| 分支类型 | 命名格式 | 示例 |
|----------|-----------|------|
| 功能分支 | `feature/功能描述-开发者` | `feature/chat-flow-zhongshu` |
| 修复分支 | `fix/问题描述-开发者` | `fix/login-bug-taizi` |
| 热修复分支 | `hotfix/问题描述-开发者` | `hotfix/security-fix-shangshu` |
| 发布分支 | `release/版本号` | `release/v1.0.0` |
| 开发分支 | `develop` | develop |

---

## 二、Commit规范

### 2.1 Commit消息格式

```
<类型>(<影响范围>): <简短描述>

[空行]
[详细描述（可选）]

[空行]
[Footer（可选）]
```

### 2.2 类型定义

| 类型 | 含义 | 示例 |
|------|------|------|
| feat | 新功能 | feat(chat): 添加情绪检测模块 |
| fix | Bug修复 | fix(QA): 修复知识库检索问题 |
| docs | 文档更新 | docs: 更新API文档 |
| style | 代码格式 | style: 格式化代码 |
| refactor | 重构 | refactor: 优化知识库结构 |
| perf | 性能优化 | perf: 优化检索速度 |
| test | 测试 | test: 添加单元测试 |
| build | 构建相关 | build: 更新Docker配置 |
| ci | CI/CD | ci: 添加GitHub Actions |
| chore | 维护 | chore: 更新依赖 |

### 2.3 示例

```bash
# 正确示例
feat(chat): 添加情绪检测模块

新增情绪检测功能，支持4级情绪识别：
- Lv1: 轻微 - 正常回应
- Lv2: 中等 - 增加同理心
- Lv3: 强烈 - 道歉+升级处理
- Lv4: 危险 - 立即转人工

Closes #123

# 错误示例
feat: 添加功能
fix bug
update
```

### 2.4 提交约束

- 首行不超过50字符
- 详细描述首行后空一行
- 使用祈使句
- 不要提交敏感信息

---

## 三、PR规范

### 3.1 PR标题格式

```
[<类型>] <简短描述>
```

示例：
```
[feat] 添加情绪检测模块
[fix] 修复知识库检索问题
[refactor] 优化代码结构
```

### 3.2 PR描述模板

```markdown
## 概述
[简要描述此PR解决的问题]

## 变更内容
- [ ] 功能1
- [ ] 功能2

## 测试
- [ ] 单元测试通过
- [ ] 集成测试通过
- [ ] 手动测试通过

## 影响范围
[描述此PR影响的范围]

## 截图（可选）
[如有UI变更，添加截图]

## Reviewer
@reviewer1
@reviewer2
```

### 3.3 PR合并要求

| 场景 | 要求 |
|------|------|
| 普通PR | 1人Review通过 |
| 核心模块 | 2人Review通过 |
| 热修复 | 可直接合并，事后Review |

---

## 四、代码Review标准

### 4.1 Review检查清单

```markdown
## 代码Review检查清单

### 代码规范 (20%)
- [ ] 命名规范
- [ ] 代码格式
- [ ] 注释充分

### 逻辑正确 (25%)
- [ ] 业务逻辑正确
- [ ] 边界处理
- [ ] 异常处理

### 性能 (20%)
- [ ] 无性能问题
- [ ] 资源使用合理

### 安全 (20%)
- [ ] 无安全漏洞
- [ ] 敏感信息处理正确

### 可维护性 (15%)
- [ ] 代码复杂度合理
- [ ] 无重复代码
- [ ] 易于测试
```

### 4.2 Review意见格式

```markdown
## Review意见

### 问题1 (严重)
> 位置: src/chat/emotion.py:45
> 问题: 这里的异常处理可能导致数据丢失

建议：
```python
# 当前代码
try:
    save(data)
except:
    pass

# 建议修改为
try:
    save(data)
except Exception as e:
    logger.error(f"保存失败: {e}")
    raise
```

### 问题2 (建议)
> 位置: src/chat/emotion.py:30
> 建议: 可以使用枚举替代硬编码

```

---

## 五、版本管理

### 5.1 版本号规范

```
主版本.次版本.修订号

- 主版本(MAJOR): 不兼容的API变更
- 次版本(MINOR): 向后兼容的新功能
- 修订号(PATCH): 向后兼容的问题修复
```

### 5.2 版本发布流程

```bash
# 1. 更新版本号
npm version patch  # 或 minor, major

# 2. 生成CHANGELOG
npm run changelog

# 3. 创建Tag
git tag -a v1.0.0 -m "Release v1.0.0"

# 4. 推送Tag
git push origin v1.0.0
```

### 5.3 CHANGELOG格式

```markdown
# Changelog

## [1.0.1] - 2026-03-20

### 添加
- 新功能A

### 修复
- 问题A修复

### 优化
- 性能优化A

## [1.0.0] - 2026-03-01
### 添加
- 初始版本
```

---

## 六、Git Hooks

### 6.1 预置Hooks

```bash
#!/bin/bash
# .git/hooks/commit-msg

# 检查Commit消息格式
commit_msg=$(cat "$1")
if ! echo "$commit_msg" | grep -qE '^(feat|fix|docs|style|refactor|perf|test|build|ci|chore)\('; then
    echo "Commit消息格式不正确"
    exit 1
fi
```

### 6.2 安装Hooks

```bash
# 安装Git Hooks
cp -r scripts/git-hooks/* .git/hooks/
chmod +x .git/hooks/*
```

---

## 七、日常工作流程

### 7.1 开始新功能

```bash
# 1. 更新develop分支
git checkout develop
git pull origin develop

# 2. 创建功能分支
git checkout -b feature/chat-flow-zhongshu

# 3. 开发...

# 4. 提交代码
git add .
git commit -m "feat(chat): 添加聊天流程"

# 5. 推送
git push origin feature/chat-flow-zhongshu
```

### 7.2 提交PR

```bash
# 1. 合并develop最新代码
git fetch origin
git rebase origin/develop

# 2. 推送并创建PR
git push origin feature/chat-flow-zhongshu
# 然后在GitHub创建PR
```

### 7.3 合并PR

```bash
# 1. Review通过后合并
# 使用Squash合并保持历史清晰

# 2. 删除分支
git branch -d feature/chat-flow-zhongshu
git push origin --delete feature/chat-flow-zhongshu
```

---

## 八、常见问题

### Q1: 提交后发现消息错误怎么办？

```bash
# 修改最后一次Commit
git commit --amend -m "正确的消息"

# 如果已经推送
git push -f
```

### Q2: 如何撤销提交？

```bash
# 撤销Commit但保留修改
git reset --soft HEAD~1

# 撤销Commit和修改
git reset --hard HEAD~1
```

### Q3: 合并冲突怎么办？

```bash
# 1. 拉取最新代码
git fetch origin

# 2. 在当前分支合并
git merge origin/develop

# 3. 解决冲突后
git add .
git commit -m "merge: 解决冲突"
```

---

## 九、附录

### 9.1 常用命令速查

```bash
# 创建分支
git checkout -b feature/xxx

# 切换分支
git checkout develop

# 查看状态
git status

# 查看差异
git diff

# 查看日志
git log --oneline --graph

# 撤销修改
git checkout -- .

# 暂存修改
git stash

# 恢复暂存
git stash pop
```

### 9.2 资源

- Git文档: https://git-scm.com/doc
- GitHub Flow: https://guides.github.com/introduction/flow/
