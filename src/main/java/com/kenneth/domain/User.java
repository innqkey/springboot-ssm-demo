package com.kenneth.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "t_user")
public class User implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 学号
     */
    @Column(name = "card_no")
    private Long cardNo;

    /**
     * 身高
     */
    @Column(name = "user_hight")
    private BigDecimal userHight;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取学号
     *
     * @return card_no - 学号
     */
    public Long getCardNo() {
        return cardNo;
    }

    /**
     * 设置学号
     *
     * @param cardNo 学号
     */
    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 获取身高
     *
     * @return user_hight - 身高
     */
    public BigDecimal getUserHight() {
        return userHight;
    }

    /**
     * 设置身高
     *
     * @param userHight 身高
     */
    public void setUserHight(BigDecimal userHight) {
        this.userHight = userHight;
    }
}