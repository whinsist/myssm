<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="jz_d_nav">
	<a href="/" <c:if test="${footNav==0}">class="over"</c:if>>
		<div class="a1 nav1"></div>
		<div class="a2">首页</div>
	</a>
	<a href="/dec/item.shtml" <c:if test="${footNav==2}">class="over"</c:if>>
		<div class="a1 nav3"></div>
		<div class="a2">列表</div>
	</a>
	<a href="/dec/msg.shtml" <c:if test="${footNav==3}">class="over"</c:if>>
		<div class="a1 nav4"></div>
		<div class="a2">咨询</div>
	</a>
</div>
<script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
<style>
.support-coat{
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,.3);
    overflow: hidden;
    z-index: 100;
}
.support-coat .support{
    position: absolute;
    top: 50%;
    left: 50%;
    width: 56%;
    margin: -60px 0 0 -28%;
    background-color: #fff; padding-top:0.6rem;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;
    text-align: center;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
.support-coat .support p{
    text-align: center;
    padding-bottom: 0.6rem;
    color: #666;
}
.support-coat .support div{
    border-top: 1px solid #eee;
}
.support-coat .support div a{
    float: left;
    display: block;max-width: -moz-calc(50% - 1px); max-width: -webkit-calc(50% - 1px);max-width: calc(50% - 1px);
    width: 50%;
    padding: 10px 0;
    text-align: center;
    color: #ff6900;
    border-left: 1px solid #eee;
}
.support-coat .support div a:first-child{
    border-left: none;
}
</style>
 
 
 
 