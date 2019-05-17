/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util.ldap;


import cn.com.cloudstar.rightcloud.framework.common.util.JsonUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static javax.naming.directory.SearchControls.SUBTREE_SCOPE;

/**
 * Provides static methods to authenticate users, change passwords, etc.
 ******************************************************************************/

/**
 * @author jerry
 */
public class ActiveDirectory {

    private static String[] userAttributes = {"distinguishedName", "cn", "name", "uid", "sn", "givename", "memberOf", "samaccountname", "userPrincipalName","mail","company","department","unicodePwd","sAMAccountName","telephoneNumber","title","displayName"};

    private ActiveDirectory() {
    }

    //**************************************************************************
    //** getConnection
    //*************************************************************************/

    /**
     * Used to authenticate a user given a username/password. Domain name is
     * derived from the fully qualified domain name of the host machine.
     */
    public static LdapContext getConnection(String username, String password) throws NamingException {
        return getConnection(username, password, null, null,null,false,null);
    }


    //**************************************************************************
    //** getConnection
    //*************************************************************************/

    /**
     * Used to authenticate a user given a username/password and domain name.
     */
    public static LdapContext getConnection(String username, String password, String domainName)
            throws NamingException {
        return getConnection(username, password, domainName, null,null,false,null);
    }


    //**************************************************************************
    //** getConnection
    //*************************************************************************/

    /**
     * Used to authenticate a user given a username/password and domain name.
     * Provides an option to identify a specific a Active Directory server.
     * port default is 389
     */
    public static LdapContext getConnection(String username, String password, String domainName, String serverName,String port,boolean isAuth,String certificateFilePath)
            throws NamingException {
        if (port == null || port == ""){
            port = "389";
        }
        //isAuth = true, 证书认证，否则普通登录
        if (isAuth){
            System.setProperty("javax.net.ssl.trustStore",certificateFilePath);
        }

        if (domainName == null) {
            try {
                String fqdn = java.net.InetAddress.getLocalHost().getCanonicalHostName();
                if (fqdn.split("\\.").length > 1) { domainName = fqdn.substring(fqdn.indexOf(".") + 1); }
            } catch (java.net.UnknownHostException e) {}
        }

        if (password != null) {
            password = password.trim();
            if (password.length() == 0) { password = null; }
        }
        //bind by using the specified username/password
        Hashtable<String, String> props = new Hashtable<>();
        String principalName = username + "@" + domainName;
        props.put(Context.SECURITY_PRINCIPAL, principalName);
        if (password != null) { props.put(Context.SECURITY_CREDENTIALS, password); }

        String ldapURL = "ldap://" + serverName+":"+port;
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, ldapURL);

