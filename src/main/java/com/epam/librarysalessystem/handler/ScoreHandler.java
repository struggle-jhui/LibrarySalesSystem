package com.epam.librarysalessystem.handler;

import com.epam.librarysalessystem.entity.Member;
import org.springframework.beans.factory.InitializingBean;

import java.math.BigDecimal;

/**
 * ScoreHandler
 *
 * @Since 2022/3/18
 */
public interface ScoreHandler extends InitializingBean {
    void updateMemberScore(Member member, BigDecimal memberScore, BigDecimal totalOrderPrice);
}
