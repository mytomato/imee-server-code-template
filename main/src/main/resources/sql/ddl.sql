-- H2 Database Schema Definition
-- Table: user
CREATE TABLE `user` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100),
    phone VARCHAR(20),
    status INT DEFAULT 1,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO `user` (username, email, phone, status) VALUES 
('zhangsan', 'zhangsan@example.com', '13800138001', 1),
('lisi', 'lisi@example.com', '13800138002', 1),
('wangwu', 'wangwu@example.com', '13800138003', 1);