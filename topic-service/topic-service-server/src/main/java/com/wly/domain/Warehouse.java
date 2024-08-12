package com.wly.domain;

import java.io.Serializable;

/**
 * 
 * @TableName warehouse
 */
public class Warehouse implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 题目id
     */
    private Long topicId;

    /**
     * 题目名称
     */
    private String topicName;

    /**
     * 题目完成状态 AC为通过, WA为正在尝试
     */
    private Object status;

    /**
     * 仓库id
     */
    private Long warehouseId;

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 题目id
     */
    public Long getTopicId() {
        return topicId;
    }

    /**
     * 题目id
     */
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    /**
     * 题目名称
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * 题目名称
     */
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    /**
     * 题目完成状态 AC为通过, WA为正在尝试
     */
    public Object getStatus() {
        return status;
    }

    /**
     * 题目完成状态 AC为通过, WA为正在尝试
     */
    public void setStatus(Object status) {
        this.status = status;
    }

    /**
     * 仓库id
     */
    public Long getWarehouseId() {
        return warehouseId;
    }

    /**
     * 仓库id
     */
    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Warehouse other = (Warehouse) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getTopicName() == null ? other.getTopicName() == null : this.getTopicName().equals(other.getTopicName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getWarehouseId() == null ? other.getWarehouseId() == null : this.getWarehouseId().equals(other.getWarehouseId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getTopicName() == null) ? 0 : getTopicName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getWarehouseId() == null) ? 0 : getWarehouseId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", topicId=").append(topicId);
        sb.append(", topicName=").append(topicName);
        sb.append(", status=").append(status);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}