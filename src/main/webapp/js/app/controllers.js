/* -------------------------------------------------- */
//
//  The Controllers
//
/* -------------------------------------------------- */

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
		NewsService.sendAnswer(question.id, question.answer, function() {
			question.answered = true;
		});
	};	
}


ventalandia.controller.TopbarController = function($scope, $cookies, $http) {
	NewsService.getMyNewsSummary(function(summary) {
		$scope.summary = summary;
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
