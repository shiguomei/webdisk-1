<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
		<title>WebDisk网络硬盘</title>
		<style type="text/css">
			body {
				width: 860px;
				margin: 0 auto;
				font-size: 12px;
				background: #ccc;
			}
			
			#nav {
				height: 35px;
				background: #eeeeee;
				text-align: left;
				font-size: 20px;
			}
			
			#nav a {
				color: black;
				text-decoration: none;
			}
			
			#nav a:visited {
				color: black;
				text-decoration: none;
			}
			
			#nav a:hover {
				color: burlywood;
				text-decoration: none;
				font-size: 25px;
			}
			
			#bodycontene {
				margin: 100px auto;
				font-size: 20px;
			}
		</style>
		<script src="../js/jquery-1.8.2.js" type="text/javascript"></script>
<script type="text/javascript">

$(function(){
	
	var a = '${user}';
	if(a==""){
		window.location.href="${pageContext.request.contextPath }/jsp/login.jsp";
	}
	
	
	
});
</script>
	</head>

	<body>
		<div id="nav">

			&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath }/jsp/main/index.jsp">我的网盘</a>
			||
			<a href="#">我要上传</a>

			会员:${user }<a href="${pageContext.request.contextPath }/user/quit.do">退出</a> 
		</div>
		<div id="bodycontene" align="center">
			<form action="${pageContext.request.contextPath }/upload/upload.do" method="post" enctype="multipart/form-data">
				<input type="file" name="file01" />
				<input type="submit" value="上传"/>
		</form>
		${exis} 
			
		
		</div>
	</body>

</html>