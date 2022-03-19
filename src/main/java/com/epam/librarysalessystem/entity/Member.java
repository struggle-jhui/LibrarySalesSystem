package com.epam.librarysalessystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Member: 成员信息
 *
 * @Since 2022/3/18
 */
@Data
@Entity
@NoArgsConstructor
public class Member implements Serializable {

    private static final long serialVersionUID = -42668623563599570L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String memberName;

    @NotBlank(message = "请填写身份证信息")
    @Pattern(regexp = "^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message = "身份证格式错误")
    private String identityCardId;

    @NotNull(message = "请填写年龄信息")
    @Min(18)
    @Max(80)
    private Integer age;

    @NotBlank(message = "请填写本人手机号")
    private String phone;

    @NotNull(message = "请填写成员类型信息")
    private MemberType memberType;

    @NotBlank(message = "请输入密码")
    private String passWord;

    private BigDecimal score;
}
