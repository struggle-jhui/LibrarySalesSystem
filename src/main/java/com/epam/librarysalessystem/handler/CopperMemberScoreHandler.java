package com.epam.librarysalessystem.handler;

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
public class CopperMemberScoreHandler implements ScoreHandler{


    @Autowired
    private MemberDao memberDao;

    @Override
    public void updateMemberScore(Member member, BigDecimal memberScore, BigDecimal totalOrderPrice) {
        if (totalOrderPrice.compareTo(memberScore) == 0) {
            return;
        }
        member.setScore(totalOrderPrice);
        memberDao.save(member);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ScoreHandlerFactory.register(MemberType.COPPER, this);
    }
}
