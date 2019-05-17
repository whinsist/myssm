/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util.sms.bean;

/**
 * The type Status report.
 */
public class StatusReport implements java.io.Serializable {
    private String errorCode;

    private String memo;

    private String mobile;

    private String receiveDate;

    private int reportStatus;

    private long seqID;

    private String serviceCodeAdd;

    private String submitDate;

    /**
     * Instantiates a new Status report.
     */
    public StatusReport() {
    }

    /**
     * Instantiates a new Status report.
     *
     * @param errorCode      the error code
     * @param memo           the memo
     * @param mobile         the mobile
     * @param receiveDate    the receive date
     * @param reportStatus   the report status
     * @param seqID          the seq id
     * @param serviceCodeAdd the service code add
     * @param submitDate     the submit date
     */
    public StatusReport(String errorCode, String memo, String mobile, String receiveDate, int reportStatus, long seqID,
                        String serviceCodeAdd, String submitDate) {
        this.errorCode = errorCode;
        this.memo = memo;
        this.mobile = mobile;
        this.receiveDate = receiveDate;
        this.reportStatus = reportStatus;
        this.seqID = seqID;
        this.serviceCodeAdd = serviceCodeAdd;
        this.submitDate = submitDate;
    }

    /**
     * Gets the errorCode value for this StatusReport.
     *
     * @return errorCode error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the errorCode value for this StatusReport.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the memo value for this StatusReport.
     *
     * @return memo memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Sets the memo value for this StatusReport.
     *
     * @param memo the memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * Gets the mobile value for this StatusReport.
     *
     * @return mobile mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the mobile value for this StatusReport.
     *
     * @param mobile the mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Gets the receiveDate value for this StatusReport.
     *
     * @return receiveDate receive date
     */
    public String getReceiveDate() {
        return receiveDate;
    }

    /**
     * Sets the receiveDate value for this StatusReport.
     *
     * @param receiveDate the receive date
     */
    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * Gets the reportStatus value for this StatusReport.
     *
     * @return reportStatus report status
     */
    public int getReportStatus() {
        return reportStatus;
    }

    /**
     * Sets the reportStatus value for this StatusReport.
     *
     * @param reportStatus the report status
     */
    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }

    /**
     * Gets the seqID value for this StatusReport.
     *
     * @return seqID seq id
     */
    public long getSeqID() {
        return seqID;
    }

    /**
     * Sets the seqID value for this StatusReport.
     *
     * @param seqID the seq id
     */
    public void setSeqID(long seqID) {
        this.seqID = seqID;
    }

    /**
     * Gets the serviceCodeAdd value for this StatusReport.
     *
     * @return serviceCodeAdd service code add
     */
    public String getServiceCodeAdd() {
        return serviceCodeAdd;
    }

    /**
     * Sets the serviceCodeAdd value for this StatusReport.
     *
     * @param serviceCodeAdd the service code add
     */
    public void setServiceCodeAdd(String serviceCodeAdd) {
        this.serviceCodeAdd = serviceCodeAdd;
    }

    /**
     * Gets the submitDate value for this StatusReport.
     *
     * @return submitDate submit date
     */
    public String getSubmitDate() {
        return submitDate;
    }

    /**
     * Sets the submitDate value for this StatusReport.
     *
     * @param submitDate the submit date
     */
    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }
}
