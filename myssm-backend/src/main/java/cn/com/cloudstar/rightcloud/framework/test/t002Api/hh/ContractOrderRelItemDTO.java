package cn.com.cloudstar.rightcloud.framework.test.t002Api.hh;

import lombok.Data;

/**
 * FileName: ContractOrderRelItemDTO
 * Author:   ladeng
 * Date:     2018-09-14 09:59
 * Description: 合同与采购订单关系
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Data
public class ContractOrderRelItemDTO {
    private String orderNo;//采购订单编号
    private String orderLineNo;//采购订单行项目号
    private String delFlag;//删除标志
    private String goodsCode;//物料编码
    private String goodsName;//物料描述
    private String unit;//单位
    private String qty;//数量
    private String applyDept;//申请部门
    private String applyUser;//申请人
    private String applyUserTel;//申请人电话
    private String deliveryDate;//交货日期
    private String deliveryFactoryCode;//交货工厂编号
    private String deliveryFactoryName;//交货工厂描述（模板中显示描述）
    private String lgort;//库存地点编号
    private String lgobe;//库存地点描述（模板中显示描述）
    private String planTypeCode;//计划类型编号
    private String planTypeName;//计划类型描述（模板中显示描述）
    private String remark;//备注
    private String price;//含税单价
    private String taxCode;//税码编号
    private String taxName;//税码对应的value
    private String priceUnit;//价格单位
    private String lineAmount;//含税金额（行小计）

    // 20190614新增 供货地址
    private String supplyingAddress;
}
