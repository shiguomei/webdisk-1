<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>WebDisk网络硬盘</title>
<style type="text/css">
body {
	width: 860px;
	margin: 0 auto;
	font-size: 16px;
	background: #ccc;
}

#nav {
	height: 35px;
	background: #eeeeee;
	
	font-size: 20px;
}

a {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	color: burlywood;
	text-decoration: none;
	
}
#zong{
 text-align: right;
 }


#bodycontene {
	font-size: 20px;
}
</style>
<script src="../js/jquery-1.8.2.js" type="text/javascript"></script>
<script type="text/javascript">
			$(function(){
				if('${user}'==''){
					window.location.href="${pageContext.request.contextPath }/jsp/login.jsp";

				}else{
					$.get('${pageContext.request.contextPath }/upload/size.do','value=${user}',function(a,b,c){
						$('#zong').text(a);
					
					});
					$.post('${pageContext.request.contextPath }/upload/select.do','value=${user}',function(a,b,c){
						
						
						
							//window.location.href="${pageContext.request.contextPath }/jsp/login.jsp";
					
							var $xml=c.responseXML;
						
							var $file = $xml.getElementsByTagName('file');
							var $id = $xml.getElementsByTagName('id');
							var $name =$xml.getElementsByTagName('name');
							var $size =$xml.getElementsByTagName('size');
							var $date =$xml.getElementsByTagName('date');
							
							for(var i = 0;i<$file.length;i++){
								var idval = $id[i].firstChild.nodeValue;
								var nameval = $name[i].firstChild.nodeValue;
								var sizeval = $size[i].firstChild.nodeValue;
								var dateval = $date[i].firstChild.nodeValue;
								
								$('#trone').after("<tr align=center><td>"+idval+"</td><td>"+decodeURI(nameval)+"</td><td>"+decodeURI(sizeval)+"</td><td>"+dateval+"</td><td><a href=${pageContext.request.contextPath }/upload/down.do?id="+idval+">下载</a>||<a href=${pageContext.request.contextPath }/upload/del.do?userName=${user}&id="+idval+">删除</a></td></tr>");
							}
						
			
					});
					
				}
			
			});
		</script>
</head>

<body>
	<div id="nav">

		&nbsp;&nbsp;&nbsp; <a href="#">我的网盘</a> || <a
			href="${pageContext.request.contextPath }/jsp/main/upload.jsp">我要上传</a> 
			会员:${user }<a
			href="${pageContext.request.contextPath }/user/quit.do">退出</a> 
	</div>
	<div id="bodycontene">
		<table border="1" cellspacing="0" cellpadding="0" align="center"
			width="860px">

			<tr align="center" id="trone">
				<td>编号</td>
				<td>文件名</td>
				<td>文件大小</td>
				<td>文件上传日期</td>
				<td>文件操作</td>
			</tr>
			<tr align="right"><td colspan="5"><span
			id=zong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
		</table>
	</div>
</body>

</html>