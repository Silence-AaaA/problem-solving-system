package com.wly.service.Impl;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wly.domain.Competition;
import com.wly.domain.CompetitionParticipant;
import com.wly.domain.R;
import com.wly.mapper.CompetitionMapper;
import com.wly.mapper.CompetitionParticipantMapper;
import com.wly.service.CompetitionParticipantService;
import com.wly.user.domain.User;
import com.wly.user.mapper.UserMapper;
import com.wly.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Slience
* @description 针对表【competition_participant(比赛参与者表)】的数据库操作Service实现
* @createDate 2024-08-10 14:40:51
*/
@Service
public class CompetitionParticipantServiceImpl extends ServiceImpl<CompetitionParticipantMapper, CompetitionParticipant>
implements CompetitionParticipantService {

    @Autowired
    private CompetitionParticipantMapper competitionParticipantMapper;

    @Autowired
    private CompetitionMapper competitionMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加比赛
     * @param id
     * @return
     */
    @Override
    public R joinCompetition(Long id) {
        CompetitionParticipant cp = new CompetitionParticipant();
        cp.setCompetitionId(id);
        Competition competition = competitionMapper.selectById(id);
        cp.setCompetitionName(competition.getCompetitionName());

        Long userId = UserContext.getUser();
        cp.setUserId(userId);

//        获取USER对象
        User user = userMapper.selectById(UserContext.getUser());
        cp.setCreator(UserContext.getUser());
        cp.setUserName(user.getUserName());
        cp.setCreateDate(DateTime.now());

        competitionParticipantMapper.insert(cp);
        return R.ok();
    }
}
