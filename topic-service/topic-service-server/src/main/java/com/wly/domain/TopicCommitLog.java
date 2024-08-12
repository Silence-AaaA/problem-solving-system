package com.wly.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目提交记录表
 * @TableName topic_commit_log
 */
public class TopicCommitLog implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 题目ID
     */
    private Long topicId;

    /**
     * 题目名称
     */
    private String topicName;

    /**
     * 提交记录
     */
    private String commitFile;

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
     * 题目ID
     */
    public Long getTopicId() {
        return topicId;
    }

    /**
     * 题目ID
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
     * 提交记录
     */
    public String getCommitFile() {
        return commitFile;
    }

    /**
     * 提交记录
     */
    public void setCommitFile(String commitFile) {
        this.commitFile = commitFile;
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
        TopicCommitLog other = (TopicCommitLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getTopicName() == null ? other.getTopicName() == null : this.getTopicName().equals(other.getTopicName()))
            && (this.getCommitFile() == null ? other.getCommitFile() == null : this.getCommitFile().equals(other.getCommitFile()))
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
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getTopicName() == null) ? 0 : getTopicName().hashCode());
        result = prime * result + ((getCommitFile() == null) ? 0 : getCommitFile().hashCode());
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
        sb.append(", topicId=").append(topicId);
        sb.append(", topicName=").append(topicName);
        sb.append(", commitFile=").append(commitFile);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updater=").append(updater);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}