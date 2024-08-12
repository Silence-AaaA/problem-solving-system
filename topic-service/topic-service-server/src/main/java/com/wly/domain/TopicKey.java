package com.wly.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 题解表
 * @TableName topic_key
 */
public class TopicKey implements Serializable {
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
     * 参与人id
     */
    private Long participantId;

    /**
     * 题解内容
     */
    private String content;

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
     * 参与人id
     */
    public Long getParticipantId() {
        return participantId;
    }

    /**
     * 参与人id
     */
    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    /**
     * 题解内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 题解内容
     */
    public void setContent(String content) {
        this.content = content;
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
        TopicKey other = (TopicKey) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getTopicName() == null ? other.getTopicName() == null : this.getTopicName().equals(other.getTopicName()))
            && (this.getParticipantId() == null ? other.getParticipantId() == null : this.getParticipantId().equals(other.getParticipantId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
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
        result = prime * result + ((getParticipantId() == null) ? 0 : getParticipantId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
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
        sb.append(", participantId=").append(participantId);
        sb.append(", content=").append(content);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updater=").append(updater);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}