<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <table id="dg" title="Department List" class="easyui-datagrid" style="width:700px;height:250px"
           url="${pageContext.request.contextPath }/departList/getDeptList.do"
           toolbar="#toolbar" pagination="true"
           rownumbers="true" fitColumns="true" singleSelect="true">
       <thead>
           <tr>
               <th field="div_name" width="50">부서명</th>
               <th field="div_code"  width="50">부서 코드</th>
               <th field="div_parent" width="50">상위부서</th>
           </tr>
       </thead>
   </table>
   <div id="toolbar">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newDept()">부서 추가</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editDept()">부서 수정</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteDept($('#div_code'))">부서 삭제</a>
	   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updDiv()">수정</a>       
   </div>
   
   <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
           closed="true" buttons="#dlg-buttons">
       <div class="ftitle">Department Information</div>
       <form id="fm" method="post" novalidate>
           <div class="fitem">
               <label>부서 명 :</label>
               <input name="div_name" id="div_name" class="easyui-textbox" required="true">
           </div>
           <div class="fitem">
               <label>부서 코드 :</label>
               <input name="div_code" id="div_code" class="easyui-textbox" required="true">
           </div>
           <div class="fitem">
               <label>상위 부서 :</label>
               <input name="div_parent" id="div_parent" class="easyui-textbox">
           </div>
       </form>
   </div>
   <div id="dlg-buttons">
       <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveDept()" style="width:90px">Save</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
   </div>
   <script type="text/javascript">
       var url;
       function newDept(){
           $('#dlg').dialog('open').dialog('setTitle','New Department');
           $('#fm').form('clear');
           url = '${pageContext.request.contextPath}/departList/dept_save.do';
       }
       function editDept(){
           var row = $('#dg').datagrid('getSelected');
           if (row){
               $('#dlg').dialog('open').dialog('setTitle','Edit Department');
               $('#fm').form('load',row);
               var old_code = $('#div_code').val();
               url = '${pageContext.request.contextPath}/departList/dept_update.do?old_code='+old_code;
               
           }
       }
       function saveDept(){
           $('#fm').form('submit',{
               url: url,
               onSubmit: function(){
                   return $(this).form('validate');
               }
           });
           $('#dlg').dialog('close');        // close the dialog
           $('#dg').datagrid('reload');    // reload the user data
       }
       function deleteDept(div_code){
           var row = $('#dg').datagrid('getSelected');
           //var div_code = row.$('#div_code').val();
           if (row){
                $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
                    if (r){
                        $.post('${pageContext.request.contextPath}/departList/dept_delete.do?div_code='+row.div_code
                        		,function(result){
                                $('#dg').datagrid('reload');    // reload the user data
                        },'json');
                    }
                });
                
            }
        }
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>

