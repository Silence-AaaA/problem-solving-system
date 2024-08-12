package com.wly.domain;

import java.io.Serializable;

/**
 * 
 * @TableName competition_topic
 */
public class CompetitionTopic implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 比赛id
     */
    private Long competitionId;

    /**
     * 题目id
     */
    private Long topicId;

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
     * 比赛id
     */
    public Long getCompetitionId() {
        return competitionId;
    }

    /**
     * 比赛id
     */
    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
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
        CompetitionTopic other = (CompetitionTopic) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompetitionId() == null ? other.getCompetitionId() == null : this.getCompetitionId().equals(other.getCompetitionId()))
            && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompetitionId() == null) ? 0 : getCompetitionId().hashCode());
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", competitionId=").append(competitionId);
        sb.append(", topicId=").append(topicId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}