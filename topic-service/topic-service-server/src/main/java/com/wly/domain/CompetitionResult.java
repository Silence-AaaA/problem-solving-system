package com.wly.domain;

import java.io.Serializable;

/**
 * 比赛结果表
 * @TableName competition_result
 */
public class CompetitionResult implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 比赛id
     */
    private Long competitionId;

    /**
     * 比赛名称
     */
    private String commentName;

    /**
     * 比赛排名
     */
    private Long ranks;

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
     * 比赛名称
     */
    public String getCommentName() {
        return commentName;
    }

    /**
     * 比赛名称
     */
    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    /**
     * 比赛排名
     */
    public Long getRanks() {
        return ranks;
    }

    /**
     * 比赛排名
     */
    public void setRanks(Long ranks) {
        this.ranks = ranks;
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
        CompetitionResult other = (CompetitionResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompetitionId() == null ? other.getCompetitionId() == null : this.getCompetitionId().equals(other.getCompetitionId()))
            && (this.getCommentName() == null ? other.getCommentName() == null : this.getCommentName().equals(other.getCommentName()))
            && (this.getRanks() == null ? other.getRanks() == null : this.getRanks().equals(other.getRanks()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompetitionId() == null) ? 0 : getCompetitionId().hashCode());
        result = prime * result + ((getCommentName() == null) ? 0 : getCommentName().hashCode());
        result = prime * result + ((getRanks() == null) ? 0 : getRanks().hashCode());
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
        sb.append(", commentName=").append(commentName);
        sb.append(", ranks=").append(ranks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}