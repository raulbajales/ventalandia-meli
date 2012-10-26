'use strict';

/* Controllers */

function MiniProfileController($scope, $cookies, $http) {
	var repLevels = {
		0: "grey",
		1: "red",
		2: "orange",
		3: "yellow",
		4: "lightgreen",
		5: "green"
	};
	$scope.firstName = "...";
	$scope.lastName = "...";
	$http({method: "GET", url: "/api/users/me", 
		   headers: {"x-vtd-token": $cookies["vtd_token"]}
	    }).success(function(data, status, headers, config) {
			$scope.firstName = data.name;
			$scope.lastName = data.surname;
			$scope.reputationClass = repLevels[data.sellerReputationLevel];
		}).error(function(data, status, headers, config) {
			console.log("[ERROR] - Unable to get profile data");
			console.log(JSON.stringify(data)); // invoke a general ui error handler
		});
}

function NewsController($scope, $cookies, $http) {
	$http({method: "GET", url: "/api/news", 
		   headers: {"x-vtd-token": $cookies["vtd_token"]}
	    }).success(function(data, status, headers, config) {
			$scope.newsfeed = data;
			//$scope.newsfeed = [{"id":5006,"buyer":{"id":12345,"nickname":"ICLACREYO"},"date":"2012-10- 10T13:10:52.067","type":"QUESTION","item":{"id":"MLA434525953","title":"Bicicleta rodado 26"},"entityId":0}];
		}).error(function(data, status, headers, config) {
			console.log("[ERROR] - Unable to get newsfeed");
			console.log(JSON.stringify(data)); // invoke a general ui error handler
		});	
}

function CustomersController() {}

function ProductsController() {}
