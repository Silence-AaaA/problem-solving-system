package com.wly.dto;

import com.wly.domain.Topic;
import lombok.Data;

import java.util.Date;

@Data
public class TopicDto {
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

}
