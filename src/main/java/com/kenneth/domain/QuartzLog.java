package com.kenneth.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_quartz_log")
public class QuartzLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 任务Id
     */
    @Column(name = "quartz_id")
    private Long quartzId;

    /**
     * 用时
     */
    private Long time;

    /**
     * 结果10:成功 20: 失败
     */
    private String result;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
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
     * 获取任务Id
     *
     * @return quartz_id - 任务Id
     */
    public Long getQuartzId() {
        return quartzId;
    }

    /**
     * 设置任务Id
     *
     * @param quartzId 任务Id
     */
    public void setQuartzId(Long quartzId) {
        this.quartzId = quartzId;
    }

    /**
     * 获取用时
     *
     * @return time - 用时
     */
    public Long getTime() {
        return time;
    }

    /**
     * 设置用时
     *
     * @param time 用时
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * 获取结果10:成功 20: 失败
     *
     * @return result - 结果10:成功 20: 失败
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置结果10:成功 20: 失败
     *
     * @param result 结果10:成功 20: 失败
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
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