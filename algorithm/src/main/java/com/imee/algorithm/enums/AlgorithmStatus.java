package com.imee.algorithm.enums;

/**
 * 算法状态枚举
 * 定义算法资产的生命周期状态
 */
public enum AlgorithmStatus {

    /**
     * 0 - 开发中:算法正在创建和编辑阶段
     */
    DEVELOPING(0, "开发中"),

    /**
     * 1 - 待审核: 算法已提交等待审核
     */
    PENDING(1, "待审核"),

    /**
     * 2 - 已发布: 审核通过,对外展示
     */
    PUBLISHED(2, "已发布"),

    /**
     * 3 - 已下线: 不再使用或被新版本替代
     */
    OFFLINE(3, "已下线");

    private final Integer code;
    private final String desc;

    AlgorithmStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getDescription() {
        return desc;
    }

    /**
     * 根据code获取枚举值
     * @param code 状态码
     * @return 对应的枚举值,不存在则返回null
     */
    public static AlgorithmStatus fromCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (AlgorithmStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 判断是否可以从当前状态流转到目标状态
     * @param target 目标状态
     * @return true表示可以流转
     */
    public boolean canTransitionTo(AlgorithmStatus target) {
        if (target == null) {
            return false;
        }

        switch (this) {
            case DEVELOPING:
                // 开发中 -> 待审核
                return target == PENDING;
            case PENDING:
                // 待审核 -> 开发中(退回) 或 已发布(通过)
                return target == DEVELOPING || target == PUBLISHED;
            case PUBLISHED:
                // 已发布 -> 已下线
                return target == OFFLINE;
            case OFFLINE:
                // 已下线 -> 已发布(重新发布)
                return target == PUBLISHED;
            default:
                return false;
        }
    }
}
