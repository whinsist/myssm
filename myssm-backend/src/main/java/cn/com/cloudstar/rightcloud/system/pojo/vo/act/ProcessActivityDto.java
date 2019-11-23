package cn.com.cloudstar.rightcloud.system.pojo.vo.act;

import java.util.Date;

/**
 * 流程活动记录
 * @author yuz
 * @date 2018/8/6
 */
public class ProcessActivityDto {

    /**
     * 记录ID
     */
    String id;
    /**
     * 活动ID
     */
    String activityId;
    /**
     * 活动名称
     */
    String activityName;
    /**
     * 活动处理人
     */
    String assigneeId;
    /**
     * 活动处理人名称
     */
    String assigneeName;
    /**
     * 活动类型
     * startEvent: 流程开始事件
     * userTask:   用户任务
     * serviceTask: 后台任务
     * endEvent:    流程结束事件
     */
    String activityType;
    /**
     * 活动开始时间
     */
    Date   startTime;
    /**
     * 活动结束时间
     */
    Date   endTime;
    /**
     * 活动状态
     * pass: 通过
     * reject: 拒绝
     */
    String status;
    /**
     * 任务注释
     */
    String comment;

    /**
     * 是否为当前活动节点
     */
    boolean actived;

    /**
     * 节点左边距
     */
    int actLeft;
    /**
     * 节点右边距
     */
    int actTop;
    /**
     * 节点宽度
     */
    int actWidth;
    /**
     * 节点高度
     */
    int actHeight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public int getActLeft() {
        return actLeft;
    }

    public void setActLeft(int actLeft) {
        this.actLeft = actLeft;
    }

    public int getActTop() {
        return actTop;
    }

    public void setActTop(int actTop) {
        this.actTop = actTop;
    }

    public int getActWidth() {
        return actWidth;
    }

    public void setActWidth(int actWidth) {
        this.actWidth = actWidth;
    }

    public int getActHeight() {
        return actHeight;
    }

    public void setActHeight(int actHeight) {
        this.actHeight = actHeight;
    }
}
