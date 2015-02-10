<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/themes/icon.css">
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath }/resources/themes/color.css">
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath }/css/Mng/userMng.css">
<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/jquery.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/common/common.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/js/security/login.js"></script>

</head>
<body>
    <div style="margin:20px 0;"></div>
    <div class="easyui-panel" title="Login to system" style="width:400px;padding:30px 70px 20px 70px">
    <form id="loginForm" action="loginprocess.do" method="post">
        <div style="margin-bottom:10px">
            <input class="easyui-textbox" name="username" style="width:100%;height:40px;padding:12px" data-options="prompt:'Username',iconCls:'icon-man',iconWidth:38">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="password" type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'Password',iconCls:'icon-lock',iconWidth:38">
        </div>
        <div style="margin-bottom:20px">
            <input type="checkbox" checked="checked">
            <span>Remember me</span>
        </div>
        <div>
            <a href="javascript:login.submit();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
                <span style="font-size:14px;">Login</span>
            </a>
        </div>
      </form>
    </div>
</body>
</html>