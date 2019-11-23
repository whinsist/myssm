package cn.com.cloudstar.rightcloud.system.status;

/**
 * DESC:
 *
 * @author jzhongchen
 * @date 2019/07/15 10:14
 */
public interface OrderStatus {

    /**
     * 编辑状态
     */
    String EDITING = "editing";
    /**
     * 提交待审批 or 一级审批中
     */
    String APPROVING = "approving";
    /**
     * 一级审批驳回
     */
    String LEV1_REJECTED = "lev1_rejected";
    /**
     * 一级审批通过 or 二级审批中
     */
    String LEV1_PASSED = "lev1_passed";
    /**
     * 二级审批驳回
     */
    String LEV2_REJECTED = "lev2_rejected";
    /**
     * 二级审批通过 or 服务开通处理中
     */
    String SUCCEED = "succeed";
    /**
     * 开通完成
     */
    String COMPLETED = "completed";
    /**
     * 拒绝并关闭
     */
    String LEV2_REFUSED = "lev2_refused";
    /**
     * 一级审核拒绝
     */
    String LEV1_REFUSED = "lev1_refused";
    /**
     * 驳回 二级审核不需要改状态
     */
    @Deprecated
    String REJECTED = "rejected";

    String CANCELED = "canceled";

}
