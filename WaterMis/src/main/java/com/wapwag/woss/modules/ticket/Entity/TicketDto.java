package com.wapwag.woss.modules.ticket.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wapwag.woss.common.persistence.DataEntity;

import java.util.Date;

public class TicketDto extends DataEntity<TicketDto> {
    private String sortName;
    private String sortOrder;

    private String ticketId;
    private String ticketType;
    private String ticketTypeName;
    private String title;
    private String comments;
    private String ticketLevel;
    private String address;
    private Date eventTime;//告警生成时间
    private String deviceCode;
    private String deviceName;
    private String pumpId;
    private String pumpName;
    private String areaId;
    private String channel;
    private String reportName;
    private String reportPhone;
    private Date  startTime;//计划开始
    private String startBeginTime;//执行时间开始
    private String startEndTime;//执行时间结束
    private Date endTime;//计划结束
    private String createBeginTime;//新建开始 用于条件
    private String createEndTime;//新建截止  用于条件

    private String validFlag;

    private String ticketReason;
    private String ticketDescription;
    private String bizId;
    private String status;
    private String currentStatusName;
    private String deptId;
    private String deptName;

    private String createByStr;
    private String updateBySre;

    private String isMyHandle;//待处理标志
    private String handleUserId;//待处理人;
    private String allHandleUser;//所有处理人

    private String mgName;//部门负责人姓名


    private String approveOpinion;//处理结果及其意见
    private String approveOperation;//处理类型 同意不同意



    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTicketLevel() {
        return ticketLevel;
    }

    public void setTicketLevel(String ticketLevel) {
        this.ticketLevel = ticketLevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getPumpId() {
        return pumpId;
    }

    public void setPumpId(String pumpId) {
        this.pumpId = pumpId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportPhone() {
        return reportPhone;
    }

    public void setReportPhone(String reportPhone) {
        this.reportPhone = reportPhone;
    }


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }






    public String getTicketReason() {
        return ticketReason;
    }

    public void setTicketReason(String ticketReason) {
        this.ticketReason = ticketReason;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getCreateByStr() {
        return createByStr;
    }

    public void setCreateByStr(String createByStr) {
        this.createByStr = createByStr;
    }

    public String getUpdateBySre() {
        return updateBySre;
    }

    public void setUpdateBySre(String updateBySre) {
        this.updateBySre = updateBySre;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPumpName() {
        return pumpName;
    }

    public void setPumpName(String pumpName) {
        this.pumpName = pumpName;
    }

    public String getCurrentStatusName() {
        return currentStatusName;
    }

    public void setCurrentStatusName(String currentStatusName) {
        this.currentStatusName = currentStatusName;
    }

    public String getIsMyHandle() {
        return isMyHandle;
    }

    public void setIsMyHandle(String isMyHandle) {
        this.isMyHandle = isMyHandle;
    }

    public String getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(String handleUserId) {
        this.handleUserId = handleUserId;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    public String getCreateBeginTime() {
        return createBeginTime;
    }

    public void setCreateBeginTime(String createBeginTime) {
        this.createBeginTime = createBeginTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public String getApproveOpinion() {
        return approveOpinion;
    }

    public void setApproveOpinion(String approveOpinion) {
        this.approveOpinion = approveOpinion;
    }

    public String getApproveOperation() {
        return approveOperation;
    }

    public void setApproveOperation(String approveOperation) {
        this.approveOperation = approveOperation;
    }

    public String getAllHandleUser() {
        return allHandleUser;
    }

    public void setAllHandleUser(String allHandleUser) {
        this.allHandleUser = allHandleUser;
    }

    public String getStartBeginTime() {
        return startBeginTime;
    }

    public void setStartBeginTime(String startBeginTime) {
        this.startBeginTime = startBeginTime;
    }

    public String getStartEndTime() {
        return startEndTime;
    }

    public void setStartEndTime(String startEndTime) {
        this.startEndTime = startEndTime;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getMgName() {
        return mgName;
    }

    public void setMgName(String mgName) {
        this.mgName = mgName;
    }
}
