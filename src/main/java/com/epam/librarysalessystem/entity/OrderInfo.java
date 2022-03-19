package com.epam.librarysalessystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * OrderInfo
 *
 * @Since 2022/3/18
 */
@Data
@Entity
@NoArgsConstructor
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1188006341549105281L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "请填写成员ID")
    private Long memberId;

    @NotBlank(message = "请填写商品名称")
    private String name;

    @NotNull(message = "请填写商品价格")
    private BigDecimal price;
}
