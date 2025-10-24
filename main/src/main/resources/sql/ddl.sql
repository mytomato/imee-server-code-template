-- ========================================
-- 算法资产管理系统 - 数据库初始化脚本
-- 数据库: H2 (兼容 MySQL)
-- 版本: v1.0
-- 创建日期: 2025-10-22
-- ========================================

-- ========================================
-- 1. 算法分类表 (algorithm_category)
-- 描述: 定义5大算法分类及其特性
-- ========================================
DROP TABLE IF EXISTS algorithm_category;

CREATE TABLE algorithm_category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    category_name VARCHAR(100) NOT NULL COMMENT '分类名称',
    category_code VARCHAR(50) NOT NULL COMMENT '分类编码',
    description TEXT COMMENT '分类描述',
    key_features TEXT COMMENT '需重视的特性',
    core_advantages TEXT COMMENT '核心优势',
    sort_order INT NOT NULL DEFAULT 0 COMMENT '排序序号',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE KEY uk_category_code (category_code)
) COMMENT='算法分类表';

-- 初始化5大分类数据
INSERT INTO algorithm_category (id, category_name, category_code, description, key_features, core_advantages, sort_order) VALUES
(1, '数值模拟/机理模型', 'numerical_model', '大气环境、水环境、源清单等数值模型', '模型反馈及时性、结果准确性', '信创适配、可解释性', 1),
(2, '经验模型/统计模型', 'statistical_model', '基于系统输入输出数据的统计关系', '模型反馈及时性、结果准确性', '模型准确性、运行效率', 2),
(3, 'AI模型', 'ai_model', '基于文本、图像、音频、视频、表格等多模态的AI算法', '可信度', '创造力、运行效率', 3),
(4, '指标算法', 'indicator_algorithm', '基于国/行/地/企标准的指标算法', '标准化、输出结果准确性', '实用性、权威性', 4),
(5, '通用大语言模型', 'llm_model', '适配过、验证过的通用大语言模型', '技术实力、多模态支持', '语言处理能力、推理能力', 5);


-- ========================================
-- 2. 算法资产表 (algorithm)
-- 描述: 存储算法资产的核心信息(七维模型)
-- ========================================
DROP TABLE IF EXISTS algorithm;

CREATE TABLE algorithm (
    -- 基本信息
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    name VARCHAR(200) NOT NULL COMMENT '算法名称',
    category_id INT NOT NULL COMMENT '算法分类ID',
    version_number VARCHAR(20) NOT NULL DEFAULT '1.0' COMMENT '当前版本号',
    status INT NOT NULL DEFAULT 0 COMMENT '算法状态: 0开发中 1待审核 2已发布 3已下线',
    description VARCHAR(2000) COMMENT '算法描述',
    tags VARCHAR(500) COMMENT '标签/关键词(逗号分隔)',

    -- 权属与责任
    is_proprietary TINYINT NOT NULL DEFAULT 1 COMMENT '是否自主产权: 1是 0否',
    origin_unit VARCHAR(100) COMMENT '原生单位',
    maintenance_unit VARCHAR(100) COMMENT '运维单位',
    responsible_person VARCHAR(50) NOT NULL COMMENT '算法负责人',
    contact_info VARCHAR(100) COMMENT '联系方式',

    -- 应用与场景
    application_scenario TEXT COMMENT '应用场景',
    business_domain VARCHAR(200) COMMENT '业务域(逗号分隔)',
    use_cases TEXT COMMENT '应用案例',

    -- 技术规格
    input_modality VARCHAR(100) COMMENT '输入模态: text/image/audio/video/table/timeseries/mixed',
    input_data_schema TEXT COMMENT '输入数据Schema(JSON格式)',
    output_data_schema TEXT COMMENT '输出数据Schema(JSON格式)',
    algorithm_principle TEXT COMMENT '算法原理',
    core_advantages TEXT COMMENT '核心优势',
    benchmark_model VARCHAR(200) COMMENT '对标模型',

    -- 性能与评估
    training_data_size VARCHAR(100) COMMENT '训练数据规模',
    model_parameters VARCHAR(50) COMMENT '模型参数量(如1.3B, 7B)',
    accuracy DECIMAL(5,2) COMMENT '准确率(百分比 0-100)',
    performance_metrics TEXT COMMENT '其他性能指标(JSON格式)',
    evaluation_dataset TEXT COMMENT '评估数据集',
    evaluation_report_url VARCHAR(500) COMMENT '评估报告URL',

    -- 资源与依赖
    data_requirements TEXT COMMENT '数据需求',
    compute_requirements TEXT COMMENT '算力需求',
    software_dependencies TEXT COMMENT '软件依赖',
    hardware_dependencies TEXT COMMENT '硬件依赖',

    -- 管理与维护
    view_count BIGINT NOT NULL DEFAULT 0 COMMENT '查看次数',
    creator_id VARCHAR(50) NOT NULL COMMENT '创建人ID',
    creator_name VARCHAR(50) NOT NULL COMMENT '创建人姓名',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    -- 索引
    INDEX idx_category_id (category_id),
    INDEX idx_status (status),
    INDEX idx_creator_id (creator_id),
    INDEX idx_create_time (create_time),
    INDEX idx_name (name),

    -- 约束
    CONSTRAINT chk_status CHECK (status IN (0, 1, 2, 3)),
    CONSTRAINT chk_is_proprietary CHECK (is_proprietary IN (0, 1)),
    CONSTRAINT fk_algorithm_category FOREIGN KEY (category_id) REFERENCES algorithm_category(id)
) COMMENT='算法资产表';


