package com.wly.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目表
 * @TableName topic
 */
public class Topic implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 题目名称
     */
    private String topicName;

    /**
     * 题目类型
     */
    private String type;

    /**
     * 难易程度(0, 1 ,2 ,3 ,4 ,5)
     */
    private Object level;

    /**
     * 题目描述
     */
    private String description;

    /**
     * 出题人
     */
    private String author;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新者
     */
    private Long updater;

    /**
     * 更新时间
     */
    private Date updateDate;

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
     * 题目类型
     */
    public String getType() {
        return type;
    }

    /**
     * 题目类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 难易程度(0, 1 ,2 ,3 ,4 ,5)
     */
    public Object getLevel() {
        return level;
    }

    /**
     * 难易程度(0, 1 ,2 ,3 ,4 ,5)
     */
    public void setLevel(Object level) {
        this.level = level;
    }

    /**
     * 题目描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 题目描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 出题人
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 出题人
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 创建者
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * 创建者
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 更新者
     */
    public Long getUpdater() {
        return updater;
    }

    /**
     * 更新者
     */
    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    /**
     * 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
        Topic other = (Topic) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTopicName() == null ? other.getTopicName() == null : this.getTopicName().equals(other.getTopicName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdater() == null ? other.getUpdater() == null : this.getUpdater().equals(other.getUpdater()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTopicName() == null) ? 0 : getTopicName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdater() == null) ? 0 : getUpdater().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", topicName=").append(topicName);
        sb.append(", type=").append(type);
        sb.append(", level=").append(level);
        sb.append(", description=").append(description);
        sb.append(", author=").append(author);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updater=").append(updater);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}