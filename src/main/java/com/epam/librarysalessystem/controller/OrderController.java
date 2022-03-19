package com.epam.librarysalessystem.controller;

import com.epam.librarysalessystem.entity.OrderInfo;
import com.epam.librarysalessystem.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * OrderController
 *
 * @Since 2022/3/18
 */
@RestController
@Slf4j
@RequestMapping("/library/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public List<OrderInfo> findOderInfoList() {
        log.info("findOderInfoList start");
        List<OrderInfo> allOrders = orderService.findOderInfoList();
        log.debug("findOderInfoList end, allOrders is {}", allOrders);
        return allOrders;
    }

    @PostMapping()
    public void createOrderInfo(@Valid @RequestBody OrderInfo orderInfo) {
        log.info("createOrderInfo start, input param is {}", orderInfo);
        orderService.saveOrder(orderInfo);
    }
}
