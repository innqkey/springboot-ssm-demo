package com.kenneth.model;

import java.util.Date;

import com.kenneth.constant.ContextConstant;
import com.kenneth.domain.QuartzInfo;

/**
 *  定时任务详情Model
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class QuartzInfoModel extends QuartzInfo {
	
	private static final long serialVersionUID = 1L;

	/** 状态 - 禁用 */
	public static final Byte  STATE_DISABLE = 10;
	/** 状态 - 启用 */
	public static final Byte  STATE_ENABLE = 20;
	/**
     * 状态中文转换
     * 
     * @param state
     * @return
     */
    public static String stateConvert(Byte state) {
        String stateStr;
        if (ContextConstant.STATE_DISABLE == state) {
            stateStr = "禁用";
        } else {
            stateStr = "启用";
        }
        return stateStr;
    }
    
	
	/**
	 * 任务状态描述
	 */
	private String stateStr;
	
	/**
	 * 上次执行时间
	 */
	private Date lastStartTime;

	private String successRate;

	public String getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(String successRate) {
		this.successRate = successRate;
	}

	/**
	 * 获取任务状态描述
	 * @return stateStr
	 */
	public String getStateStr() {
		this.stateStr = stateConvert(this.getState());
		return stateStr;
	}

	/**
	 * 设置任务状态描述
	 * @param stateStr
	 */
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	/**
	 * 获取上次执行时间
	 * 
	 * @return lastStartTime
	 */
	public Date getLastStartTime() {
		return lastStartTime;
	}

	/**
	 * 设置上次执行时间
	 * 
	 * @param lastStartTime
	 */
	public void setLastStartTime(Date lastStartTime) {
		this.lastStartTime = lastStartTime;
	}

	
	
	
	
	
	
}
