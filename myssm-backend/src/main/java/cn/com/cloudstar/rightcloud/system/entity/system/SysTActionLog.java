package cn.com.cloudstar.rightcloud.system.entity.system;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@Document(collection="action_log")
@CompoundIndexes({
//        @CompoundIndex(name = "account_idx", def = "{'account': 1, 'action_time': -1}"),
        @CompoundIndex(name = "actionname_idx", def = "{'action_name': 1, 'action_time': -1}")
})
public class SysTActionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("_id")
    private String mongoId;

    @Transient
    private Long id;

    @Indexed
    @Field("account")
    private String account;

    @Field("action_method")
    private String actionMethod;

    @Indexed
    @Field("action_name")
    private String actionName;

    @Indexed
    @Field("action_time")
    private Date actionTime;

    @Field("action_path")
    private String actionPath;

    private String param;

    private String cause;

    @Field("remote_ip")
    private String remoteIp;

    @Field("http_method")
    private String httpMethod;

    @Field("lb_ip")
    private String lbIp;

    @Field("company_name")
    private String companyName;

    @Field("company_id")
    private String companyId;

    @Field("role_name")
    private String roleName;

    private String client;

    public SysTActionLog(){
        super();
    }


    @PersistenceConstructor
    public SysTActionLog(String mongoId, String account, String actionMethod, String actionName, Date actionTime, String actionPath, String param, String cause, String remoteIp, String httpMethod, String lbIp, String client, String companyName, String roleName, String companyId) {
        this.account = account;
        this.actionMethod = actionMethod;
        this.actionName = actionName;
        this.actionTime = actionTime;
        this.actionPath = actionPath;
        this.param = param;
        this.cause = cause;
        this.remoteIp = remoteIp;
        this.httpMethod = httpMethod;
        this.lbIp = lbIp;
        this.client = client;
        this.companyName = companyName;
        this.roleName = roleName;
        this.companyId = companyId;
        this.mongoId = mongoId;
    }

    private SysTActionLog(Builder builder) {
        setId(builder.id);
        setAccount(builder.account);
        setActionMethod(builder.actionMethod);
        setActionTime(builder.actionTime);
        setActionPath(builder.actionPath);
        setParam(builder.param);
        setCause(builder.cause);
        setRemoteIp(builder.remoteIp);
        setHttpMethod(builder.httpMethod);
        setLbIp(builder.lbIp);
        setClient(builder.client);
        setCompanyName(builder.companyName);
        setRoleName(builder.roleName);
        setActionName(builder.actionName);
        setCompanyId(builder.companyId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(SysTActionLog copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.account = copy.account;
        builder.actionMethod = copy.actionMethod;
        builder.actionTime = copy.actionTime;
        builder.actionPath = copy.actionPath;
        builder.param = copy.param;
        builder.cause = copy.cause;
        builder.remoteIp = copy.remoteIp;
        builder.httpMethod = copy.httpMethod;
        builder.lbIp = copy.lbIp;
        builder.client = copy.client;
        builder.companyName = copy.companyName;
        builder.roleName = copy.roleName;
        builder.actionName = copy.actionName;
        builder.companyId = copy.companyId;
        return builder;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getActionMethod() {
        return actionMethod;
    }

    public void setActionMethod(String actionMethod) {
        this.actionMethod = actionMethod;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getActionPath() {
        return actionPath;
    }

    public void setActionPath(String actionPath) {
        this.actionPath = actionPath;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getLbIp() {
        return lbIp;
    }

    public void setLbIp(String lbIp) {
        this.lbIp = lbIp;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static final class Builder {
        private Long id;
        private String account;
        private String actionMethod;
        private Date actionTime;
        private String actionPath;
        private String param;
        private String cause;
        private String remoteIp;
        private String httpMethod;
        private String lbIp;
        private String client;
        private String companyName;
        private String roleName;
        private String actionName;
        private String companyId;



        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder account(String val) {
            account = val;
            return this;
        }

        public Builder actionMethod(String val) {
            actionMethod = val;
            return this;
        }

        public Builder actionTime(Date val) {
            actionTime = val;
            return this;
        }

        public Builder actionPath(String val) {
            actionPath = val;
            return this;
        }

        public Builder param(String val) {
            param = val;
            return this;
        }

        public Builder cause(String val) {
            cause = val;
            return this;
        }

        public Builder remoteIp(String val) {
            remoteIp = val;
            return this;
        }

        public Builder httpMethod(String val) {
            httpMethod = val;
            return this;
        }

        public Builder lbIp(String val) {
            lbIp = val;
            return this;
        }

        public Builder client(String val) {
            client = val;
            return this;
        }

        public Builder companyName(String val) {
            companyName = val;
            return this;
        }

        public Builder roleName(String val) {
            roleName = val;
            return this;
        }
        public Builder actionName(String val){
            actionName = val;
            return this;
        }

        public Builder companyId(String val){
            companyId = val;
            return this;
        }

        public SysTActionLog build() {
            return new SysTActionLog(this);
        }
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }
}