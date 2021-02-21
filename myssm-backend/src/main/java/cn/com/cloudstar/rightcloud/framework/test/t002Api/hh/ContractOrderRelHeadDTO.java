package cn.com.cloudstar.rightcloud.framework.test.t002Api.hh;

import com.deepoove.poi.config.Name;
import lombok.Data;

import java.util.List;

/**
 * FileName: ContractOrderRelHeadDTO
 * Author:   ladeng
 * Date:     2018-09-14 09:59
 * Description: 合同与采购订单关系
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Data
public class ContractOrderRelHeadDTO {

    @Name("ITE")
    private DetailData detailTable;
    private String optCode;//请求标识
    private String optMessage;//消息文本

    private String orderNo;//采购订单编号
    private String contractNo;//合同号
    private String compCode;//公司代码
    private String compName;//公司名称
    private String supplierCode;//供应商编号
    private String supplireName;//供应商名称
    private String orderTypeCode;//订单类型代码
    private String orderTypeDesc;//订单类型描述
    private String printDate;//打印日期
    private String orderCreateUserName;//制单人
    private String orderAuditUserName;//审核人
    private String orderAuditDate;//审核日期
    private String purchaseGroupCode;//采购组编号
    private String purchaseGroupName;//采购组描述
    private String currency;//币种

    private List<ContractOrderRelItemDTO> itemList;//明细行信息

    public DetailData getDetailTable() {
        return detailTable;
    }

    public void setDetailTable(DetailData detailTable) {
        this.detailTable = detailTable;
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    public String getOptMessage() {
        return optMessage;
    }

    public void setOptMessage(String optMessage) {
        this.optMessage = optMessage;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplireName() {
        return supplireName;
    }

    public void setSupplireName(String supplireName) {
        this.supplireName = supplireName;
    }

    public String getOrderTypeCode() {
        return orderTypeCode;
    }

    public void setOrderTypeCode(String orderTypeCode) {
        this.orderTypeCode = orderTypeCode;
    }

    public String getOrderTypeDesc() {
        return orderTypeDesc;
    }

    public void setOrderTypeDesc(String orderTypeDesc) {
        this.orderTypeDesc = orderTypeDesc;
    }

    public String getPrintDate() {
        return printDate;
    }

    public void setPrintDate(String printDate) {
        this.printDate = printDate;
    }

    public String getOrderCreateUserName() {
        return orderCreateUserName;
    }

    public void setOrderCreateUserName(String orderCreateUserName) {
        this.orderCreateUserName = orderCreateUserName;
    }

    public String getOrderAuditUserName() {
        return orderAuditUserName;
    }

    public void setOrderAuditUserName(String orderAuditUserName) {
        this.orderAuditUserName = orderAuditUserName;
    }

    public String getOrderAuditDate() {
        return orderAuditDate;
    }

    public void setOrderAuditDate(String orderAuditDate) {
        this.orderAuditDate = orderAuditDate;
    }

    public String getPurchaseGroupCode() {
        return purchaseGroupCode;
    }

    public void setPurchaseGroupCode(String purchaseGroupCode) {
        this.purchaseGroupCode = purchaseGroupCode;
    }

    public String getPurchaseGroupName() {
        return purchaseGroupName;
    }

    public void setPurchaseGroupName(String purchaseGroupName) {
        this.purchaseGroupName = purchaseGroupName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<ContractOrderRelItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<ContractOrderRelItemDTO> itemList) {
        this.itemList = itemList;
    }


}
