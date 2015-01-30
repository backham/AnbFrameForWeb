<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/app/rank_mng.js"></script>

<div ng-app="rankApp">
	<div ng-controller="rankController">
		<h3>{{title}}</h3>

 <table st-table="rowCollection" class="table table-striped">
 	<thead>
 		<tr>
 			<th>직급명</th>
 			<th>직급코드</th>
 		</tr>
 	</thead>
 	<tbody>
            <tr ng-repeat="rank in rowCollection">
                <td>
                    {{rank.rankName}}
                </td>
                <td>
                    {{rank.rankCode}}
                </td>
            </tr>
            </tbody>
        </table>

	</div>
</div>