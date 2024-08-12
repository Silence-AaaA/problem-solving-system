package com.wly.dto;

import com.wly.domain.JudgeTopicResult;
import com.wly.domain.Topic;
import lombok.Data;

@Data
public class CompetitionTopicDto {

    private Topic topic;
    private JudgeTopicResult result;

}
