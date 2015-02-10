	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript"	src="${pageContext.request.contextPath }/js/usermng/userMng.js"></script>
	<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath }/css/Mng/userMng.css">
	<div id="mainBody" style="width:700px;">
	  <div style="float:right; margin-bottom:5px;">		
	  		<select class="easyui-combobox" name="state" style="width:100px;">
		        <option value="empId">사용자아이디</option>
		        <option value="empName">사용자이름</option>
	        </select>
	        <input class="easyui-textbox" data-options="buttonText:'Search',buttonIcon:'icon-search',prompt:'Search...'" style="width:200px;height:24px;">
	  </div>
	  <div>
		  <table id="dg" title="직원목록" class="easyui-datagrid" style="width:100%; height:250px"
		            url="${pageContext.request.contextPath }/usermng/getListUser.do"
		            toolbar="#toolbar" pagination="true"
		            rownumbers="true" fitColumns="true" singleSelect="true">
		        <thead>
		            <tr>
		            	<th field="anbUserUserId" width="50">사용자 아이디</th>
		            	<th field="empName" width="50">사용자이름</th>
		            	<th field="anbRankRankCode" hidden width="50">직급</th>
		            	<th field="anbDivDivCode" hidden width="50">부서</th>
		            	<th field="anbRankRankName" width="50">직급</th>
		            	<th field="anbDivDivName" width="50">부서</th>
		                <th field="empEmail" width="50">이메일</th>               
		                <th field="empPhone" width="50">전화번호</th>
		                <th field="empAddress" width="50">주소</th>
		            </tr>
		        </thead>
		    </table>
		</div>
	</div>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="userMng.newUser()">New User</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="userMng.editUser()">Edit User</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="userMng.removeUser()">Remove User</a>
    </div>
    
     <div id="dlg" class="easyui-dialog" style="width:400px;height:340px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
        <div class="ftitle">User Information</div>
        <form id="fm" method="post" novalidate>
     	<input type="hidden" id="mode" name="mode" />
            <div class="fitem">
                <label>사용자 아이디</label>
                <input id="anbUserUserId" name="anbUserUserId" class="easyui-textbox" required="true" maxlength="20">
            </div>
            <div class="fitem">
                <label>사용자이름</label>
                <input name="empName" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>이메일</label>
                <input name="empEmail" class="easyui-textbox" validType="email">
            </div>
            <div class="fitem">
                <label>전화번호</label>
                <input name="empPhone" class="easyui-textbox">
            </div>
            <div class="fitem">
                <label>주소</label>
                <input name="empAddress" class="easyui-textbox">
            </div>
            <div class="fitem">
                <label>직급</label>
                <input class="easyui-combobox" id="anbRankRankCode" name="anbRankRankCode" data-options="valueField:'code',textField:'name'">
            </div>
            <div class="fitem">
                <label>부서</label>
                <input class="easyui-combobox" id="anbDivDivCode" name="anbDivDivCode" data-options="valueField:'code',textField:'name'">
            </div>
        </form>
    </div>
<%--    <security:authentication var="user" property="principal.username" /> --%>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="userMng.saveUser()" style="width:90px">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
    </div>