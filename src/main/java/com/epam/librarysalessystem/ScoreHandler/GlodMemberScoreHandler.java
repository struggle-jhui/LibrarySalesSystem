package com.epam.librarysalessystem.ScoreHandler;

import com.epam.librarysalessystem.dao.MemberDao;
import com.epam.librarysalessystem.entity.Member;
import com.epam.librarysalessystem.entity.MemberType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * GlodMemberScoreHandler
 *
 * @Since 2022/3/18
 */
@Component
public class GlodMemberScoreHandler implements ScoreHandler{
    private static final BigDecimal TRIPLE = new BigDecimal(3);

    @Autowired
    private MemberDao memberDao;

    @Override
    public void updateMemberScore(Member member, BigDecimal memberScore, BigDecimal totalOrderPrice) {
        BigDecimal correctScore = totalOrderPrice.multiply(TRIPLE);
        if (memberScore.compareTo(correctScore) == 0) {
            return;
        }
        member.setScore(correctScore);
        memberDao.save(member);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ScoreHandlerFactory.register(MemberType.GOLD, this);
    }
}
