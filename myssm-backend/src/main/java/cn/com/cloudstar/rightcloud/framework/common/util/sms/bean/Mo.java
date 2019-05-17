/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util.sms.bean;

/**
 * The type Mo.
 */
public class Mo implements java.io.Serializable {
    private String addSerial;

    private String addSerialRev;

    private String channelnumber;

    private String mobileNumber;

    private String sentTime;

    private String smsContent;

    /**
     * Instantiates a new Mo.
     */
    public Mo() {
    }

    /**
     * Instantiates a new Mo.
     *
     * @param addSerial     the add serial
     * @param addSerialRev  the add serial rev
     * @param channelnumber the channelnumber
     * @param mobileNumber  the mobile number
     * @param sentTime      the sent time
     * @param smsContent    the sms content
     */
    public Mo(String addSerial, String addSerialRev, String channelnumber, String mobileNumber, String sentTime,
              String smsContent) {
        this.addSerial = addSerial;
        this.addSerialRev = addSerialRev;
        this.channelnumber = channelnumber;
        this.mobileNumber = mobileNumber;
        this.sentTime = sentTime;
        this.smsContent = smsContent;
    }

    /**
     * Gets the addSerial value for this Mo.
     *
     * @return addSerial add serial
     */
    public String getAddSerial() {
        return addSerial;
    }

    /**
     * Sets the addSerial value for this Mo.
     *
     * @param addSerial the add serial
     */
    public void setAddSerial(String addSerial) {
        this.addSerial = addSerial;
    }

    /**
     * Gets the addSerialRev value for this Mo.
     *
     * @return addSerialRev add serial rev
     */
    public String getAddSerialRev() {
        return addSerialRev;
    }

    /**
     * Sets the addSerialRev value for this Mo.
     *
     * @param addSerialRev the add serial rev
     */
    public void setAddSerialRev(String addSerialRev) {
        this.addSerialRev = addSerialRev;
    }

    /**
     * Gets the channelnumber value for this Mo.
     *
     * @return channelnumber channelnumber
     */
    public String getChannelnumber() {
        return channelnumber;
    }

    /**
     * Sets the channelnumber value for this Mo.
     *
     * @param channelnumber the channelnumber
     */
    public void setChannelnumber(String channelnumber) {
        this.channelnumber = channelnumber;
    }

    /**
     * Gets the mobileNumber value for this Mo.
     *
     * @return mobileNumber mobile number
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Sets the mobileNumber value for this Mo.
     *
     * @param mobileNumber the mobile number
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Gets the sentTime value for this Mo.
     *
     * @return sentTime sent time
     */
    public String getSentTime() {
        return sentTime;
    }

    /**
     * Sets the sentTime value for this Mo.
     *
     * @param sentTime the sent time
     */
    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    /**
     * Gets the smsContent value for this Mo.
     *
     * @return smsContent sms content
     */
    public String getSmsContent() {
        return smsContent;
    }

    /**
     * Sets the smsContent value for this Mo.
     *
     * @param smsContent the sms content
     */
    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    @Override
    public String toString() {
        return addSerial + "|" + addSerialRev + "|" + channelnumber + "|" + mobileNumber + "|" + sentTime + "|" +
                smsContent;
    }

}
