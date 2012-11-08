/* Controllers */

ventalandia.controller.MiniProfileController = function($scope, UserService) {
	UserService.getMyProfile(function(miniProfile) {
		$scope.miniProfile = miniProfile;
		$scope.reputationClass = ventalandia.ui.reputationClassFor($scope.miniProfile.sellerReputationLevel);
		$scope.$emit('broadcast', {id: "miniProfileLoaded", data: $scope.miniProfile});
	});
}

ventalandia.controller.NewsController = function($scope, NewsService) {
	NewsService.getMyNewsfeed(function(newsfeed) {
		$scope.newsfeed = newsfeed.hasEntries() ? newsfeed : null;			
	});

	$scope.showNewsDetails = function($event, entry) {
		ventalandia.ui.newsfeed.activate($event.currentTarget);
 		$scope.$emit('broadcast', {id: "showNewsDetails", data: entry});
	}		
}

ventalandia.controller.NewsDetailsController = function($scope, $cookies, $http) {
	$scope.$on('showNewsDetails', function(event, entry) {
		NewsService.getNewsDetails(entry.id, function(newsDetails) {
		    $scope.newsDetails = newsDetails;
	    });
	});

	$scope.$on('miniProfileLoaded', function(event, miniProfile) {
		$scope.miniProfile = miniProfile;
	});

	$scope.sendAnswer = function(question) {
		if (!question.answer) return;
		var theBody = {"question_id": question.id, "text": question.answer};
		$http({method: "POST", url: "/api/answers/", 
			   headers: {"x-vtd-token": $cookies["vtd_token"],
			             "Content-Type": "application/json"},
			   data: theBody
		    }).success(function(data, status, headers, config) {
		    	question.answered = true;
		    	console.log("Just sent answer: " + JSON.stringify(theBody));
			}).error(function(data, status, headers, config) {
				console.log("[ERROR] - Unable send answer '" + JSON.stringify(question) + "'");
				console.log(JSON.stringify(data)); // invoke a general ui error handler
			});			
	};	
}


ventalandia.controller.TopbarController = function($scope, $cookies, $http) {
	$http({method: "GET", url: "/api/news/summary", 
		   headers: {"x-vtd-token": $cookies["vtd_token"]}
	    }).success(function(data, status, headers, config) {
	    	var summary = ventalandia.model.Summary.fromObject(data);

			/* Mock data for test: */
			/*
			var summary = ventalandia.model.Summary.fromObject(ventalandia.test.mocks.newsSummary);
			*/

			$scope.summary = summary;
		}).error(function(data, status, headers, config) {
			console.log("[ERROR] - Unable to get news summary ");
			console.log(JSON.stringify(data)); // invoke a general ui error handler
		});

	$scope.confirmLogout = function() {
        ventalandia.ui.confirm("Salir", "Esta seguro de que desea salir de Ventalandia?", "Cancelar", "Ok", function() {
          document.cookie = 'vtd_token=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
          document.location.href="/";
          document.location.reload();
        });
	}
}

ventalandia.controller.CustomersController = function() {}

ventalandia.controller.ProductsController = function() {}