-- ========================================
-- 3. 算法版本表 (algorithm_version)
-- 描述: 管理算法的多版本历史
-- ========================================
DROP TABLE IF EXISTS algorithm_version;

CREATE TABLE algorithm_version (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    algorithm_id BIGINT NOT NULL COMMENT '算法ID',
    version_number VARCHAR(20) NOT NULL COMMENT '版本号(如1.0, 1.1, 2.0)',
    status INT NOT NULL DEFAULT 0 COMMENT '版本状态: 0开发中 1待审核 2已发布 3已下线',
    description VARCHAR(2000) COMMENT '版本描述',
    change_log TEXT COMMENT '变更日志',
    creator_id VARCHAR(50) NOT NULL COMMENT '创建人ID',
    creator_name VARCHAR(50) NOT NULL COMMENT '创建人姓名',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    publish_time DATETIME COMMENT '发布时间',

    -- 索引
    INDEX idx_algorithm_id (algorithm_id),
    INDEX idx_status (status),
    INDEX idx_create_time (create_time),
    UNIQUE KEY uk_algorithm_version (algorithm_id, version_number),

    -- 约束
    CONSTRAINT chk_version_status CHECK (status IN (0, 1, 2, 3)),
    CONSTRAINT fk_version_algorithm FOREIGN KEY (algorithm_id) REFERENCES algorithm(id)
) COMMENT='算法版本表';


-- ========================================
-- 4. 算法审核记录表 (algorithm_audit)
-- 描述: 记录算法资产的审核历史
-- ========================================
DROP TABLE IF EXISTS algorithm_audit;

CREATE TABLE algorithm_audit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    algorithm_id BIGINT NOT NULL COMMENT '算法ID',
    audit_result INT NOT NULL COMMENT '审核结果: 1通过 2退回',
    reject_reason TEXT COMMENT '退回原因(审核不通过时必填)',
    auditor_id VARCHAR(50) NOT NULL COMMENT '审核人ID',
    auditor_name VARCHAR(50) NOT NULL COMMENT '审核人姓名',
    audit_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审核时间',

    -- 索引
    INDEX idx_algorithm_id (algorithm_id),
    INDEX idx_auditor_id (auditor_id),
    INDEX idx_audit_time (audit_time),

    -- 约束
    CONSTRAINT chk_audit_result CHECK (audit_result IN (1, 2)),
    CONSTRAINT fk_audit_algorithm FOREIGN KEY (algorithm_id) REFERENCES algorithm(id)
) COMMENT='算法审核记录表';


-- ========================================
-- 5. 算法附件表 (algorithm_attachment)
-- 描述: 存储算法相关的文档和附件
-- ========================================
DROP TABLE IF EXISTS algorithm_attachment;

CREATE TABLE algorithm_attachment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    algorithm_id BIGINT NOT NULL COMMENT '算法ID',
    file_name VARCHAR(200) NOT NULL COMMENT '文件名',
    file_type VARCHAR(50) NOT NULL COMMENT '文件类型: technical_doc/user_manual/api_doc/case_doc',
    file_path VARCHAR(500) NOT NULL COMMENT '文件存储路径',
    file_size BIGINT COMMENT '文件大小(字节)',
    uploader_id VARCHAR(50) NOT NULL COMMENT '上传人ID',
    uploader_name VARCHAR(50) NOT NULL COMMENT '上传人姓名',
    upload_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',

    -- 索引
    INDEX idx_algorithm_id (algorithm_id),
    INDEX idx_file_type (file_type),

    -- 约束
    CONSTRAINT chk_file_type CHECK (file_type IN ('technical_doc', 'user_manual', 'api_doc', 'case_doc')),
    CONSTRAINT fk_attachment_algorithm FOREIGN KEY (algorithm_id) REFERENCES algorithm(id)
) COMMENT='算法附件表';


