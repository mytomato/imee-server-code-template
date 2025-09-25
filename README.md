# IMEE Server Code Template

IMEE Server Code Template 是一个基于 Spring Boot 和 MyBatis-Plus 的多模块项目模板工程，为代码开发提供样板和规范。

该项目使用H2内存数据库进行开发和测试，通过main模块作为统一入口启动整个应用程序，集成了各个业务模块的功能。

## 项目结构

```
imee-server-code-template
├── common          # 通用模块，提供工具类、公共接口和基础依赖
├── main            # 主应用模块，项目入口
├── user            # 用户管理模块，示例业务模块
└── pom.xml         # 根项目配置文件
```

数据库脚本位于 `main/src/main/resources/sql` 目录下。

## 模块介绍

### 1. common 模块

**作用**：
- 提供项目通用的工具类和公共接口
- 管理所有模块的公共依赖
- 包含统一的结果封装类、异常处理等基础组件

**主要功能**：
- 统一的返回结果封装 (`Result`, `ResultCode`, `IErrorCode`)
- 基础工具类和公共方法
- 公共依赖管理（Spring Boot、MyBatis Plus、FastJSON等）

### 2. main 模块

**作用**：
- 项目主入口模块
- 负责启动整个应用
- 集成其他业务模块的功能

**主要功能**：
- Spring Boot 应用启动类
- 主配置文件 (`application.yml`)
- 系统级别的配置和初始化
- 数据库脚本初始化配置
- CommandLineRunner实现用于初始化测试数据
- 统一管理所有模块的配置

### 3. user 模块

**作用**：
- 用户管理业务模块（示例）
- 展示基于 MyBatis-Plus 的 CRUD 实现
- 提供标准的 RESTful API 接口

**主要功能**：
- 用户实体类定义
- MyBatis-Plus Mapper 接口
- Service 服务层实现
- Controller 控制器层实现
- 分页查询、增删改查等标准操作
- 使用H2数据库存储数据，表名为`user`

## 技术栈

- **核心框架**：Spring Boot 2.7.14
- **持久层框架**：MyBatis-Plus 3.5.3.1
- **JSON处理**：FastJSON 2.0.25
- **工具类**：Apache Commons Lang3 3.12.0
- **数据库**：H2内存数据库
- **构建工具**：Maven 3.x
- **Java版本**：Java 8

## 开发规范

### 1. 项目结构规范

1. 所有业务模块应继承根项目，并在 `pom.xml` 中声明对 `common` 模块的依赖
2. 每个模块应包含标准目录结构：
   ```
   module-name/
   ├── src/
   │   ├── main/
   │   │   ├── java/
   │   │   └── resources/
   │   └── test/
   │       ├── java/
   │       └── resources/
   └── pom.xml
   ```
3. 数据库脚本统一放在 `main/src/main/resources/sql` 目录下
4. 业务模块不应包含独立的配置文件（如 application.yml），所有配置应由 main 模块统一管理

### 2. 命名规范

1. **模块命名**：使用小写字母，多个单词用连字符分隔，如 `user-service`
2. **包命名**：使用公司域名反写，如 `com.imee.module-name`
3. **类命名**：采用大驼峰命名法，如 `UserController`
4. **方法命名**：采用小驼峰命名法，如 `getUserById`

### 3. 依赖管理规范

1. 所有公共依赖版本在根项目 `pom.xml` 的 `dependencyManagement` 中统一管理
2. 子模块引用公共依赖时无需指定版本号
3. 子模块引用 `common` 模块时无需指定版本号

### 4. 编码规范

1. **统一结果返回**：所有 RESTful API 接口应使用 `common` 模块中的 `Result` 类封装返回结果
2. **异常处理**：统一使用 Spring Boot 的全局异常处理机制
3. **日志记录**：使用 SLF4J 进行日志记录
4. **代码注释**：关键业务逻辑必须添加注释说明

### 5. 数据库规范

1. 默认使用 H2 内存数据库进行开发和测试
2. 表名使用小写字母，多个单词用下划线分隔
3. 主键统一命名为 `id`，类型为 `bigint`，自增
4. 逻辑删除字段统一命名为 `status`，0 表示删除，1 表示正常
5. 数据库表结构通过DDL脚本自动初始化

### 6. API 设计规范

1. RESTful API 设计遵循标准 HTTP 方法：
   - GET：查询资源
   - POST：创建资源
   - PUT：更新资源
   - DELETE：删除资源
2. 统一使用 JSON 格式进行数据交互
3. API 路径使用小写字母，多个单词用连字符分隔

## 快速开始

### 环境要求

- Java 8 或更高版本
- Maven 3.x 或更高版本

### 构建项目

```bash
# 克隆项目
git clone <repository-url>

# 进入项目目录
cd imee-server-code-template

# 清理并编译项目
mvn clean compile

# 打包项目
mvn clean package

# 或者只编译特定模块
mvn clean compile -pl main,user
```

### 运行项目

```bash
# 进入 main 模块目录
cd main

# 运行应用
mvn spring-boot:run
```

### 访问接口

项目启动后，可通过以下地址访问接口：

- Hello 接口：`http://localhost:8080/hello`

用户管理模块提供以下RESTful API接口：

- GET /api/users - 获取所有用户
- GET /api/users/{id} - 根据ID获取用户
- GET /api/users/page - 分页获取用户
- POST /api/users - 创建用户
- PUT /api/users/{id} - 更新用户
- DELETE /api/users/{id} - 删除用户（逻辑删除）

所有接口返回统一的JSON格式，包含code（状态码）、message（消息）和data（数据）三个字段。

## 模块扩展

要创建新的业务模块，请遵循以下步骤：

1. 在根目录下创建新的模块目录
2. 在模块目录中创建标准的 Maven 项目结构
3. 在模块的 `pom.xml` 中继承根项目并依赖 `common` 模块
4. 实现业务逻辑代码
5. 在根项目的 `pom.xml` 中添加新模块到 `<modules>` 列表中
6. 如果需要数据库表，可以在 `main/src/main/resources/sql/ddl.sql` 中添加相应的表结构定义

## 贡献

欢迎提交 Issue 和 Pull Request 来改进这个模板项目。

## 许可证

[MIT License](LICENSE)