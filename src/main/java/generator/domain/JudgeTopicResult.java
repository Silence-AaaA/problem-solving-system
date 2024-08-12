package generator.domain;

import java.io.Serializable;

/**
 * 判断题题目结果表
 * @TableName judge_topic_result
 */
public class JudgeTopicResult implements Serializable {
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
     * 题目结果（0代表正确, 1为错误）
     */
    private Object topicResult;

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
     * 题目结果（0代表正确, 1为错误）
     */
    public Object getTopicResult() {
        return topicResult;
    }

    /**
     * 题目结果（0代表正确, 1为错误）
     */
    public void setTopicResult(Object topicResult) {
        this.topicResult = topicResult;
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
        JudgeTopicResult other = (JudgeTopicResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getTopicName() == null ? other.getTopicName() == null : this.getTopicName().equals(other.getTopicName()))
            && (this.getTopicResult() == null ? other.getTopicResult() == null : this.getTopicResult().equals(other.getTopicResult()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getTopicName() == null) ? 0 : getTopicName().hashCode());
        result = prime * result + ((getTopicResult() == null) ? 0 : getTopicResult().hashCode());
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
        sb.append(", topicResult=").append(topicResult);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}