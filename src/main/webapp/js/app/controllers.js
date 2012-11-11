/* -------------------------------------------------- */
//
//  MiniProfileController
//
/* -------------------------------------------------- */

ventalandia.controller.MiniProfileController = function($scope, UserService) {
	UserService.getMyProfile(function(miniProfile) {
		$scope.miniProfile = miniProfile;
		$scope.reputationClass = ventalandia.ui.reputationClassFor($scope.miniProfile.sellerReputationLevel);
		$scope.$emit('broadcast', {id: "miniProfileLoaded", data: $scope.miniProfile});
	});
}
ventalandia.controller.MiniProfileController.$inject = ['$scope', 'UserService'];

/* -------------------------------------------------- */
//
//  NewsController
//
/* -------------------------------------------------- */

ventalandia.controller.NewsController = function($scope, NewsService) {
	NewsService.getMyNewsfeed(function(newsfeed) {
		$scope.newsfeed = newsfeed.hasEntries() ? newsfeed : null;			
	});

	$scope.showNewsDetails = function($event, entry) {
		ventalandia.ui.newsfeed.activate($event.currentTarget);
 		$scope.$emit('broadcast', {id: "showNewsDetails", data: entry});
	}		
}
ventalandia.controller.NewsController.$inject = ['$scope', 'NewsService'];

/* -------------------------------------------------- */
//
//  NewsDetailsController
//
/* -------------------------------------------------- */

ventalandia.controller.NewsDetailsController = function($scope, NewsService) {
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
ventalandia.controller.NewsController.$inject = ['$scope', 'NewsService'];

/* -------------------------------------------------- */
//
//  TopbarController
//
/* -------------------------------------------------- */

ventalandia.controller.TopbarController = function($scope, NewsService) {
	NewsService.getMyNewsSummary(function(summary) {
		$scope.summary = summary;
	});

	$scope.confirmLogout = function() {
        ventalandia.ui.confirm("Salir", "Esta seguro de que desea salir de Ventalandia?", "Cancelar", "Ok", function() {
        	ventalandia.ui.logout();
        });
	}
}
ventalandia.controller.TopbarController.$inject = ['$scope', 'NewsService'];

ventalandia.controller.CustomersController = function() {}

ventalandia.controller.ProductsController = function() {}