-- ========================================
-- 6. 操作日志表 (operation_log)
-- 描述: 记录所有关键操作,用于审计和追溯
-- ========================================
DROP TABLE IF EXISTS operation_log;

CREATE TABLE operation_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    algorithm_id BIGINT COMMENT '算法ID(删除算法时置NULL)',
    algorithm_name VARCHAR(200) COMMENT '算法名称(冗余字段)',
    operation_type VARCHAR(50) NOT NULL COMMENT '操作类型: CREATE/UPDATE/DELETE/SUBMIT_AUDIT/APPROVE/REJECT/PUBLISH/OFFLINE/CREATE_VERSION/UPLOAD_ATTACHMENT/DELETE_ATTACHMENT',
    operation_detail TEXT COMMENT '操作详情(JSON格式)',
    operator_id VARCHAR(50) NOT NULL COMMENT '操作人ID',
    operator_name VARCHAR(50) NOT NULL COMMENT '操作人姓名',
    operation_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',

    -- 索引
    INDEX idx_algorithm_id (algorithm_id),
    INDEX idx_operator_id (operator_id),
    INDEX idx_operation_time (operation_time),
    INDEX idx_operation_type (operation_type),

    -- 约束
    CONSTRAINT fk_log_algorithm FOREIGN KEY (algorithm_id) REFERENCES algorithm(id) ON DELETE SET NULL
) COMMENT='操作日志表';


-- ========================================
-- 7. 用户收藏表 (user_favorite)
-- 描述: 记录用户收藏的算法
-- ========================================
DROP TABLE IF EXISTS user_favorite;

CREATE TABLE user_favorite (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    algorithm_id BIGINT NOT NULL COMMENT '算法ID',
    user_id VARCHAR(50) NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) NOT NULL COMMENT '用户姓名',
    favorite_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',

    -- 索引
    INDEX idx_algorithm_id (algorithm_id),
    INDEX idx_user_id (user_id),
    UNIQUE KEY uk_user_algorithm (user_id, algorithm_id),

    -- 约束
    CONSTRAINT fk_favorite_algorithm FOREIGN KEY (algorithm_id) REFERENCES algorithm(id) ON DELETE CASCADE
) COMMENT='用户收藏表';


-- ========================================
-- 数据初始化完成
-- ========================================

-- 插入测试数据(可选,用于开发测试)
-- 注意: 生产环境请删除或注释掉以下测试数据

-- 测试算法1: 空气质量预测算法
INSERT INTO algorithm (
    name, category_id, version_number, status, description, tags,
    is_proprietary, origin_unit, maintenance_unit, responsible_person, contact_info,
    application_scenario, business_domain, use_cases,
    input_modality, input_data_schema, output_data_schema,
    algorithm_principle, core_advantages, benchmark_model,
    training_data_size, model_parameters, accuracy, performance_metrics,
    evaluation_dataset, evaluation_report_url,
    data_requirements, compute_requirements, software_dependencies, hardware_dependencies,
    view_count, creator_id, creator_name
) VALUES (
    '空气质量预测算法', 1, '1.0', 2, '基于LSTM神经网络的空气质量预测算法,能够准确预测未来24小时的AQI指数', '空气质量,预测,LSTM,时序分析',
    1, '研发中心', '运维部', '张三', 'zhangsan@example.com',
    '用于城市空气质量预测预报,支持实时数据输入和多步预测', '大气环境', '某市空气质量预测项目,准确率达到85%,已成功应用于10+城市',
    'timeseries', '{"fields":[{"name":"pm25","type":"float","unit":"μg/m³"},{"name":"pm10","type":"float","unit":"μg/m³"},{"name":"temperature","type":"float","unit":"℃"},{"name":"humidity","type":"float","unit":"%"}]}',
    '{"fields":[{"name":"predicted_aqi","type":"int","range":"0-500"},{"name":"predicted_level","type":"string","values":["优","良","轻度污染","中度污染","重度污染","严重污染"]}]}',
    '使用多层LSTM神经网络对时序数据进行建模,通过学习历史数据中的时间依赖关系,实现对未来空气质量的准确预测', '准确率高达85%,可解释性强,支持信创环境部署', '传统ARIMA统计模型',
    '10万条样本,覆盖2020-2022年数据', '2M参数', 85.50, '{"mae":5.2,"rmse":8.1,"r2":0.82}',
    '某市2020-2022年历史监测数据', '/reports/air-quality-eval-v1.pdf',
    '每小时更新的空气质量监测数据,包括PM2.5、PM10、气象数据等', '4核CPU, 8GB RAM, 推理时间<100ms', 'Python 3.8, TensorFlow 2.8, NumPy 1.21', '无特殊硬件要求,支持CPU推理',
    156, '10001', '李四'
);

