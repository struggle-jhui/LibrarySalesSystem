package com.epam.librarysalessystem.dao;

import com.epam.librarysalessystem.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderDao
 *
 * @Since 2022/3/18
 */
public interface OrderDao extends JpaRepository<OrderInfo, Long> {

}
