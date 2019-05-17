/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.exception.resolver;

import java.util.HashMap;
import java.util.Map;

/**
 * Resolve the error message from english to chinese.
 *
 * @author Chaohong.Mao
 */
public class CloudErrorResolver {
    private static Map<String, String> msgTransMap = new HashMap<>();


    static {
        // common error
        msgTransMap.put("Your account does not have enough balance.", "账户余额不足");
        msgTransMap.put("InvalidAccessKeyId.NotFound", "您的ApiKey不正确");
        msgTransMap.put(
                "The request signature we calculated does not match the signature you provided. Check your AWS Secret Access Key and signing method. Consult the service documentation for details.",
                "您的SecretKey不正确"
        );
        msgTransMap.put("The security token included in the request is invalid.", "您的AccessKey不正确");
        msgTransMap.put("SignatureDoesNotMatch", "您的Access Key Secret不正确");
        msgTransMap.put("The Access Key ID provided does not exist in our records.", "无效的 AccessKeyId 值（该 key 不存在）");
        msgTransMap.put("The last token request is processing", "上一次请求还在处理中");
        msgTransMap.put("The request has failed due to a temporary failure of the server.", "服务不可用");
        msgTransMap.put("Specified action is not valid.", "该 key 无权调用该 API");
        msgTransMap.put("Request was denied due to request throttling.", "因系统流控拒绝访问");
        msgTransMap
                .put("The request processing has failed due to some unknown error, exception or failure.", "云平台内部错误");

        //validate
        msgTransMap.put("Account is inactive to this service", "访问控制权限未开通，请开通后再进行接入操作");

        // ecs error
        msgTransMap.put("The RegionId provided does not exist in our records.", "指定的区域不存在");
        msgTransMap.put(
                "The input parameter \"RegionId\" that is mandatory for processing this request is not supplied.",
                "缺少区域ID（实际情况也可能是该用户无权使用此区域）"
        );
        msgTransMap.put("The specified InstanceType is not authorized.", "指定的实例类型未授权使用");
        msgTransMap.put("The specified parameter \"SystemDisk.Size\" is less than the image size", "指定的 系统盘大小 小于镜像的大小");
        msgTransMap.put("The specified parameter \"SystemDisk.Size\" is more than the max size", "指定的 系统盘大小 大于最大容量上限");
        msgTransMap
                .put("The specified parameter \"SystemDisk.Size\" is less than the minimum size", "指定的 系统盘大小 小于最小容量");
        msgTransMap.put("The ZoneId provided does not exist in our records.", "指定的分区不存在（实际情况也可能是该用户无权使用此分区）");
        msgTransMap
                .put("The input parameter \"ImageId\" that is mandatory for processing this request is not supplied.",
                        "缺少镜像ID"
                );
        msgTransMap.put("The specified InstanceType beyond the permitted range.", "指定的实例类型不合法（超出可选范围）");
        msgTransMap.put(
                "The input parameter \"InstanceType\" that is mandatory for processing this request is not supplied.",
                "缺少实例类型值"
        );
        msgTransMap
                .put("The SecurityGroupId provided does not exist in our records.", "指定的安全组不存在（实际情况也可能是该用户无权使用此安全组）");
        msgTransMap.put(
                "The input parameter \"SecurityGroupId\" that is mandatory for processing this request is not supplied.",
                "缺少安全组ID"
        );
        msgTransMap.put("The specified parameter \"Description\" is not valid.", "指定的描述格式不合法");
        msgTransMap.put("The specified InternetChargeType is not valid.", "指定的网络付费类型不存在");
        msgTransMap
                .put("The specified parameter \"InternetMaxBandwidthIn\" is not valid.", "指定的带宽峰值（入向）不合法（不是数字或超出范围）");
        msgTransMap
                .put("The specified parameter \"InternetMaxBandwidthOut\" is not valid.", "指定的带宽峰值（出向）不合法（不是数字或超出范围）");
        msgTransMap.put("The specified parameter \"HostName\" is not valid.", "指定的主机名格式不合法");
        msgTransMap.put("The specified parameter \"Password\" is not valid.", "指定的密码格式不合法");
        msgTransMap.put("The specified parameter \"SystemDisk.Category\" is not valid.", "指定的系统盘类型不合法");
        msgTransMap.put("The specified parameter \"SyatemDisk.DiskName or DataDisk.n.DiskName\" is not valid.",
                "指定的系统盘名称或系数据盘名称不合法"
        );
        msgTransMap.put(
                "The specified parameter \"SyatemDisk.DiskDescription\" or \"DataDisk.n.Description\" is not valid.",
                "指定的系统盘描述或数据盘描述不合法"
        );
        msgTransMap.put("The specified parameter \"DataDisk.n.Size\" is not valid.", "指定的数据盘大小不合法（不是数字）");
        msgTransMap.put("The specified parameter \"VlanId\" is not valid or vlan has not enough IP address.",
                "指定的 VlanId 不合法"
        );
        msgTransMap.put(
                "The specified DataDisk.n.Size beyond the permitted range, or the capacity of snapshot exceeds the size limit of the specified disk category.",
                "指定的数据盘大小不合法（超出范围）"
        );
        msgTransMap.put("The specified parameter \"DataDisk.n.Category\" is not valid.", "指定的数据盘类型不合法");
        msgTransMap.put("The specified parameter \"DataDisk.n.SnapshotId\" is not valid.", "指定的数据盘快照ID没找到");
        msgTransMap.put("The specified parameter \"DataDisk.n.Device\" is not valid.", "指定的数据盘设备不合法");
        msgTransMap.put("The specified image does not support the specified instance type.", "指定的实例类型上不允许使用该指定的镜像");
        msgTransMap.put("The specified snapshot is created before 2013-07-15.", "使用了2013-07-15之前创建的快照");
        msgTransMap.put("Living afterpay instances quota exceeded.", "用户的按量付费实例个数达到上限");
        msgTransMap.put("The specified image has not be subscribed.", "没有订阅镜像市场的镜像");
        msgTransMap.put("The specified Image is disabled or is deleted.", "镜像不可用或者不存在");
        msgTransMap.put("The disk category is not authorized.", "磁盘种类未被授权使用");
        msgTransMap.put("The data disk must be cloud disk when system disk category is cloud.", "系统盘是云磁盘则数据盘必须是云磁盘");
        msgTransMap.put("The specified snapshot has not completed yet.", "快照没有完成");
        msgTransMap.put("The specified snapshot is not allowed to create disk.", "特定磁盘的快照不能创建磁盘或者快照不能创建磁盘");
        msgTransMap.put("The total size of specified disk category in an instance exceeds.", "指定的磁盘种类超过了单实例的最大容量");
        msgTransMap.put("The specified device has been occupied.", "挂载点已被占用");
        msgTransMap.put(
                "The specified market image is not available, Or the specified user defined image includes product code because it is based on an image subscribed from marketplace, and that image in marketplace includeing exact the same product code has been removed.",
                "镜像市场的镜像已下架,或者自定义镜像中包含的产品code对应的镜像市场镜像已经下架"
        );
        msgTransMap.put("The ClusterId provided does not exist in our records.", "指定的 ClusterId 不存在");
        msgTransMap.put("The creation of Instance to the specified Zone is not allowed.", "该可用区无权创建实例或者分区和区域不匹配");
        msgTransMap.put("The specified zone does not offer the specified disk category.", "该可用区无权创建指定种类的磁盘");
        msgTransMap.put(
                "The specified Zone or cluster does not offer the specified disk category or the speicified zone and cluster do not match.",
                "分区或者集群不支持该磁盘种类，或者分区或者集群不匹配"
        );
        msgTransMap.put("The specified parameter \"InstanceName\" is not valid.", "指定的实例名称格式不合法");
        msgTransMap.put("The specified parameter \"Description\" is not valid.", "指定的实例描述不合法");
        msgTransMap.put("The specified Image is disabled or is deleted.", "指定的镜像找不到");
        msgTransMap.put("The resource is out of usage.", "分区或者集群的库存不够");
        msgTransMap.put("The request processing has failed due to some unknown error.", "该用户没有分区可用");
        msgTransMap.put("The specified region is in resource control, please try later.", "该区域暂时停售按量实例");
        msgTransMap.put("The specified region and cluster do not match.", "分区或者集群不匹配");
        msgTransMap.put("Exceeding the allowed amount of instances of a security group.", "该安全组内的实例数量已经达到上限");
        msgTransMap.put("There is no authority to create instance in the specified region.", "用户无权使用该区域");
        msgTransMap.put("The specified Zone or cluster does not offer the specified disk category.", "分区或者集群不支持磁盘种类");
        msgTransMap.put("The specified snapshot is system disk snapshot.", "系统盘快照不能创建数据盘");
        msgTransMap.put(
                "The capacity of snapshot exceeds the size limit of the specified disk category or the specified category is not authorizied.",
                "指定的数据盘大小不合法（超出范围）或者磁盘种类未被授权使用"
        );
        msgTransMap.put("The specified cluster does not offer the specified disk category.", "集群不支持磁盘种类");
        msgTransMap.put("The specified disk categories’ combination is not supported..", "所选磁盘类型的组合不支持");
        msgTransMap.put("The quota of portable cloud disk exceeds.", "可挂载的云磁盘数量已经达到上限（最多四块）");
        msgTransMap.put("The specified disk is not a portable disk and cannot be set to DeleteWithInstance attribute.",
                "该磁盘不支持挂载与卸载"
        );
        msgTransMap.put("The specified image is not support IoOptimized Instance.", "指定的镜像ID不支持 IO 优化实例");
        msgTransMap.put("Another Instance has been creating", "同步请求，创建 ECS 的数量超过一个");
        msgTransMap.put("The specified instancetype is not support IoOptimized instance", "实例类型不支持 IO 优化实例");
        msgTransMap.put("The specified zone does not support creating instance.", "所选的可用区不支持创建IO优化型实例");
        msgTransMap.put("The specified disk category is not support the specified instance type.", "所选磁盘种类不能用于所选实例类型");
        msgTransMap.put(
                "The specified DataDisk.n.Size beyond the permitted range, or the capacity of snapshot exceeds the size limit of the specified disk category.",
                "指定的 数据盘大小 不合法（超出范围）"
        );
        msgTransMap.put(
                "The specified image is from the image market，You have not bought it or your quota has been exceeded.",
                "指定的镜像来自镜像市场，您没有购买或者该镜像在次地域的配额已用完"
        );
        msgTransMap.put("The sum of the specified DataDisk.n.Size beyond the permitted range.",
                "指定的磁盘的总容量超过了单台实例可挂载磁盘总容量的上限"
        );
        msgTransMap
                .put("The specified imageid is not supported to create iooptimized instance", "指定的镜像 ID 不支持创建 IO 优化实例");
        msgTransMap.put("The specified zone does not offer the specified instancetype.", "该可用区不支持创建指定的实例规格");
        msgTransMap.put("The specified instancetype must be IoOptimized instance", "指定的实例规格必须是 I/O 优化实例");
        msgTransMap.put("The total number of specified disk in an instance exceeds.", "镜像中包含的数据盘和数据盘参数合并后，数据盘的总数超出限制");
        msgTransMap.put("Error vpc has no stable subnet or router entry.", "没有指定资源的操作权限");
        msgTransMap.put("Route entry not exists.", "路由表条目不存在");
        msgTransMap.put("Specified CIDR block is not valid.", "CIDR格式错误");
        msgTransMap.put("Specified VPC name is not valid.", "VPC名称不符合格式要求");
        msgTransMap.put("Specified virtual switch name is not valid.", "子网名称不符合格式要求");
        msgTransMap.put("Specified object has dependent resources", "存在依赖的资源");
        msgTransMap.put("Specified object has dependent resources.", "存在依赖的资源");

        // stop　ｅｃｓ
        msgTransMap.put("The current status of the resource does not support this operation.", "该资源目前的状态不支持此操作");
        msgTransMap.put("The specified operation is denied as your instance is locked for security reasons.",
                "该资源目前被安全锁定被拒绝操作"
        );

        //　delete ecs
        msgTransMap.put("The operation is not permitted due to charge type of the instance.", "不能删除预付费的实例");
        msgTransMap.put("The request processing has failed due to some unknown error.", "内部错误");
        msgTransMap.put("Specified operation is denied as the instance is being used by another product.",
                "实例被 SLB 引用，且 SLB 正在配置"
        );

        // normal msg
        msgTransMap.put("null", "系统错误");

        //securityGroup msg
        msgTransMap.put("Specified security group name is not valid.", "安全组名称输入不合法");
        msgTransMap.put("The specified parameter “Description” is not valid.", "安全组描述输入不合法");
        msgTransMap.put("The maximum number of security groups is reached.", "安全组个数超过限制");
        msgTransMap.put("The specified RegionId does not exist.", "指定的 RegionId 不存在");
        msgTransMap.put("Specified VPC does not exist.", "指定的VPC不存在");
        msgTransMap.put("Current VPC status does not support this operation.", "指定的VPC状态不对");
        msgTransMap.put("The specified parameter \"SourceCidrIp\" is not valid.", "SourceCidrIp 参数格式不正确");
        msgTransMap.put("The specified security group has been authorized in another one.", "指定的安全组被其他安全组授权无法执行删除操作");

        //rds
        msgTransMap.put("Account limit exceeded.", "账号数量超过限制");
        msgTransMap.put("The specified AccountName already exists.", "账户名称已存在");
        msgTransMap
                .put("The current status of the specified instance does not support this operation.", "当前实例状态不支持此操作");
        msgTransMap.put("The specified SecurityIps is invalid.", "组内白名单验证失败");
        msgTransMap.put("The specified EngineVersion is invalid.", "数据库版本验证失败");
        msgTransMap.put("The specified parameter \"AccountPassword\" is not valid.", "密码不符合要求");
        msgTransMap.put("The account balance is insufficient.", "账户余额不足，请先充值");
        msgTransMap.put("The specified public network type existed", "所选网络已存在");
        msgTransMap.put("The specified network type at least one.", "所选网络不存在");
        msgTransMap.put("The request references an incorrect order sales component. Contact the customer support.",
                "此次订购的RDS配置有误，请联系客服支持。"
        );
        msgTransMap.put("The request processing has failed due to some unknown error, exception or failure.",
                "所选区域库存不足，请选择其他区域。"
        );
        msgTransMap.put("Connection timed out: connect", "网络连接超时");
        msgTransMap.put("Invalid configuration for device '0'.", "设备‘0’的配置无效");
    }

    public static String getErrorMsg(String originMsg) {
        return msgTransMap.getOrDefault(originMsg, originMsg);
    }

}
