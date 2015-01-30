var rankApp = angular.module('rankApp',['smart-table'])
.controller('rankController',['$scope', '$http',  function($scope, $http){

	$scope.title = "직급관리";
	
	var dataRank = $http.get("rank_list.do").
	  success(function(data, status, headers, config) {
		  $scope.rowCollection = data;
	  }).
	  error(function(data, status, headers, config) {
		    // called asynchronously if an error occurs
		    // or server returns response with an error status.
	  });
	
	
}]);