        try {
            return new InitialLdapContext(props, null);
        } catch (javax.naming.CommunicationException e) {
            e.printStackTrace();
            throw new NamingException(
                    "Failed to connect to " + domainName + ((serverName == null) ? "" : " through " + serverName));
        } catch (NamingException e) {
            throw new NamingException("Failed to authenticate " + username + "@" + domainName +
                    ((serverName == null) ? "" : " through " + serverName));
        }
    }

    //**************************************************************************
    //** getUser
    //*************************************************************************/

    /**
     * Used to check whether a username is valid.
     *
     * @param username A username to validate (e.g. "peter", "peter@acme.com",
     *                 or "ACME\peter").
     */
    public static User getUser(String username, LdapContext context) {
        try {
            String domainName = null;
            if (username.contains("@")) {
                username = username.substring(0, username.indexOf("@"));
                domainName = username.substring(username.indexOf("@") + 1);
            } else if (username.contains("\\")) {
                username = username.substring(0, username.indexOf("\\"));
                domainName = username.substring(username.indexOf("\\") + 1);
            } else {
                String authenticatedUser = (String) context.getEnvironment().get(Context.SECURITY_PRINCIPAL);
                if (authenticatedUser.contains("@")) {
                    domainName = authenticatedUser.substring(authenticatedUser.indexOf("@") + 1);
                }
            }

            if (domainName != null) {
                String principalName = username + "@" + domainName;
                SearchControls controls = new SearchControls();
                controls.setSearchScope(SUBTREE_SCOPE);
                controls.setReturningAttributes(userAttributes);
                NamingEnumeration<SearchResult> answer = context.search(toDC(domainName),
                        "(& (userPrincipalName=" + principalName +
                                ")(objectClass=user))",
                        controls
                );
                if (answer.hasMore()) {
                    Attributes attr = answer.next().getAttributes();
                    Attribute user = attr.get("userPrincipalName");
                    if (user != null) { return new User(attr); }
                }
            }
        } catch (NamingException e) {
        }
        return null;
    }

    //**************************************************************************
    //** getUsers
    //*************************************************************************/

    /**
     * Returns a list of users in the domain.
     */
    public static List<User> getUsers(LdapContext context) throws NamingException {

        //java.util.ArrayList<User> users = new java.util.ArrayList<User>();
        List<User> users = new ArrayList<>();
        String authenticatedUser = (String) context.getEnvironment().get(Context.SECURITY_PRINCIPAL);
        if (authenticatedUser.contains("@")) {
            String domainName = authenticatedUser.substring(authenticatedUser.indexOf("@") + 1);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SUBTREE_SCOPE);
            controls.setReturningAttributes(userAttributes);
            NamingEnumeration answer = context.search(toDC(domainName), "(objectClass=user)", controls);
            try {
                while (answer.hasMore()) {
                    Attributes attr = ((SearchResult) answer.next()).getAttributes();
                    Attribute user = attr.get("userPrincipalName");
                    if (user != null) {
                        users.add(new User(attr));
                    }
                }
            } catch (Exception e) {}
        }
        return users;
        //return users.toArray(new User[users.size()]);
    }

    public static List<User> getUsers(LdapContext context,String sAMAccountName,String displayName,String mail) throws NamingException {

        //java.util.ArrayList<User> users = new java.util.ArrayList<User>();
        List<User> users = new ArrayList<>();
        String authenticatedUser = (String) context.getEnvironment().get(Context.SECURITY_PRINCIPAL);
        if (authenticatedUser.contains("@")) {
            String domainName = authenticatedUser.substring(authenticatedUser.indexOf("@") + 1);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SUBTREE_SCOPE);
            controls.setReturningAttributes(userAttributes);
            String searchFilter = "(objectClass=user)";
            if (!StringUtil.isNullOrEmpty(sAMAccountName)){
                sAMAccountName = "*"+sAMAccountName+"*";
                searchFilter = "(&(sAMAccountName=" + sAMAccountName +
                        ")"+"(objectClass=user))";
            }else if (!StringUtil.isNullOrEmpty(displayName)){
                displayName = "*"+displayName+"*";
                searchFilter = "(&(displayName=" + displayName +
                        ")"+"(objectClass=user))";
            }else if(!StringUtil.isNullOrEmpty(mail)){
                mail = "*"+mail+"*";
                searchFilter = "(&(mail=" + mail +
                        ")"+"(objectClass=user))";
            }
            NamingEnumeration answer = context.search(toDC(domainName), searchFilter, controls);
            try {
                while (answer.hasMore()) {
                    Attributes attr = ((SearchResult) answer.next()).getAttributes();
                    Attribute user = attr.get("userPrincipalName");
                    if (user != null) {
                        users.add(new User(attr));
                    }
                }
            } catch (Exception e) {}
        }
        return users;
    }

    private static String getDomain(LdapContext context){
        String domainName = null;
        try {
            String authenticatedUser = (String) context.getEnvironment().get(Context.SECURITY_PRINCIPAL);
            if (authenticatedUser.contains("@")) {
                domainName = authenticatedUser.substring(authenticatedUser.indexOf("@") + 1);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return domainName;
    }

    private static String getPrincipalName(String username,LdapContext context){
        String domainName = getDomain(context);
        String principalName = null;
        if (domainName != null) {
            principalName = username + "@" + domainName;
        }
        return principalName;

    }

    private static String toDC(String domainName) {
        StringBuilder buf = new StringBuilder();
        for (String token : domainName.split("\\.")) {
            if (token.length() == 0) {
                continue;   // defensive check
            }
            if (buf.length() > 0) { buf.append(","); }
            buf.append("DC=").append(token);
        }
        return buf.toString();
    }


    //**************************************************************************
    //** User Class
    //*************************************************************************/

    /**
     * Used to represent a User in Active Directory
     */
    public static class User {
        private String distinguishedName;
        private String userPrincipal;
        private String commonName;
        private String mail;
        private String company;
        private String department;
        private String unicodePwd;
        private String sn;
        private String givenname;
        private String sAMAccountName;
        private String telephoneNumber;
        private String title;
        private String displayName;

        public User(Attributes attr) throws NamingException {
            userPrincipal = (String) attr.get("userPrincipalName").get();
            commonName = (String) attr.get("cn").get();
            distinguishedName = (String) attr.get("distinguishedName").get();
            if (attr.get("mail") !=null){
                mail  = (String) attr.get("mail").get();
            }
            if (attr.get("company") !=null){
                company  = (String) attr.get("company").get();
            }
            if (attr.get("department") !=null){
                department  = (String) attr.get("department").get();
            }
            if (attr.get("sn") !=null){
                sn  = (String)attr.get("sn").get() ;
            }

            if (attr.get("givenname") !=null){
                givenname  = (String) attr.get("givenname").get();
            }
            if (attr.get("sAMAccountName") !=null){
                sAMAccountName  = (String) attr.get("sAMAccountName").get();
            }
            if (attr.get("telephoneNumber") !=null){
                telephoneNumber  = (String) attr.get("telephoneNumber").get();
            }
            if (attr.get("title") !=null){
                title  = (String) attr.get("title").get();
            }
            if (attr.get("displayName") !=null){
                displayName  = (String) attr.get("displayName").get();
            }
        }

        public String getUserPrincipal() {
            return userPrincipal;
        }

        public String getCommonName() {
            return commonName;
        }

        public String getDistinguishedName() {
            return distinguishedName;
        }

        public String getMail() {
            return mail;
        }

        public String getCompany() {
            return company;
        }

        public String getDepartment() {
            return department;
        }

        public String getUnicodePwd() {
            return unicodePwd;
        }

        public String getSn() {
            return sn;
        }

        public String getGivenname() {
            return givenname;
        }

        public String getsAMAccountName() {
            return sAMAccountName;
        }

        public String getTelephoneNumber() {
            return telephoneNumber;
        }

        public String getTitle() {
            return title;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDistinguishedName(String distinguishedName) {
            this.distinguishedName = distinguishedName;
        }

        public void setUserPrincipal(String userPrincipal) {
            this.userPrincipal = userPrincipal;
        }

        public void setCommonName(String commonName) {
            this.commonName = commonName;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public void setUnicodePwd(String unicodePwd) {
            this.unicodePwd = unicodePwd;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public void setGivenname(String givenname) {
            this.givenname = givenname;
        }

        public void setsAMAccountName(String sAMAccountName) {
            this.sAMAccountName = sAMAccountName;
        }

        public void setTelephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return getDistinguishedName();
        }

        /**
         * Used to change the user password. Throws an IOException if the Domain
         * Controller is not LDAPS enabled.
         *
         * @param trustAllCerts If true, bypasses all certificate and host name
         *                      validation. If false, ensure that the LDAPS certificate has been
         *                      imported into a trust store and sourced before calling this method.
         *                      Example:
         *                      String keystore = "/usr/java/jdk1.5.0_01/jre/lib/security/cacerts";
         *                      System.setProperty("javax.net.ssl.trustStore",keystore);
         */
        public void changePassword(String newPass, boolean trustAllCerts, LdapContext context)
                throws IOException, NamingException {
            String dn = getDistinguishedName();
            //Switch to SSL/TLS
            StartTlsResponse tls = null;
            try {
                tls = (StartTlsResponse) context.extendedOperation(new StartTlsRequest());
            } catch (Exception e) {
                //"Problem creating object: javax.naming.ServiceUnavailableException: [LDAP: error code 52 - 00000000: LdapErr: DSID-0C090E09, comment: Error initializing SSL/TLS, data 0, v1db0"
                throw new IOException(
                        "Failed to establish SSL connection to the Domain Controller. Is LDAPS enabled?");
            }
            //Exchange certificates
            if (trustAllCerts) {
                tls.setHostnameVerifier(DO_NOT_VERIFY);
                SSLSocketFactory sf = null;
                try {
                    SSLContext sc = SSLContext.getInstance("TLS");
                    sc.init(null, TRUST_ALL_CERTS, null);
                    sf = sc.getSocketFactory();
                } catch (java.security.NoSuchAlgorithmException | java.security.KeyManagementException e) {

                }
                tls.negotiate(sf);
            } else {
                tls.negotiate();
            }
            //Change password
            try {
                ModificationItem[] mods = new ModificationItem[1];
                String newQuotedPassword = "\"" +newPass + "\"";
                byte[] newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
                mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                        new BasicAttribute("unicodePwd", newUnicodePassword));
                context.modifyAttributes(dn, mods);
            } catch (javax.naming.directory.InvalidAttributeValueException e) {
                String error = e.getMessage().trim();
                if (error.startsWith("[") && error.endsWith("]")) {
                    error = error.substring(1, error.length() - 1);
                }
                e.printStackTrace();
                tls.close();
                throw new NamingException("New password does not meet Active Directory requirements. " +
                        "Please ensure that the new password meets password complexity, " +
                        "length, minimum password age, and password history requirements.");
            } catch (NamingException e) {
                tls.close();
                throw e;
            }

            //Close the TLS/SSL session
            tls.close();
        }

        /**
         * 修改用户信息
         * @param user
         * @param trustAllCerts
         * @param context
         * @throws IOException
         * @throws NamingException
         */
        public void modifyUser(User user,boolean trustAllCerts, LdapContext context)
                throws IOException, NamingException {
            String dn = getDistinguishedName();

            //Switch to SSL/TLS
            StartTlsResponse tls = null;
            try {
                tls = (StartTlsResponse) context.extendedOperation(new StartTlsRequest());
            } catch (Exception e) {
                //"Problem creating object: javax.naming.ServiceUnavailableException: [LDAP: error code 52 - 00000000: LdapErr: DSID-0C090E09, comment: Error initializing SSL/TLS, data 0, v1db0"
                throw new IOException(
                        "Failed to establish SSL connection to the Domain Controller. Is LDAPS enabled?");
            }

            //Exchange certificates
            if (trustAllCerts) {
                tls.setHostnameVerifier(DO_NOT_VERIFY);
                SSLSocketFactory sf = null;
                try {
                    SSLContext sc = SSLContext.getInstance("TLS");
                    sc.init(null, TRUST_ALL_CERTS, null);
                    sf = sc.getSocketFactory();
                } catch (java.security.NoSuchAlgorithmException | java.security.KeyManagementException e) {

                }
                tls.negotiate(sf);
            } else {
                tls.negotiate();
            }

            //Change base info
            try {
                String domain = getDomain(context);
                String uname = user.getsAMAccountName();
                Attributes attrs = getCommonUserAttrs(user,domain);
                //lisi@xxx.xxx.com
                putAttribute(attrs, "userPrincipalName", getPrincipalName(uname,context));
                context.modifyAttributes(dn,
                        DirContext.REPLACE_ATTRIBUTE, attrs);

            } catch (javax.naming.directory.InvalidAttributeValueException e) {
                String error = e.getMessage().trim();
                if (error.startsWith("[") && error.endsWith("]")) {
                    error = error.substring(1, error.length() - 1);
                }
                e.printStackTrace();
                tls.close();
                throw new NamingException("New password does not meet Active Directory requirements. " +
                        "Please ensure that the new password meets password complexity, " +
                        "length, minimum password age, and password history requirements.");
            } catch (NamingException e) {
                tls.close();
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Close the TLS/SSL session
            tls.close();


        }

        /**
         * 设置属性
         * @param attrs
         * @param attrName
         * @param attrValue
         */
        private void putAttribute(Attributes attrs, String attrName,
                                  Object attrValue) {
            if (attrValue != null && attrValue.toString().length() != 0) {
                Attribute attr = new BasicAttribute(attrName, attrValue);
                attrs.put(attr);
            }
        }

        /**
         * attrs获取通用方法
         * @param user
         * @param domain
         * @return
         * @throws Exception
         */
        private Attributes getCommonUserAttrs(User user,String domain) throws Exception {
            Attributes attrs = new BasicAttributes();
            // 设置属性
            putAttribute(attrs, "userPrincipalName", user.getsAMAccountName()+"@"+domain);
            putAttribute(attrs, "sAMAccountName", user.getsAMAccountName());
            if(StringUtil.isNotEmpty(user.getMail())) {
                putAttribute(attrs, "mail", user.getMail());
            }
            if(StringUtil.isNotEmpty(user.getGivenname())) {
                putAttribute(attrs, "givenname", user.getGivenname());
            }
            if(StringUtil.isNotEmpty(user.getCompany())) {
                putAttribute(attrs, "company", user.getCompany());
            }
            if(StringUtil.isNotEmpty(user.getDepartment())) {
                putAttribute(attrs, "department", user.getDepartment());
            }
            if(StringUtil.isNotEmpty(user.getDisplayName())) {
                putAttribute(attrs, "displayName", user.getDisplayName());
            }

            if(StringUtil.isNotEmpty(user.getTelephoneNumber())) {
                putAttribute(attrs, "telephoneNumber", user.getTelephoneNumber());
            }


            if(StringUtil.isNotEmpty(user.getTitle())) {
                putAttribute(attrs, "title", user.getTitle());
            }

            if(StringUtil.isNotEmpty(user.getUnicodePwd())) {
                putAttribute(attrs, "unicodePwd", ("\"" + user.getUnicodePwd() +
                        "\"").getBytes("UTF-16LE"));
            }
            return attrs;
        }

        private static final HostnameVerifier DO_NOT_VERIFY = (hostname, session) -> true;

        private static TrustManager[] TRUST_ALL_CERTS = new TrustManager[]{new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }
        }};
    }

    public static void main(String args[]) throws NamingException {
        LdapContext ldapContext = getConnection("administrator", "Hpinvent@2017", "cloud-star.local", "10.69.0.254","389",false,null);
        // LdapContext ldapContext = getConnection("tommy.liu", "!QAZ2wsx", "cloud-star.local", "10.69.0.254");
        //LdapContext ldapContext = getConnection("jerrylyi", "Hpinvent@1990", "cloud-star.local", "10.69.0.254");

        /*User user = getUser("tommy.liu", ldapContext);
        System.out.println(JsonUtil.toJson(user));
        user.changePassword("!QAZ2wsx", "1qaz@WSX", true, ldapContext);
        LdapContext ldapContext2 = getConnection("tommy.liu", "1qaz@WSX", "cloud-star.local", "10.69.0.254");
   */

        User user = getUser("jerrylyi", ldapContext);
        System.out.println(JsonUtil.toJson(user));
        //user.changePassword("Hpinvent@2016", "Hpinvent@1990", true, ldapContext);
        //LdapContext ldapContext2 = getConnection("tommy.liu", "1qaz@WSX", "cloud-star.local", "10.69.0.254");
        List<User> users = ActiveDirectory.getUsers(ldapContext, "", "", "");
        System.out.println("finish"+users.size());
    }
}