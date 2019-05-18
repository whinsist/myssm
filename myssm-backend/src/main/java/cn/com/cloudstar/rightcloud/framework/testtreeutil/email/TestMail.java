package cn.com.cloudstar.rightcloud.framework.testtreeutil.email;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Attachment;
import cn.com.cloudstar.rightcloud.framework.common.util.MailUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class TestMail {
    public static void main(String[] args) {
        List<String> toAddressList = new ArrayList<>();
        toAddressList.add("444089024@qq.com");
        toAddressList.add("wuhong@cloud-star.com.cn");

        List<Attachment> attachtList = new ArrayList<>();
        Attachment att1 = new Attachment();
        att1.setAttachmentLocation("E:\\temp\\1.jpg");
        att1.setOriginalName("1.jpg");
        attachtList.add(att1);



        String str = "<html lang=\"en\"> <head>   <base target=\"_blank\">   <meta charset=\"utf-8\">    <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0;\">  <meta name=\"format-detection\" content=\"telephone=no\"/>   <style> /* Reset styles */  body { margin: 0; padding: 0; min-width: 100%; width: 100% !important; height: 100%  !important;} body, table, td, div, p, a { -webkit-font-smoothing: antialiased;  text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;  line-height: 100%; } table, td { mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse !important; border-spacing: 0; } img { border: 0;  line-height: 100%; outline: none; text-decoration: none; -ms-interpolation-mode:  bicubic; } #outlook a { padding: 0; } .ReadMsgBody { width: 100%; } .ExternalClass { width: 100%; } .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font,  .ExternalClass td, .ExternalClass div { line-height: 100%; } /* Rounded corners for advanced mail clients only */ @media all and (min-width: 560px) {   .container { border-radius: 8px; -webkit-border-radius: 8px; -moz-border-radius: 8px; -khtml-border-radius: 8px;} } : :-webkit-scrollbar {     display: none;   }   body {     font-size: 14px;      font-family: arial, verdana, sans-serif;     line-height: 1.666;     padding: 0;    margin: 0;     overflow: auto;     white-space: normal;     word-wrap: break-word;    min-height: 100px   }    td, input, button, select, body {     font-family: Helvetica,  \'Microsoft Yahei\', verdana   }    pre {     white-space: pre-wrap;    white-space: -moz-pre-wrap;     white-space: -pre-wrap;     white-space: -o-pre-wrap;    word-wrap: break-word;     width: 95%   }    th, td {     font-family: arial, verdana, sans-serif;    line-height: 1.666   }    img {     border: 0   }    header, footer, section, aside, article, nav, hgroup,  figure, figcaption {     display: block   }   a{color:#428BCA}     </style> </head> <body> <div style=\"width: 100%;margin: 0  auto;max-width:1000px;\">   <div class=\"banner-has\" style=\"width: 100%;height: 80px;background: black;background-repeat: no-repeat;background-position: center;background-size:100%;margin-bottom: 4px;\">    &nbsp;<a href=\'https://www.rightcloud.com.cn/\'><img src=\'https://www.rightcloud.com.cn/images/logo/rightcloud_logo.png\' style=\'margin-top:15px;max-width:150px;margin-left:20px;\'><div  style=\'float:right;margin-top:30px;max-width:300px;color:#fff;margin-right:20px;\'> 咨询热线&nbsp;:&nbsp;400-030-8008  </div></a>   </div>     <div style=\"padding-bottom: 5%;      padding-top: 5%;    background: rgb(248,248,248);    padding-left: 5%;     padding-right:5%;  width: 90%;     margin-bottom: 4px;\">       <h2 style=\"font-size: 18px;color: #555556;margin-bottom: 30px;\">尊敬的 <span style=\"color:#0096D6;\">${owner}</span>：</h2>     <p style=\"font-size: 14px;color: #6c6f70;margin-bottom: 20px;line-height: 30px;\"></p>   <p><span>您好，欢迎使用${systemName},您的实名认证结果：${result}</span></p>   <p><span>请点击下方链接登陆查看</span></p>   <p><span> <a href=\"${url}\" target=\"_blank\">${url}</a> </span></p>   <p><span style = \"float:right\">谢谢</span></p>   </div>  <div style=\"background: black;     font-size: 12px;     line-height: 62px;  color: #a7b3b3;     width: 100%; text-align:center; max-width:1000px;\">Copyright &copy; ${companyName} 版权所有   </div> </div> </body> </html>";
        StringBuffer mailContent = new StringBuffer(str);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("owner", "wuhong");
        paramMap.put("companyName", "云星数据");
        paramMap.put("systemName", "云管理系统");
        paramMap.put("result", "success");
        paramMap.put("url", "http://baidu.com");


        paramMap.forEach((key, value) -> {
            StringUtil.strBufReplace("${" + key + "}", value, mailContent);
        });




        MailUtil.sendMail(toAddressList, null, null, "【RightCloud】审核结果通知", mailContent.toString(), attachtList);
        
        System.out.println("发送成功");
    }
}
