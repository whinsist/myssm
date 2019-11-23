package cn.com.cloudstar.rightcloud.system.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class BackPageController   {

    // http://localhost:8081/api/v1/back/page/login
    // http://localhost:8081/api/v1/back/page/test/fileUpload
	@RequestMapping("/back/page/{pageName}")
    public String page1(@PathVariable String pageName) {
    	return "back_page/" + pageName;
    }


	@RequestMapping("/back/page/{module}/{pageName}")
    public String page2(@PathVariable String module, @PathVariable String pageName, HttpServletRequest request) {
	    request.setAttribute("id", request.getParameter("id"));
    	return "back_page/" + module +"/" + pageName;
    }




}
