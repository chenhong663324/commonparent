<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>登录页面</title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/style1.css" type=text/css rel=stylesheet>
<link href="${pageContext.request.contextPath}/css/boot-crm.css" type=text/css rel=stylesheet>
	<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css" />
<meta content="MSHTML 6.00.2600.0" name=GENERATOR>

<script>
// 判断是登录账号和密码是否为空
function check(){
    var username = $("#username1").val();
    var password = $("#password1").val();
    if(username=="" || password==""){
    	$("#message").text("账号或密码不能为空！");
        return false;
    }
    return true;
}
</script>
</head>
<body  leftMargin=0 topMargin=0 marginwidth="0" marginheight="0"  background="${pageContext.request.contextPath}/images/main3.jpg">
<div ALIGN="center">
<table border="0" width="400px" cellspacing="0" cellpadding="0"
                                                           id="table1">
	<tr>
		<td height="200"></td>
	</tr>
	<tr>

   </td>
   <td class="login_msg" width="400" align="center">
	 <!-- margin:0px auto; 控制当前标签居中 -->
	 <fieldset style="width: auto; margin: 0px auto;">
		  <legend>
		     <font style="font-size:30px" face="宋体" color="white">
		          欢迎使用全民吐槽厅
		     </font>
		  </legend> 
		<font color="red">
			 <span id="message">${sessionScope['org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS'][0]['msg']}</span>
		</font>
		<form action="${pageContext.request.contextPath }/user/login.action" 
			                       method="post" onsubmit="return check()">
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
			<dd class="user_icon">
          账&nbsp;号：<input id="username1" type="text" class="login_txtbx" name="username" />
			</dd>
          <br /><br />
			<dd class="user_icon">
			 密&nbsp;码：<input id="password1" type="password" name="password" />
			</dd>
          <br />&nbsp;
          <center>&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" >&nbsp;&nbsp;登录&nbsp;&nbsp;</button></center>
		 </form><br />&nbsp;
		 &nbsp;&nbsp;<button  type="submit" data-toggle="modal" data-target="#myModal" ><font color="red">&nbsp;&nbsp;注册&nbsp;&nbsp;</font></button>
	 </fieldset>
	</td>
	</tr>
</table>
</div>


<!-- 添加用户信息的模态框-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	 aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!-- 模态框的标题 -->
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">用户注册信息</h4>
			</div>
			<!-- 模态框的主体-表单头部 -->
			<form class="form-horizontal" role="form"
				  action="${pageContext.request.contextPath }/user/addUser.action"
				  method="post" id="form" enctype="multipart/form-data" >
				<div class="modal-body">
					<div class="form-group">
						<label class="col-sm-3 control-label">姓名:</label>
						<div class="col-sm-5">
							<input type="text" class="form-control input-lg" id="username"
								   name="username" placeholder="请输入用户名" required autofocus>
						</div>
					</div>
					<div class="form-group  form-group-lg">
						<label  class="col-sm-3 control-label">密码:</label>
						<div class="col-sm-5">
							<input type="text" class="form-control input-lg" id="password"
								   name="password" placeholder="请输入密码" required autofocus>
						</div>
					</div>
					<div class="form-group">
						<label  class="col-sm-3 control-label">上传用户照片:</label>
						<div class="col-sm-5">
							<input type="file" class="form-control input-lg" id="pic"
								   name="pic">
						</div>
					</div>
				</div>
				<!-- 模态框的尾部 -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-primary" id="save">保存</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>

<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.zh-CN.js"></script>

</html>
