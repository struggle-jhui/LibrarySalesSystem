package com.epam.librarysalessystem.service;

import com.epam.librarysalessystem.dao.OrderDao;
import com.epam.librarysalessystem.entity.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * OrderService
 *
 * @Since 2022/3/18
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 查询所有的订单
     *
     * @return member of list
     */
    public List<OrderInfo> findOderInfoList() {
        return orderDao.findAll();
    }

    /**
     * 创建或者更新订单信息
     *
     * @return order of list
     */
    public OrderInfo saveOrder(OrderInfo orderInfo)  {
        return orderDao.save(orderInfo);
    }
}
