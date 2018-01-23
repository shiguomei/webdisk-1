<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="GBK">
		<title></title>
		<style type="text/css">
			#div01{
				margin: 100px auto;
				text-align: center;
			}
			#div01 input[type=text]{
				width: 300px;
				height: 40px;
			}
			#div01 input[type=password]{
				width: 300px;
				height: 40px;
			}
		</style>
		<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script type="text/javascript" src=js/json2.js></script>
<script type="text/javascript">

function rig() {
	var name = document.getElementsByName('userName')[0].value;
	var password = document.getElementsByName('userPassword')[0].value;
	var form1 = new Object();
	form1.userName=name;
	form1.userPassword=password;
	var content =JSON.stringify(form1);
	console.log(content);
	$.ajax({
		   type: "POST",
		   url: "${pageContext.request.contextPath }/user/register.do",
		   data: content,
		   contentType:"application/json",
		   success: function(backDate,b,c){
			  window.location.href="${pageContext.request.contextPath }/jsp/login.jsp";
		   }
		}); 
}
</script>
	</head>
	<body>
		<div id="div01">
			<form >
				<input type="text" placeholder="用户名只能为6-16为英文数字，英文开头" name="userName" /><br><br>
				<input type="password" placeholder="密码只能为6-16为英文数字" name="userPassword" /><br><br>
			
			</form>
			<button onclick="rig()">注册</button>
		</div>
	</body>
</html>