-- 测试算法2: 水质监测AI模型
INSERT INTO algorithm (
    name, category_id, version_number, status, description, tags,
    is_proprietary, origin_unit, maintenance_unit, responsible_person, contact_info,
    application_scenario, business_domain, use_cases,
    input_modality, input_data_schema, output_data_schema,
    algorithm_principle, core_advantages,
    training_data_size, model_parameters, accuracy,
    data_requirements, compute_requirements, software_dependencies,
    view_count, creator_id, creator_name
) VALUES (
    '水质监测AI模型', 3, '1.0', 2, '基于深度学习的水质异常检测模型', '水质,监测,异常检测,CNN',
    1, '研发中心', '运维部', '王五', 'wangwu@example.com',
    '实时监测水质参数,自动识别异常情况并预警', '水环境', '某流域水质监测项目,成功识别95%的异常事件',
    'table', '{"fields":[{"name":"ph","type":"float"},{"name":"do","type":"float"},{"name":"cod","type":"float"},{"name":"nh3n","type":"float"}]}',
    '{"fields":[{"name":"is_abnormal","type":"boolean"},{"name":"abnormal_type","type":"string"},{"name":"confidence","type":"float"}]}',
    '使用CNN网络提取水质参数特征,结合异常检测算法识别异常模式', '实时性高,误报率低,支持多种异常类型识别',
    '50万条样本', '5M参数', 95.20,
    '实时水质监测数据', '8核CPU, 16GB RAM', 'Python 3.8, PyTorch 1.12',
    89, '10002', '赵六'
);

-- 测试算法3: 指标计算算法(开发中)
INSERT INTO algorithm (
    name, category_id, version_number, status, description, tags,
    is_proprietary, responsible_person, contact_info,
    application_scenario, business_domain,
    algorithm_principle, core_advantages,
    creator_id, creator_name
) VALUES (
    'AQI计算算法', 4, '1.0', 0, '基于国标HJ633-2012的空气质量指数计算算法', 'AQI,指标算法,国标',
    1, '孙七', 'sunqi@example.com',
    '用于计算空气质量指数(AQI),支持6项污染物指标', '大气环境',
    '严格按照HJ633-2012标准实现,分别计算各污染物的分指数IAQI,取最大值作为AQI', '标准化,权威性,准确性',
    '10001', '李四'
);

-- 测试版本数据
INSERT INTO algorithm_version (algorithm_id, version_number, status, description, creator_id, creator_name, publish_time)
VALUES (1, '1.0', 3, '初始版本', '10001', '李四', '2024-01-20 09:00:00');

-- 测试审核记录
INSERT INTO algorithm_audit (algorithm_id, audit_result, auditor_id, auditor_name, audit_time)
VALUES (1, 1, '10003', '管理员张三', '2024-01-20 09:00:00');

-- 测试附件数据
INSERT INTO algorithm_attachment (algorithm_id, file_name, file_type, file_path, file_size, uploader_id, uploader_name)
VALUES (1, '技术文档.pdf', 'technical_doc', '/upload/algorithm/1/technical_doc_20240115.pdf', 2048576, '10001', '李四');

-- 测试操作日志
INSERT INTO operation_log (algorithm_id, algorithm_name, operation_type, operation_detail, operator_id, operator_name)
VALUES (1, '空气质量预测算法', 'APPROVE', '{"status":"待审核->已发布"}', '10003', '管理员张三');

-- 测试收藏数据
INSERT INTO user_favorite (algorithm_id, user_id, user_name)
VALUES (1, '10002', '赵六');


-- ========================================
-- 脚本执行完成
-- ========================================
-- 说明:
-- 1. 本脚本适用于H2数据库,兼容MySQL语法
-- 2. 包含7张核心业务表和初始化数据
-- 3. 测试数据用于开发环境,生产环境请删除
-- 4. 所有表使用AUTO_INCREMENT主键
-- 5. 时间字段统一使用DATETIME类型
-- 6. 状态字段使用CHECK约束保证数据合法性
-- 7. 外键约束保证数据完整性
-- ========================================