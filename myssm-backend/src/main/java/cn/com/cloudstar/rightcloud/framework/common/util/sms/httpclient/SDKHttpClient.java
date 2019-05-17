/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util.sms.httpclient;

import cn.com.cloudstar.rightcloud.framework.common.util.sms.bean.Mo;
import cn.com.cloudstar.rightcloud.framework.common.util.sms.bean.StatusReport;
import com.google.common.base.Strings;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sdk http client.
 */
public class SDKHttpClient {
    private static Logger logger = LoggerFactory.getLogger(SDKHttpClient.class);

    /**
     * Regist string.
     *
     * @param url   the url
     * @param param the param
     * @return the string
     */
// 下发
    public static String regist(String url, String param) {
        String ret = "失败";
        url = url + "?" + param;
        logger.debug("【SDKHttpClient】发送MT到SDK->" + url);
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);
        responseString = responseString.trim();
        if (!Strings.isNullOrEmpty(responseString)) {
            ret = xmlResponse(responseString);
        }
        return ret;
    }

    /**
     * Xml response string.
     *
     * @param response the response
     * @return the string
     */
// 统一解析格式
    public static String xmlResponse(String response) {
        String result = "失败";
        Document document = null;
        try {
            document = DocumentHelper.parseText(response);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        result = root.elementText("message");
        return result;
    }

    /**
     * Gets mos.
     *
     * @param url the url
     * @param sn  the sn
     * @param key the key
     * @return the mos
     */
// 获取mo
    public static List<Mo> getMos(String url, String sn, String key) {
        List<Mo> _Mos = new ArrayList<Mo>();

        if ("".equals(url)) {
            return _Mos;
        }
        String param = "cdkey=" + sn + "&password=" + key;
        url = url + "?" + param;
        logger.debug("【SDKHttpClient】Request-Url:" + url);
        HttpClientUtil.getInstance();
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);

        responseString = responseString.trim();
        if (!Strings.isNullOrEmpty(responseString)) {
            List<Element> elements = xmlDoc(responseString);
            for (Element element : elements) {
                if (null != element) {
                    logger.debug("【SDKHttpClient】上行请求->" + responseString);
                    Mo mo = new Mo();
                    mo.setMobileNumber(element.elementText("srctermid"));
                    mo.setSmsContent(element.elementText("msgcontent"));
                    mo.setAddSerial(element.elementText("addSerial"));
                    mo.setAddSerialRev(element.elementText("addSerialRev"));
                    mo.setSentTime(element.elementText("sendTime"));
                    _Mos.add(mo);
                }
            }
        }
        return _Mos;
    }

    // 解析状态、上行
    private static List<Element> xmlDoc(String response) {
        boolean result = false;
        Document document = null;
        try {
            document = DocumentHelper.parseText(response);
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
        Element root = document.getRootElement();
        List<Element> list = root.elements();
        ArrayList elemets = new ArrayList();
        // 增强for循环来遍历所有的U8ArrivalVouch节点
        for (Element element : list) {
            String message = element.getName();
            if ("message".equalsIgnoreCase(message)) {
                if (element.elements().size() > 0) {
                    // System.out.println("--------------------"+element.elements().size());
                    elemets.add(element);
                }
            }

        }
        return elemets;
    }

    /**
     * Gets reports.
     *
     * @param url the url
     * @param sn  the sn
     * @param key the key
     * @return the reports
     */
// 获取report
    public static List<StatusReport> getReports(String url, String sn, String key) {
        List<StatusReport> _Reports = new ArrayList<StatusReport>();
        if ("".equals(url)) {
            return _Reports;
        }
        String param = "cdkey=" + sn + "&password=" + key;
        url = url + "?" + param;
        logger.debug("【SDKHttpClient】Request-Url:" + url);
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);
        responseString = responseString.trim();
        if (!Strings.isNullOrEmpty(responseString)) {
            List<Element> elements = xmlDoc(responseString);
            for (Element element : elements) {
                if (null != element) {
                    logger.debug("【SDKHttpClient】REPORT->" + element.elementText("seqid"));
                    StatusReport report = new StatusReport();
                    report.setMobile(element.elementText("srctermid"));
                    report.setErrorCode(element.elementText("state"));
                    report.setSeqID(Long.parseLong(element.elementText("seqid")));
                    report.setReceiveDate(element.elementText("receiveDate"));
                    report.setSubmitDate(element.elementText("submitDate"));
                    report.setServiceCodeAdd(element.elementText("addSerialRev"));
                    _Reports.add(report);
                }
            }

        }
        return _Reports;
    }

    /**
     * Send sms string.
     *
     * @param url   the url
     * @param param the param
     * @return the string
     */
// 下发
    public static String sendSMS(String url, String param) {
        String ret = "";
        url = url + "?" + param;
        logger.debug("【SDKHttpClient】发送MT到SDK->" + url);
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);
        responseString = responseString.trim();
        if (!Strings.isNullOrEmpty(responseString)) {
            ret = xmlMt(responseString);
        }
        return ret;
    }

    /**
     * Xml mt string.
     *
     * @param response the response
     * @return the string
     */
// 解析下发response
    public static String xmlMt(String response) {
        String result = "0";
        Document document = null;
        try {
            document = DocumentHelper.parseText(response);
        } catch (DocumentException e) {
            e.printStackTrace();
            result = "-250";
        }
        Element root = document.getRootElement();
        result = root.elementText("error");
        if (null == result || "".equals(result)) {
            result = "-250";
        }
        return result;
    }

    /**
     * Gets balance.
     *
     * @param url   the url
     * @param param the param
     * @return the balance
     */
// 获取余额
    public static String getBalance(String url, String param) {
        String ret = "失败";
        url = url + "?" + param;
        // logger.info("【SDKHttpClient】Balance->"+url);
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);
        responseString = responseString.trim();
        if (!Strings.isNullOrEmpty(responseString)) {
            ret = xmlResponse(responseString);
        }
        return ret;
    }

//    public static void main(String[] args) {
//        String url = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/querybalance.action";
//        String param = "cdkey=8SDK-EMY-6699-RIQMT&password=146875";
//        String result = SDKHttpClient.getBalance(url, param);
//
//    }
}
