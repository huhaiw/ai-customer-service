-- 初始化分类数据
INSERT INTO t_classification (code, name, parent_id, level, sort, description, status) VALUES
('ORDER', '订单问题', 0, 1, 1, '订单相关问题', 1),
('PAYMENT', '支付问题', 0, 1, 2, '支付相关问题', 1),
('REFUND', '退款问题', 0, 1, 3, '退款相关问题', 1),
('DELIVERY', '物流问题', 0, 1, 4, '物流配送相关问题', 1),
('PRODUCT', '商品咨询', 0, 1, 5, '商品相关咨询', 1),
('ACCOUNT', '账户问题', 0, 1, 6, '账户相关问题', 1),
('OTHER', '其他问题', 0, 1, 99, '其他类型问题', 1);

-- 二级分类
INSERT INTO t_classification (code, name, parent_id, level, sort, description, status)
SELECT 'ORDER_QUERY', '订单查询', id, 2, 1, '查询订单状态', 1 FROM t_classification WHERE code = 'ORDER';

INSERT INTO t_classification (code, name, parent_id, level, sort, description, status)
SELECT 'ORDER_CANCEL', '取消订单', id, 2, 2, '取消订单', 1 FROM t_classification WHERE code = 'ORDER';

INSERT INTO t_classification (code, name, parent_id, level, sort, description, status)
SELECT 'ORDER_MODIFY', '修改订单', id, 2, 3, '修改订单信息', 1 FROM t_classification WHERE code = 'ORDER';

INSERT INTO t_classification (code, name, parent_id, level, sort, description, status)
SELECT 'PAYMENT_METHOD', '支付方式', id, 2, 1, '支付方式咨询', 1 FROM t_classification WHERE code = 'PAYMENT';

INSERT INTO t_classification (code, name, parent_id, level, sort, description, status)
SELECT 'PAYMENT_FAILED', '支付失败', id, 2, 2, '支付失败处理', 1 FROM t_classification WHERE code = 'PAYMENT';

-- 初始化知识库数据
INSERT INTO t_knowledge (title, content, category_id, type, keywords, status, source) VALUES
('如何查询订单状态？', '您可以通过以下方式查询订单状态：\n1. 登录APP，进入"我的订单"页面\n2. 输入订单号在首页搜索\n3. 联系客服提供订单号查询', 1, 'FAQ', '订单,查询,状态', 1, 'SYSTEM'),
('如何取消订单？', '取消订单步骤：\n1. 进入"我的订单"\n2. 找到需要取消的订单\n3. 点击"取消订单"按钮\n4. 选择取消原因并确认\n注意：已发货订单无法取消', 1, 'FAQ', '订单,取消', 1, 'SYSTEM'),
('支持哪些支付方式？', '我们支持以下支付方式：\n1. 支付宝\n2. 微信支付\n3. 银行卡支付\n4. 信用卡支付\n5. 花呗分期', 2, 'FAQ', '支付,方式', 1, 'SYSTEM'),
('支付失败怎么办？', '支付失败可能原因：\n1. 余额不足\n2. 网络异常\n3. 银行系统维护\n4. 支付密码错误\n\n建议检查后重试或更换支付方式', 2, 'FAQ', '支付,失败', 1, 'SYSTEM'),
('退款多久到账？', '退款到账时间：\n1. 支付宝/微信：1-3个工作日\n2. 银行卡：3-7个工作日\n3. 花呗：1-3个工作日\n\n退款将原路返回', 3, 'FAQ', '退款,到账', 1, 'SYSTEM'),
('如何查看物流信息？', '查看物流信息：\n1. 进入"我的订单"\n2. 点击订单查看详情\n3. 点击"查看物流"\n\n也可通过快递单号在快递官网查询', 4, 'FAQ', '物流,快递', 1, 'SYSTEM');
