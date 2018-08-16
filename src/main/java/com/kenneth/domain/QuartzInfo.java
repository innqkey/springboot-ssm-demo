package com.kenneth.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_quartz_info")
public class QuartzInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 是否启用 10-禁用 20-启用
     */
    private Byte state;

    @Column(name = "message_id")
    private Long messageId;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 定时任务code标识
     */
    private String code;

    /**
     * 周期
     */
    private String cycle;

    /**
     * 定时任务执行类
     */
    @Column(name = "class_name")
    private String className;

    /**
     * 成功执行次数
     */
    private Integer succeed;

    /**
     * 执行失败次数
     */
    private Integer fail;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取是否启用 10-禁用 20-启用
     *
     * @return state - 是否启用 10-禁用 20-启用
     */
    public Byte getState() {
        return state;
    }

    /**
     * 设置是否启用 10-禁用 20-启用
     *
     * @param state 是否启用 10-禁用 20-启用
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * @return message_id
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * @param messageId
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取任务名称
     *
     * @return name - 任务名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置任务名称
     *
     * @param name 任务名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取定时任务code标识
     *
     * @return code - 定时任务code标识
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置定时任务code标识
     *
     * @param code 定时任务code标识
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取周期
     *
     * @return cycle - 周期
     */
    public String getCycle() {
        return cycle;
    }

    /**
     * 设置周期
     *
     * @param cycle 周期
     */
    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    /**
     * 获取定时任务执行类
     *
     * @return class_name - 定时任务执行类
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置定时任务执行类
     *
     * @param className 定时任务执行类
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取成功执行次数
     *
     * @return succeed - 成功执行次数
     */
    public Integer getSucceed() {
        return succeed;
    }

    /**
     * 设置成功执行次数
     *
     * @param succeed 成功执行次数
     */
    public void setSucceed(Integer succeed) {
        this.succeed = succeed;
    }

    /**
     * 获取执行失败次数
     *
     * @return fail - 执行失败次数
     */
    public Integer getFail() {
        return fail;
    }

    /**
     * 设置执行失败次数
     *
     * @param fail 执行失败次数
     */
    public void setFail(Integer fail) {
        this.fail = fail;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}