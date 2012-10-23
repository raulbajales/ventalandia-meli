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
	$http({method: "GET", url: "/api/users/me", headers: {"x-vtd-token": $cookies["vtd_token"]}}).success(function(data, status, headers, config) {
		$scope.firstName = data.name;
		$scope.lastName = data.surname;
		var repColor = repLevels[data.sellerReputationLevel];
		$(".reputation.badge").css("background-color", repColor);
	}).error(function(data, status, headers, config) {
		alert("error! " + JSON.stringify(data));
	});
	$scope.firstName = "...";
	$scope.lastName = "...";
}


function NewsController() {}

function CustomersController() {}

function ProductsController() {}
