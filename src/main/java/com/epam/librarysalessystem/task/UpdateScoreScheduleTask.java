package com.epam.librarysalessystem.task;

import com.epam.librarysalessystem.ScoreHandler.ScoreHandler;
import com.epam.librarysalessystem.ScoreHandler.ScoreHandlerFactory;
import com.epam.librarysalessystem.dao.MemberDao;
import com.epam.librarysalessystem.dao.OrderDao;
import com.epam.librarysalessystem.entity.Member;
import com.epam.librarysalessystem.entity.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UpdateScoreScheduleTask: 这个后期可以优化为mq的形式去修改member分数
 *
 * @Since 2022/3/18
 */
@Component
@Slf4j
public class UpdateScoreScheduleTask {
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

    @Scheduled(cron = "*/30 * * * * ?")
    public void execute() {
        log.info("update member score start");
        List<OrderInfo> allOrderList = orderDao.findAll();
        if (CollectionUtils.isEmpty(allOrderList)) {
            return;
        }
        Map<Long, BigDecimal> memberIdAndTotalPriceMap = allOrderList.stream().collect(Collectors.toMap(OrderInfo::getMemberId, OrderInfo::getPrice, BigDecimal::add));
        log.info("update member score memberIdAndTotalPriceMap is {}", memberIdAndTotalPriceMap);
        Iterator<Map.Entry<Long, BigDecimal>> iterator = memberIdAndTotalPriceMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, BigDecimal> entry = iterator.next();
            Long memberId = entry.getKey();
            BigDecimal totalPrice = entry.getValue();
            Optional<Member> memberOptional = memberDao.findById(Long.valueOf(memberId));

            log.info("update member score member is {}", memberOptional);
            if (!memberOptional.isPresent()) {
                continue;
            }
            Member member = memberOptional.get();
            log.info("update member score member is {}", member);
            ScoreHandler scoreHandler = ScoreHandlerFactory.getScoreHandler(member.getMemberType());
            scoreHandler.updateMemberScore(member, member.getScore() == null ? new BigDecimal(0) : member.getScore(), totalPrice);
        }
        log.info("update member score end.");
    }
}
