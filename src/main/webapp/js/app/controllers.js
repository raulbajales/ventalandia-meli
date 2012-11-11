/* -------------------------------------------------- */
//
//  Events
//
/* -------------------------------------------------- */

ventalandia.controller.event = {
	MINI_PROFILE_LOADED: "miniProfileLoaded",
	NEWS_DETAILS_REQUESTED: "newsDetailsRequested"
}

/* -------------------------------------------------- */
//
//  MiniProfileController
//
/* -------------------------------------------------- */

ventalandia.controller.MiniProfileController = function($scope, UserService) {
	var event = ventalandia.controller.event;
	
	UserService.getMyProfile(function(miniProfile) {
		$scope.miniProfile = miniProfile;
		$scope.reputationClass = ventalandia.ui.reputationClassFor($scope.miniProfile.sellerReputationLevel);
		$scope.$emit('broadcast', {
			id: event.MINI_PROFILE_LOADED, 
			data: $scope.miniProfile
		});
	});
}

/* -------------------------------------------------- */
//
//  NewsController
//
/* -------------------------------------------------- */

ventalandia.controller.NewsController = function($scope, NewsService) {
	var event = ventalandia.controller.event;
	
	NewsService.getMyNewsfeed(function(newsfeed) {
		$scope.newsfeed = newsfeed.hasEntries() ? newsfeed : null;			
	});

	$scope.showNewsDetails = function($event, entry) {
		ventalandia.ui.newsfeed.activate($event.currentTarget);
 		$scope.$emit('broadcast', {
 			id: event.NEWS_DETAILS_REQUESTED, 
 			data: entry
 		});
	}		
}

/* -------------------------------------------------- */
//
//  NewsDetailsController
//
/* -------------------------------------------------- */

ventalandia.controller.NewsDetailsController = function($scope, NewsService) {
	var event = ventalandia.controller.event;

	$scope.$on(event.NEWS_DETAILS_REQUESTED, function(event, entry) {
		NewsService.getNewsDetails(entry.id, function(newsDetails) {
		    $scope.newsDetails = newsDetails;
	    });
	});

	$scope.$on(event.NEWS_DETAILS_REQUESTED, function(event, miniProfile) {
		$scope.miniProfile = miniProfile;
	});

	$scope.sendAnswer = function(question) {
		if (!question.answer) return;
		NewsService.sendAnswer(question.id, question.answer, function() {
			question.answered = true;
		});
	};	
}

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

/* -------------------------------------------------- */
//
//  CustomersController
//
/* -------------------------------------------------- */

ventalandia.controller.CustomersController = function() {}

/* -------------------------------------------------- */
//
//  ProductsController
//
/* -------------------------------------------------- */

ventalandia.controller.ProductsController = function() {}
